package com.czyzewski.zendeskhometask.network.networkhandling

sealed class NetworkResponse<out ResponseData> {
    data class Success<ResponseData>(val data: ResponseData) : NetworkResponse<ResponseData>()
    data class HttpError(
        val throwable: Throwable?,
        val errorCode: Int? = null
    ) : NetworkResponse<Nothing>()

    data class InternetError(val isNetworkAvailable: Boolean? = null) : NetworkResponse<Nothing>()
    data class UnknownError(val throwable: Throwable? = null) : NetworkResponse<Nothing>()
    object EmptyBody : NetworkResponse<Nothing>()
}
