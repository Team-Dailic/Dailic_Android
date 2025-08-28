package com.syaainn.dailic.presentation.ui.main

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.syaainn.dailic.presentation.ui.dailystudy.DailyStudyRoute
import com.syaainn.dailic.presentation.ui.home.HomeRoute
import com.syaainn.dailic.presentation.ui.license.LicenseRoute
import com.syaainn.dailic.presentation.ui.mistake.MistakeRoute
import com.syaainn.dailic.presentation.ui.occupation.OccupationRoute
import com.syaainn.dailic.presentation.ui.scrap.ScrapRoute
import com.syaainn.dailic.presentation.ui.setting.SettingRoute
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

            composable<Route.SelectOccupation> {
                OccupationRoute(
                    navigateToLicense = { occupation -> navController.navigateToLicense(occupation) }
                )
            }

            composable<Route.SelectLicense> { backStackEntry ->
                backStackEntry.toRoute<Route.SelectLicense>().apply {
                    LicenseRoute(
                        occupation = occupation,
                        navigateToBack = { navController.popBackStack() },
                        navigateToHome = { navController.navigateToHome() }
                    )
                }
            }

            composable<Route.Home> {
                HomeRoute(
                    navigateToSetting = { navController.navigateToSetting() },
                    navigateToDailyStudy = { navController.navigateToDailyStudy() },
                    navigateToMistake = { navController.navigateToMistake() },
                    navigateToScrap = { navController.navigateToScrap() }
                )
            }

            composable<Route.DailyStudy> {
                DailyStudyRoute(
                    navigateToBack = { navController.popBackStack() },
                    navigateToHome = { navController.navigateToHome() }
                )
            }

            composable<Route.Mistake> {
                MistakeRoute(
                    navigateToBack = { navController.popBackStack() }
                )
            }

            composable<Route.Scrap> {
                ScrapRoute(
                    navigateToBack = { navController.popBackStack() }
                )
            }

            composable<Route.Setting> {
                SettingRoute()
            }
        }
    }
}
