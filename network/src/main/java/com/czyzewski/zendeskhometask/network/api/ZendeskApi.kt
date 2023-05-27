package com.czyzewski.zendeskhometask.network.api

import com.czyzewski.zendeskhometask.network.networkhandling.NetworkResponse
import com.czyzewski.zendeskhometask.network.dto.TicketsResponseDto
import network.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface ZendeskApi {
    @GET("/api/v2/views/{view_id}/tickets.json")
    suspend fun getTickets(@Path("view_id") viewId: Long): NetworkResponse<TicketsResponseDto>
}