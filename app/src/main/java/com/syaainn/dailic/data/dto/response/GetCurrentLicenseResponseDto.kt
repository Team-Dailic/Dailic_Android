package com.syaainn.dailic.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetCurrentLicenseResponseDto(
    @SerialName("occupation") val occupation: String,
    @SerialName("license") val license: String,
    @SerialName("totalQuestion") val totalQuestion: Int,
    @SerialName("solvedQuestion") val solvedQuestion: Int
)