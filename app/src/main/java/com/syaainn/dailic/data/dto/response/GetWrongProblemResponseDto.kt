package com.syaainn.dailic.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetWrongProblemResponseDto(
    @SerialName("id") val id: Long,
    @SerialName("userId") val userId: Long,
    @SerialName("problemId") val problemId: Long,
    @SerialName("questionText") val questionText: String,
    @SerialName("isCorrect") val isCorrect: Boolean,
    @SerialName("isScraped") val isScraped: Boolean,
    @SerialName("userAnswer") val userAnswer: String,
    @SerialName("isRetried") val isRetried: Boolean,
    @SerialName("answeredAt") val answerAt: String
)
