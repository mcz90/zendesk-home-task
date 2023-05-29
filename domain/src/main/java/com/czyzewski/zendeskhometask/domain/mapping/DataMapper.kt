package com.czyzewski.zendeskhometask.domain.mapping

import com.czyzewski.zendeskhometask.domain.OutputData
import com.czyzewski.zendeskhometask.network.networkhandling.NetworkResponse

interface DataMapper<NetworkData, DomainData> {

    fun mapSuccess(responseData: NetworkData): DomainData

    fun map(response: NetworkResponse<NetworkData>): OutputData<DomainData> = mapResponse(response)

    private fun mapResponse(response: NetworkResponse<NetworkData>): OutputData<DomainData> =
        when (response) {
            is NetworkResponse.Success -> OutputData.Success(mapSuccess(response.data))
            is NetworkResponse.HttpError -> OutputData.HttpError(
                response.throwable,
                response.errorCode
            )

            is NetworkResponse.InternetError -> OutputData.InternetError(response.isNetworkAvailable)
            is NetworkResponse.UnknownError -> OutputData.UnknownError(response.throwable)
            NetworkResponse.EmptyBody -> OutputData.EmptyBody
        }
}
