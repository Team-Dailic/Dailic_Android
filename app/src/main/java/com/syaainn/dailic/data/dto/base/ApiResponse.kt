package com.syaainn.dailic.data.dto.base

import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse<T>(
    val status: Int,
    val message: String,
    val data: T,
)