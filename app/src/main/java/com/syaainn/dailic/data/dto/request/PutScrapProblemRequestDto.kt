package com.syaainn.dailic.data.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PutScrapProblemRequestDto(
    @SerialName("userId") val userId: Long,
    @SerialName("problemId") val problemId: Long,
    @SerialName("isScraped") val isScraped: Boolean
)
