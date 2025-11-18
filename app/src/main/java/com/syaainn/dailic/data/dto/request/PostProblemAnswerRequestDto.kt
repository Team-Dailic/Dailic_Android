package com.syaainn.dailic.data.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PostProblemAnswerRequestDto(
    @SerialName("userId") val userId: Long,
    @SerialName("problemId") val problemId: Long,
    @SerialName("userAnswer") val userAnswer: String,
    @SerialName("isRetried") val isRetried: Boolean
)
