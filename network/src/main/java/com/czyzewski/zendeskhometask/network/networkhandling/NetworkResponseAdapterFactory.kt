package com.czyzewski.zendeskhometask.network.networkhandling

import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class NetworkResponseAdapterFactory(
    private val networkConnectivityManager: NetworkConnectivityManager
) : CallAdapter.Factory() {

    override fun get(
        returnType: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {

        if (Call::class.java != getRawType(returnType)) {
            return null
        }
        check(returnType is ParameterizedType) {
            "return type must be parameterized as Call<NetworkResponse<<Foo>> or Call<NetworkResponse<out Foo>>"
        }
        val responseType = getParameterUpperBound(0, returnType)
        if (getRawType(responseType) != NetworkResponse::class.java) {
            return null
        }
        check(responseType is ParameterizedType) { "Response must be parameterized as NetworkResponse<Foo> or NetworkResponse<out Foo>" }
        val successBodyType = getParameterUpperBound(0, responseType)

        return NetworkResponseAdapter(successBodyType, networkConnectivityManager)
    }
}

private class NetworkResponseAdapter(
    private val successType: Type,
    private val networkConnectivityManager: NetworkConnectivityManager
) : CallAdapter<Any, Call<NetworkResponse<Any>>> {

    override fun responseType(): Type = successType

    override fun adapt(call: Call<Any>): Call<NetworkResponse<Any>> {
        return NetworkResponseCall(call, networkConnectivityManager)
    }
}