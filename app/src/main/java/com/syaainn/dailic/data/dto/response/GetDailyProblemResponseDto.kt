package com.syaainn.dailic.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetDailyProblemResponseDto(
    @SerialName("id") val id: Long,
    @SerialName("questionText") val question: String,
    @SerialName("options") val options: List<String>,
    @SerialName("correctAnswer") val answer: Int?,
    @SerialName("solution") val solution: String?,
    @SerialName("scraped") val isScraped: Boolean
)
