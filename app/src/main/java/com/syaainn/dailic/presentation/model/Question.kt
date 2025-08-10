package com.syaainn.dailic.presentation.model

import kotlinx.serialization.Serializable

@Serializable
data class Question (
    val id: Long = 0,
    val isScraped: Boolean? = null,
    val content: String = "",
    val options: List<String> = listOf(),
    val answerNum: Int = 0,
    val answerContent: String = ""
)