package com.syaainn.dailic.presentation.ui.main

import kotlinx.serialization.Serializable

@Serializable
sealed interface Route {

    @Serializable
    data object Splash: Route

    @Serializable
    data object Occupation: Route

    @Serializable
    data object License: Route
}