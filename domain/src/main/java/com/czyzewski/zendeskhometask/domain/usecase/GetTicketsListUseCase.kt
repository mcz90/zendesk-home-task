package com.czyzewski.zendeskhometask.domain.usecase

import com.czyzewski.zendeskhometask.domain.OutputData
import com.czyzewski.zendeskhometask.domain.model.TicketResponseModel
import com.czyzewski.zendeskhometask.domain.repository.TicketsRepository
import javax.inject.Inject

class GetTicketsListUseCase @Inject constructor(
    private val repository: TicketsRepository
) {

    suspend fun execute(viewId: Long): TicketsListResult {
        return when (val result = repository.getTickets(viewId)) {
            is OutputData.Success -> TicketsListResult.Success(result.data)
            is OutputData.Error -> TicketsListResult.Error(result)
            is OutputData.HttpError -> TicketsListResult.Error(result)
            is OutputData.InternetError -> TicketsListResult.Error(result)
            is OutputData.UnknownError -> TicketsListResult.Error(result)
            OutputData.EmptyBody -> TicketsListResult.Error(result)
        }
    }

    sealed interface TicketsListResult {
        class Success(val ticketsList: TicketResponseModel) : TicketsListResult
        class Error(val outputError: OutputData<Any>) : TicketsListResult
    }
}

