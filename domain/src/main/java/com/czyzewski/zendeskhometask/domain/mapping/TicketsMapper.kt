package com.czyzewski.zendeskhometask.domain.mapping

import com.czyzewski.zendeskhometask.domain.model.TicketModel
import com.czyzewski.zendeskhometask.domain.model.TicketResponseModel
import com.czyzewski.zendeskhometask.network.dto.TicketsResponseDto
import javax.inject.Inject

class TicketsMapper @Inject constructor(
    private val extractor: QueryExtractor<Int>
) : DataMapper<TicketsResponseDto, TicketResponseModel> {
    override fun mapSuccess(responseData: TicketsResponseDto): TicketResponseModel {
        val ticketModels = responseData.tickets.map { ticketDto ->
            TicketModel(
                id = ticketDto.id,
                subject = ticketDto.subject,
                description = ticketDto.description,
            )
        }
        return TicketResponseModel(
            nextPage = extractor.extract(responseData.nextPage, "page"),
            previousPage = extractor.extract(responseData.previousPage, "page"),
            count = responseData.count,
            tickets = ticketModels
        )
    }
}