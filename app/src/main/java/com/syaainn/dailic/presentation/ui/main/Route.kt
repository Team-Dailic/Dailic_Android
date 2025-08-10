package com.syaainn.dailic.presentation.ui.main

import com.syaainn.dailic.presentation.model.License
import com.syaainn.dailic.presentation.model.Occupation
import kotlinx.serialization.Serializable

@Serializable
sealed interface Route {

    @Serializable
    data object Splash: Route

    @Serializable
    data object SelectOccupation: Route

    @Serializable
    data class SelectLicense(
        val occupation: Occupation
    ): Route

    @Serializable
    data class Home(
        val license: License
    ): Route

    @Serializable
    data object DailyStudy: Route

    @Serializable
    data class Score(
        val score: List<Boolean>
    ): Route

    @Serializable
    data object AdditionalStudy: Route

    @Serializable
    data object Mistake: Route

    @Serializable
    data object Scrap: Route

    @Serializable
    data object Setting: Route
}