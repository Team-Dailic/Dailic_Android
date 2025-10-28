package com.syaainn.dailic.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SelectLicenseResponseDto(
    @SerialName("userId") val userId: Long,
    @SerialName("occupation") val occupation: String,
    @SerialName("license") val license: String
)