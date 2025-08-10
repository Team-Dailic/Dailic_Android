package com.syaainn.dailic.presentation.ui.main

import android.content.Intent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun DailicApp(
    appState: DailicAppState,
    startIntent: Intent
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        content = { innerPadding ->
            DailicNavHost(
                appState = appState,
                modifier = Modifier.padding(innerPadding),
                startIntent = startIntent
            )
        }
    )
}