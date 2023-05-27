package com.czyzewski.zendeskhometask.network.networkhandling

import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.net.ssl.SSLException

internal class NetworkResponseCall<ResponseData : Any>(
    private val delegate: Call<ResponseData>,
    private val networkConnectivityManager: NetworkConnectivityManager
) : Call<NetworkResponse<ResponseData>> {
    override fun timeout(): Timeout = delegate.timeout()

    override fun enqueue(callback: Callback<NetworkResponse<ResponseData>>) {
        return delegate.enqueue(object : Callback<ResponseData> {
            override fun onResponse(call: Call<ResponseData>, response: Response<ResponseData>) {
                val networkResponse = getResponse(response)
                callback.onResponse(
                    this@NetworkResponseCall, networkResponse
                )
            }

            override fun onFailure(call: Call<ResponseData>, throwable: Throwable) {
                val networkResponse = getResponse(throwable)
                callback.onResponse(this@NetworkResponseCall, networkResponse)
            }
        })
    }

    override fun isExecuted() = delegate.isExecuted

    override fun clone() = NetworkResponseCall(delegate.clone(), networkConnectivityManager)

    override fun isCanceled() = delegate.isCanceled

    override fun cancel() = delegate.cancel()

    override fun execute(): Response<NetworkResponse<ResponseData>> {
        throw UnsupportedOperationException("NetworkResponseCall doesn't support execute")
    }

    override fun request(): Request = delegate.request()

    private fun getResponse(
        response: Response<ResponseData>
    ): Response<NetworkResponse<ResponseData>> {
        val body: ResponseData? = response.body()
        val code: Int = response.code()
        val result = if (response.isSuccessful && code in 200..299) {
            if (body != null) {
                NetworkResponse.Success(body)
            } else {
                NetworkResponse.UnknownError()
            }
        } else {
            NetworkResponse.HttpError(null, errorCode = code)
        }
        return Response.success(result)
    }

    private fun getResponse(
        throwable: Throwable
    ): Response<NetworkResponse<ResponseData>> {
        val result = when (throwable) {
            is HttpException -> {
                NetworkResponse.HttpError(throwable, errorCode = throwable.code())
            }

            is UnknownHostException, is SocketException, is SocketTimeoutException, is SSLException -> {
                val networkAvailable = networkConnectivityManager.isNetworkAvailable()
                NetworkResponse.InternetError(networkAvailable)
            }

            else -> NetworkResponse.UnknownError(throwable)
        }
        return Response.success(result)
    }
}
