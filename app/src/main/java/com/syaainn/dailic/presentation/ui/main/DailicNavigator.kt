package com.syaainn.dailic.presentation.ui.main

import androidx.navigation.NavController

fun NavController.navigateToSplash() {
    navigate(
        route = Route.Splash
    )
}

fun NavController.navigateToOccupation() {
    navigate(
        route = Route.Occupation
    )
}

fun NavController.navigateToLicense() {
    navigate(
        route = Route.License
    )

}