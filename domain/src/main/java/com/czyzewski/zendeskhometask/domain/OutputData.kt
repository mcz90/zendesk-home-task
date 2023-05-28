package com.czyzewski.zendeskhometask.domain

import com.czyzewski.zendeskhometask.network.networkhandling.NetworkResponse

sealed class OutputData<out DomainData> {
    data class Success<DomainData>(val data: DomainData) : OutputData<DomainData>()
    data class Error(val error: Any?) : OutputData<Nothing>()
    data class HttpError(
        val throwable: Throwable?,
        val errorCode: Int? = null
    ) : OutputData<Nothing>()

    data class InternetError(val isNetworkAvailable: Boolean? = null) : OutputData<Nothing>()
    data class UnknownError(val throwable: Throwable? = null) : OutputData<Nothing>()
    object EmptyBody : OutputData<Nothing>()
}
