package com.syaainn.dailic.presentation.ui.main

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.syaainn.dailic.presentation.ui.license.LicenseRoute
import com.syaainn.dailic.presentation.ui.occupation.OccupationRoute
import com.syaainn.dailic.presentation.ui.splash.SplashRoute
import com.syaainn.dailic.ui.theme.DailicTheme

@Composable
fun DailicNavHost(
    appState: DailicAppState,
    modifier: Modifier = Modifier,
    startIntent: Intent
) {
    val navController = appState.navController

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(DailicTheme.colors.primaryBeige1)
    ) {
        NavHost(
            navController = navController,
            startDestination = appState.startDestination
        ) {
            composable<Route.Splash> {
                SplashRoute(
                    navigateToOccupation = { navController.navigateToOccupation() }
                )
            }

            composable<Route.Occupation> {
                OccupationRoute(
                    navigateToLicense = { navController.navigateToLicense() }
                )
            }

            composable<Route.License> {
                LicenseRoute()
            }
        }
    }
}