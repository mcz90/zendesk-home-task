package com.czyzewski.zendeskhometask.network.datasource

import com.czyzewski.zendeskhometask.network.networkhandling.NetworkResponse
import com.czyzewski.zendeskhometask.network.api.ZendeskApi
import com.czyzewski.zendeskhometask.network.dto.TicketsResponseDto
import javax.inject.Inject

interface ITicketsDataSource {
    suspend fun getTickets(viewId: Long): NetworkResponse<TicketsResponseDto>
}

class TicketsDataSource @Inject constructor(
    private val api: ZendeskApi
) : ITicketsDataSource {

    override suspend fun getTickets(viewId: Long): NetworkResponse<TicketsResponseDto> {
        return api.getTickets(viewId)
    }
}
