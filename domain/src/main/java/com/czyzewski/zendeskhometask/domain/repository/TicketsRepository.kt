package com.czyzewski.zendeskhometask.domain.repository

import com.czyzewski.zendeskhometask.domain.OutputData
import com.czyzewski.zendeskhometask.domain.mapping.DataMapper
import com.czyzewski.zendeskhometask.domain.model.TicketResponseModel
import com.czyzewski.zendeskhometask.network.datasource.ITicketsDataSource
import com.czyzewski.zendeskhometask.network.dto.TicketsResponseDto
import javax.inject.Inject

class TicketsRepository @Inject constructor(
    private val mapper: DataMapper<TicketsResponseDto, TicketResponseModel>,
    private val dataSource: ITicketsDataSource
) {
    suspend fun getTickets(viewId: Long, page: Int): OutputData<TicketResponseModel> {
        val data = dataSource.getTickets(viewId, page)
        return mapper.map(data)
    }
}