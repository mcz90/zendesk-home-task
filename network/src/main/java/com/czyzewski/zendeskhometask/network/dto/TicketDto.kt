package com.czyzewski.zendeskhometask.network.dto


import com.squareup.moshi.Json

data class TicketDto(
    @Json(name = "allow_attachments")
    val allowAttachments: Boolean,
    @Json(name = "allow_channelback")
    val allowChannelback: Boolean,
    @Json(name = "assignee_id")
    val assigneeId: Any?,
    @Json(name = "brand_id")
    val brandId: Long,
    @Json(name = "collaborator_ids")
    val collaboratorIds: List<Any>,
    @Json(name = "created_at")
    val createdAt: String,
    @Json(name = "custom_fields")
    val customFields: List<CustomFieldDto>,
    @Json(name = "description")
    val description: String,
    @Json(name = "due_at")
    val dueAt: Any?,
    @Json(name = "email_cc_ids")
    val emailCcIds: List<Any>,
    @Json(name = "external_id")
    val externalId: Any?,
    @Json(name = "fields")
    val fields: List<Any>,
    @Json(name = "follower_ids")
    val followerIds: List<Any>,
    @Json(name = "followup_ids")
    val followupIds: List<Any>,
    @Json(name = "forum_topic_id")
    val forumTopicId: Any?,
    @Json(name = "from_messaging_channel")
    val fromMessagingChannel: Boolean,
    @Json(name = "group_id")
    val groupId: Long,
    @Json(name = "has_incidents")
    val hasIncidents: Boolean,
    @Json(name = "id")
    val id: Int,
    @Json(name = "is_public")
    val isPublic: Boolean,
    @Json(name = "organization_id")
    val organizationId: Long,
    @Json(name = "priority")
    val priority: String,
    @Json(name = "problem_id")
    val problemId: Any?,
    @Json(name = "raw_subject")
    val rawSubject: String,
    @Json(name = "recipient")
    val recipient: String?,
    @Json(name = "requester_id")
    val requesterId: Long,
    @Json(name = "satisfaction_rating")
    val satisfactionRating: Any?,
    @Json(name = "sharing_agreement_ids")
    val sharingAgreementIds: List<Any>,
    @Json(name = "status")
    val status: String,
    @Json(name = "subject")
    val subject: String,
    @Json(name = "submitter_id")
    val submitterId: Long,
    @Json(name = "tags")
    val tags: List<Any>,
    @Json(name = "ticket_form_id")
    val ticketFormId: Any?,
    @Json(name = "type")
    val type: String,
    @Json(name = "updated_at")
    val updatedAt: String,
    @Json(name = "url")
    val url: String,
    @Json(name = "via")
    val via: ViaDto
)

data class ToDto(
    @Json(name = "id")
    val id: Int?,
    @Json(name = "title")
    val title: String?
)

data class ViaDto(
    @Json(name = "channel")
    val channel: String,
    @Json(name = "source")
    val source: SourceDto
)

data class SourceDto(
    @Json(name = "from")
    val from: FromDto?,
    @Json(name = "rel")
    val rel: Any?,
    @Json(name = "to")
    val to: ToDto?
)

data class FromDto(
    @Json(name = "id")
    val id: Int?,
    @Json(name = "title")
    val title: String?
)


data class CustomFieldDto(
    @Json(name = "id")
    val id: Int,
    @Json(name = "value")
    val value: String
)
