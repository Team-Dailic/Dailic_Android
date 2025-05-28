package com.syaainn.dailic.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ExampleResponseDto(
    @SerialName("id") val id: String,
    @SerialName("name") val name: String,
)