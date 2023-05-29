package com.czyzewski.zendeskhometask.network.datasource

import com.czyzewski.zendeskhometask.network.api.ZendeskApi
import com.czyzewski.zendeskhometask.network.dto.TicketsResponseDto
import com.czyzewski.zendeskhometask.network.networkhandling.NetworkResponse
import javax.inject.Inject

interface ITicketsDataSource {
    suspend fun getTickets(viewId: Long, page: Int): NetworkResponse<TicketsResponseDto>
}

class TicketsDataSource @Inject constructor(
    private val api: ZendeskApi
) : ITicketsDataSource {

    override suspend fun getTickets(viewId: Long, page: Int): NetworkResponse<TicketsResponseDto> {
        return api.getTickets(viewId, page)
    }
}
