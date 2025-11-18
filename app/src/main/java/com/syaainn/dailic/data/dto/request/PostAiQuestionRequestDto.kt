package com.syaainn.dailic.data.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PostAiQuestionRequestDto(
    @SerialName("question") val question: String
)