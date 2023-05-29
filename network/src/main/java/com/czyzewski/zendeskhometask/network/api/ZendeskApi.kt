package com.czyzewski.zendeskhometask.network.api

import com.czyzewski.zendeskhometask.network.dto.TicketsResponseDto
import com.czyzewski.zendeskhometask.network.networkhandling.NetworkResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ZendeskApi {
    @GET("/api/v2/views/{view_id}/tickets.json")
    suspend fun getTickets(
        @Path("view_id") viewId: Long,
        @Query("page") page: Int,
        @Query("sort_order") sortOrder: String? = null,
        @Query("sort_by") sortBy: String? = null
    ): NetworkResponse<TicketsResponseDto>
}

sealed class SortOrder(val value: String) {
    object Ascending : SortBy("asc")
    object Descending : SortBy("desc")
}

sealed class SortBy(val value: String) {
    object Assigned : SortBy("assigned")
    object Assignee : SortBy("assignee")
    object DueDate : SortBy("due_date")
    object Group : SortBy("group")
    object Id : SortBy("nice_id")
    object Updated : SortBy("updated")
    object AssigneeUpdated : SortBy("updated_assignee")
    object RequesterUpdated : SortBy("updated_requester")
    object Updater : SortBy("updated_by_type")
    object Organization : SortBy("organization")
    object Priority : SortBy("priority")
    object Requested : SortBy("created")
    object Requester : SortBy("requester")
    object RequesterLanguage : SortBy("locale_id")
    object Satisfaction : SortBy("satisfaction_score")
    object Solved : SortBy("solved")
    object StatusCategory : SortBy("status")
    object TicketForm : SortBy("ticket_form")
    object Type : SortBy("type")
    object Brand : SortBy("brand")
    object TicketStatus : SortBy("custom_status_id")
}