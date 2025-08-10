package com.syaainn.dailic.presentation.ui.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Stable
class DailicAppState(
    val navController: NavHostController,
) {
    val startDestination = Route.Splash
}

@Composable
fun rememberDailicAppState(
    navController: NavHostController = rememberNavController(),
): DailicAppState = remember { DailicAppState(navController) }