package com.czyzewski.zendeskhometask.network.networkhandling

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkCapabilities.NET_CAPABILITY_INTERNET
import android.net.NetworkRequest
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.cancellable
import kotlinx.coroutines.flow.map

class NetworkConnectivityManager(context: Context) {
    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?

    fun isNetworkAvailable(): Boolean {
        val networkCapabilities =
            connectivityManager?.getNetworkCapabilities(connectivityManager.activeNetwork)
        return when {
            networkCapabilities == null -> false
            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            else -> false
        }
    }

    fun observeNetworkState() = callbackFlow {
        val networkStatusCallback = object : ConnectivityManager.NetworkCallback() {
            override fun onUnavailable() {
                trySend(NetworkState.Unavailable)
            }

            override fun onAvailable(network: Network) {
                trySend(NetworkState.Available)
            }

            override fun onLost(network: Network) {
                trySend(NetworkState.ConnectionLost)
            }

            override fun onLosing(network: Network, maxMsToLive: Int) {
                trySend(NetworkState.ConnectionPoor)
            }
        }

        val request = NetworkRequest.Builder()
            .addCapability(NET_CAPABILITY_INTERNET)
            .build()
        connectivityManager?.registerNetworkCallback(request, networkStatusCallback)
        awaitClose {
            connectivityManager?.unregisterNetworkCallback(networkStatusCallback)
        }
    }.cancellable()
}

sealed class NetworkState {
    object Available : NetworkState()
    object Unavailable : NetworkState()
    object ConnectionLost : NetworkState()
    object ConnectionPoor : NetworkState()
}
