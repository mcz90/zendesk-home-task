package com.czyzewski.zendeskhometask.feature.ticketlist

import com.czyzewski.zendeskhometask.domain.mapping.QueryExtractor
import com.czyzewski.zendeskhometask.domain.mapping.TicketsMapper
import com.czyzewski.zendeskhometask.domain.model.TicketModel
import com.czyzewski.zendeskhometask.domain.repository.TicketsRepository
import com.czyzewski.zendeskhometask.domain.usecase.GetTicketsListUseCase
import com.czyzewski.zendeskhometask.network.api.ZendeskApi
import com.czyzewski.zendeskhometask.network.datasource.TicketsDataSource
import com.czyzewski.zendeskhometask.network.dto.SourceDto
import com.czyzewski.zendeskhometask.network.dto.TicketDto
import com.czyzewski.zendeskhometask.network.dto.TicketsResponseDto
import com.czyzewski.zendeskhometask.network.dto.ViaDto
import com.czyzewski.zendeskhometask.network.networkhandling.NetworkResponse
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestWatcher
import org.junit.runner.Description


@ExperimentalCoroutinesApi
class TicketsListViewModelTest {
    @ExperimentalCoroutinesApi
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var viewModel: TicketsListViewModel

    private val mockApi: ZendeskApi = mockk()

    @Before
    fun setUp() {
        viewModel = TicketsListViewModel(
            0,
            GetTicketsListUseCase(
                TicketsRepository(
                    TicketsMapper(QueryExtractor()), TicketsDataSource(mockApi)
                )
            )
        )
    }

    @Test
    fun tickets_data_matches_expected_result_on_successful_api_response() = runTest {
        coEvery {
            mockApi.getTickets(
                0L, 1
            )
        } coAnswers { NetworkResponse.Success(expectedResult) }
        viewModel.handleIntent(TicketListIntent.Init(1))
        runCurrent()
        expectedResult.tickets.forEachIndexed { index, expectedTicket ->
            Assert.assertEquals(expectedTicket.id, viewModel.ticketList[index].id)
            Assert.assertEquals(expectedTicket.subject, viewModel.ticketList[index].subject)
            Assert.assertEquals(expectedTicket.description, viewModel.ticketList[index].description)
        }
        Assert.assertEquals(null, viewModel.errorState)
        Assert.assertEquals(false, viewModel.isLoading)
    }

    @Test
    fun error_sets_to_unknown_when_network_responds_with_unknown_error() = runTest {
        coEvery {
            mockApi.getTickets(
                0L, 1
            )
        } coAnswers { NetworkResponse.UnknownError() }
        viewModel.handleIntent(TicketListIntent.Init(1))
        runCurrent()
        Assert.assertEquals(emptyList<TicketModel>(), viewModel.ticketList)
        Assert.assertEquals("Unknown error occurred", viewModel.errorState!!.message)
        Assert.assertEquals(false, viewModel.isLoading)
    }

    @Test
    fun error_sets_to_no_internet_error_when_network_responds_with_internet_error() = runTest {
        coEvery {
            mockApi.getTickets(
                0L, 1
            )
        } coAnswers { NetworkResponse.InternetError() }
        viewModel.handleIntent(TicketListIntent.Init(1))
        runCurrent()
        Assert.assertEquals(emptyList<TicketModel>(), viewModel.ticketList)
        Assert.assertEquals("Internet error occurred", viewModel.errorState!!.message)
        Assert.assertEquals(false, viewModel.isLoading)
    }

    @Test
    fun error_sets_to_http_error_when_network_responds_with_http_error() = runTest {
        coEvery {
            mockApi.getTickets(
                0L, 1
            )
        } coAnswers { NetworkResponse.HttpError(Throwable(), 404) }
        viewModel.handleIntent(TicketListIntent.Init(1))
        runCurrent()
        Assert.assertEquals(emptyList<TicketModel>(), viewModel.ticketList)
        Assert.assertEquals("Http error 404 occurred", viewModel.errorState!!.message)
        Assert.assertEquals(false, viewModel.isLoading)
    }

    @Test
    fun error_sets_to_empty_list_when_network_responds_empty_ticketslist() = runTest {
        coEvery {
            mockApi.getTickets(
                0L, 1
            )
        } coAnswers { NetworkResponse.Success(expectedResult.copy(tickets = emptyList())) }
        viewModel.handleIntent(TicketListIntent.Init(1))
        runCurrent()
        Assert.assertEquals(emptyList<TicketModel>(), viewModel.ticketList)
        Assert.assertEquals("Tickets list is empty", viewModel.errorState!!.message)
        Assert.assertEquals(false, viewModel.isLoading)
    }

    @Test
    fun tickets_list_loads_after_retry_button_click() = runTest {
        coEvery {
            mockApi.getTickets(0L, 1)
        } coAnswers { NetworkResponse.Success(expectedResult) }

        viewModel.handleIntent(TicketListIntent.OnRetryButtonClicked)
        runCurrent()
        expectedResult.tickets.forEachIndexed { index, expectedTicket ->
            Assert.assertEquals(expectedTicket.id, viewModel.ticketList[index].id)
            Assert.assertEquals(expectedTicket.subject, viewModel.ticketList[index].subject)
            Assert.assertEquals(expectedTicket.description, viewModel.ticketList[index].description)
        }
        Assert.assertEquals(null, viewModel.errorState)
        Assert.assertEquals(false, viewModel.isLoading)
    }

    private val expectedResult = TicketsResponseDto(
        nextPage = null,
        previousPage = null,
        count = 20,
        tickets = (0..19).map { index ->
            TicketDto(
                url = "https://z3nmobiletechtest.zendesk.com/api/v2/tickets/211.json",
                id = index,
                subject = "subject$index",
                description = "description$index",
                externalId = null,
                via = ViaDto(
                    channel = "api", source = SourceDto(
                        null, null, null
                    )
                ),
                createdAt = "2018-12-04T12:46:17Z",
                updatedAt = "2018-12-04T12:46:17Z",
                type = "question",
                rawSubject = "Nulla vitae, et varius vel, consectetuer In ligula, ullamcorper Aenean vulputate",
                priority = "low",
                status = "pending",
                recipient = null,
                requesterId = 373497217572,
                submitterId = 373497217572,
                assigneeId = null,
                organizationId = 360177323952,
                groupId = 360003213332,
                collaboratorIds = emptyList(),
                followerIds = emptyList(),
                emailCcIds = emptyList(),
                forumTopicId = null,
                problemId = null,
                hasIncidents = false,
                isPublic = true,
                dueAt = null,
                tags = emptyList(),
                customFields = emptyList(),
                satisfactionRating = null,
                sharingAgreementIds = emptyList(),
                fields = emptyList(),
                followupIds = emptyList(),
                ticketFormId = null,
                brandId = 360001854692,
                allowChannelback = false,
                allowAttachments = true,
                fromMessagingChannel = false
            )
        }
    )
}

@ExperimentalCoroutinesApi
class MainDispatcherRule(val dispatcher: TestDispatcher = StandardTestDispatcher()) :
    TestWatcher() {

    override fun starting(description: Description?) = Dispatchers.setMain(dispatcher)

    override fun finished(description: Description?) = Dispatchers.resetMain()

}