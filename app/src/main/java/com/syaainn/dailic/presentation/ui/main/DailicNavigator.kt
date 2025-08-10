package com.syaainn.dailic.presentation.ui.main

import androidx.navigation.NavController
import com.syaainn.dailic.presentation.model.License
import com.syaainn.dailic.presentation.model.Occupation

fun NavController.navigateToSplash() {
    navigate(
        route = Route.Splash
    )
}

fun NavController.navigateToOccupation() {
    navigate(
        route = Route.SelectOccupation
    )
}

fun NavController.navigateToLicense(occupation: Occupation) {
    navigate(
        route = Route.SelectLicense(occupation = occupation)
    )
}

fun NavController.navigateToHome(license: License) {
    navigate(
        route = Route.Home(license = license)
    )
}

fun NavController.navigateToDailyStudy() {
    navigate(
        route = Route.DailyStudy
    )
}

fun NavController.navigateToScore(score: List<Boolean>) {
    navigate(
        route = Route.Score(score = score)
    )

}

fun NavController.navigateToAdditionalStudy() {
    navigate(
        route = Route.AdditionalStudy
    )
}

fun NavController.navigateToMistake() {
    navigate(
        route = Route.Mistake
    )
}

fun NavController.navigateToScrap() {
    navigate(
        route = Route.Scrap
    )
}

fun NavController.navigateToSetting() {
    navigate(
        route = Route.Setting
    )
}

