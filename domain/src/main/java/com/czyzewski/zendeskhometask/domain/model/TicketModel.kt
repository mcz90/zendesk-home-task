package com.czyzewski.zendeskhometask.domain.model

data class TicketModel(
    val id: Int,
    val subject: String,
    val description: String
)

data class TicketResponseModel(
    val nextPage: Int?,
    val previousPage: Int?,
    val count: Int,
    val tickets: List<TicketModel>
)
