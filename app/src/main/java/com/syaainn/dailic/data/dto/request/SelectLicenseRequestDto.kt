package com.syaainn.dailic.data.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.io.Serial

@Serializable
data class SelectLicenseRequestDto(
    @SerialName("occupation")
    val occupation: String,
    @SerialName("license")
    val license: String
)