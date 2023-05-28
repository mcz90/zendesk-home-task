package com.czyzewski.zendeskhometask.domain.mapping

import com.czyzewski.zendeskhometask.domain.model.TicketModel
import com.czyzewski.zendeskhometask.domain.model.TicketResponseModel
import com.czyzewski.zendeskhometask.network.dto.TicketsResponseDto
import javax.inject.Inject

class TicketsMapper @Inject constructor() : DataMapper<TicketsResponseDto, TicketResponseModel> {
    override fun mapSuccess(responseData: TicketsResponseDto): TicketResponseModel {
        val ticketModels = responseData.tickets.map { ticketDto ->
            TicketModel(
                id = ticketDto.id,
                subject = ticketDto.subject,
                description = ticketDto.description,
            )
        }
        return TicketResponseModel(
            nextPage = responseData.nextPage,
            previousPage = responseData.previousPage,
            count = responseData.count,
            tickets = ticketModels
        )
    }
}