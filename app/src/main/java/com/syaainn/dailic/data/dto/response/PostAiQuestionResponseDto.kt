package com.syaainn.dailic.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PostAiQuestionResponseDto(
    @SerialName("answer") val answer: String
)