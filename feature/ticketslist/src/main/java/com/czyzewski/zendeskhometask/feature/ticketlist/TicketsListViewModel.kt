package com.czyzewski.zendeskhometask.feature.ticketlist

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.czyzewski.zendeskhometask.domain.model.TicketModel
import com.czyzewski.zendeskhometask.domain.usecase.GetTicketsListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ViewId

@Module
@InstallIn(ViewModelComponent::class)
object DetailsModule {
    @Provides
    @ViewId
    @ViewModelScoped
    fun provideViewId(
    ): Long = 360072851132L
}

data class ErrorState(
    val message: String
)

@HiltViewModel
class TicketsListViewModel @Inject constructor(
    @ViewId private val viewId: Long,
    private val useCase: GetTicketsListUseCase
) : ViewModel() {
    private val _ticketListState = mutableStateListOf<TicketModel>()
    val ticketList: List<TicketModel>
        get() = _ticketListState

    private val _isLoading = mutableStateOf(value = true)
    val isLoading: Boolean
        get() = _isLoading.value

    private val _errorState = mutableStateOf<ErrorState?>(value = null)
    val errorState: ErrorState?
        get() = _errorState.value

    fun handleIntent(intent: TicketListIntent) = when (intent) {
        is TicketListIntent.Init -> getTickets(viewId, intent.page)
        TicketListIntent.OnRetryButtonClicked -> getTickets(viewId, 1)
    }

    private fun getTickets(viewId: Long, page: Int) {
        _isLoading.value = true
        _errorState.value = null
        viewModelScope.launch {
            when (val result = useCase.execute(viewId, page)) {
                is GetTicketsListUseCase.TicketsListResult.Success -> {
                    if (_ticketListState.isEmpty() && result.ticketsList.tickets.isEmpty()) {
                        _errorState.value = ErrorState("Tickets list is empty")
                    } else {
                        _ticketListState.clear()
                        _ticketListState.addAll(result.ticketsList.tickets)
                        _errorState.value = null
                    }
                    _isLoading.value = false
                }

                is GetTicketsListUseCase.TicketsListResult.Error -> {
                    _ticketListState.clear()
                    _errorState.value = ErrorState(result.message)
                    _isLoading.value = false
                }
            }
        }
    }
}

sealed class TicketListIntent {
    class Init(val page: Int) : TicketListIntent()
    object OnRetryButtonClicked : TicketListIntent()
}