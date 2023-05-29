package com.czyzewski.zendeskhometask.network.dto

import com.squareup.moshi.Json

data class TicketsResponseDto(
    @Json(name = "next_page")
    val nextPage: String?,
    @Json(name = "previous_page")
    val previousPage: String?,
    @Json(name = "count")
    val count: Int,
    val tickets: List<TicketDto>
)
