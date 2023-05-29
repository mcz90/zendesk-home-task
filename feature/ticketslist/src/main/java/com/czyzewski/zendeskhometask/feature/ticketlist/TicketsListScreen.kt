package com.czyzewski.zendeskhometask.feature.ticketlist

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.czyzewski.zendeskhometask.ui.components.ErrorView
import com.czyzewski.zendeskhometask.ui.components.ProgressBar
import com.czyzewski.zendeskhometask.ui.components.TicketCard

@Composable
fun TicketsScreen(
    viewModel: TicketsListViewModel = hiltViewModel()
) {
    when {
        viewModel.isLoading -> ProgressBar()
        viewModel.errorState != null -> ErrorView(errorMessage = viewModel.errorState?.message.orEmpty()) {
            viewModel.handleIntent(TicketListIntent.OnRetryButtonClicked)
        }

        else -> {
            LazyColumn(modifier = Modifier.fillMaxHeight()) {
                items(viewModel.ticketList.size) { index ->
                    TicketCard(
                        id = viewModel.ticketList[index].id,
                        subject = viewModel.ticketList[index].subject,
                        description = viewModel.ticketList[index].description,
                        isExpanded = viewModel.ticketList[index].isExpanded
                    ) {
                        viewModel.handleIntent(TicketListIntent.OnDescriptionClicked(viewModel.ticketList[index].id))
                    }
                }
            }
        }
    }
}

data class TicketUiModel(
    val id: Int,
    val subject: String,
    val description: String,
    val isExpanded: Boolean
)