package com.syaainn.dailic.presentation.model

import kotlinx.serialization.Serializable

@Serializable
data class Problem(
    val id: Long = 0,
    val content: String = "",
    val options: List<String> = listOf(),
    val answerNum: Int = 0,
    val solution: String? = "문제의 해설이 존재하지 않습니다.",
    val isScraped: Boolean = false
)
