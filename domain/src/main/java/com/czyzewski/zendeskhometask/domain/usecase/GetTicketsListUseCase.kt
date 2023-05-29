package com.czyzewski.zendeskhometask.domain.usecase

import com.czyzewski.zendeskhometask.domain.OutputData
import com.czyzewski.zendeskhometask.domain.model.TicketResponseModel
import com.czyzewski.zendeskhometask.domain.repository.TicketsRepository
import javax.inject.Inject

class GetTicketsListUseCase @Inject constructor(
    private val repository: TicketsRepository
) {

    suspend fun execute(viewId: Long, page: Int): TicketsListResult {
        return when (val result = repository.getTickets(viewId, page)) {
            is OutputData.Success -> TicketsListResult.Success(result.data)
            is OutputData.Error -> TicketsListResult.Error("Error occurred")
            is OutputData.HttpError -> TicketsListResult.Error("Http error ${result.errorCode} occurred")
            is OutputData.InternetError -> TicketsListResult.Error("Internet error occurred")
            is OutputData.UnknownError -> TicketsListResult.Error("Unknown error occurred")
            OutputData.EmptyBody -> TicketsListResult.Error("Empty body")
        }
    }

    sealed interface TicketsListResult {
        class Success(val ticketsList: TicketResponseModel) : TicketsListResult
        class Error(val message: String) : TicketsListResult
    }
}

