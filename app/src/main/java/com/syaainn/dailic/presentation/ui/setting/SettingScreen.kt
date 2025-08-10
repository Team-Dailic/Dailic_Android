package com.syaainn.dailic.presentation.ui.setting

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.syaainn.dailic.presentation.ui.dailystudy.DailyStudyScreen
import com.syaainn.dailic.ui.theme.DAILICTheme

@Composable
fun SettingRoute() {
    SettingScreen()
}

@Composable
fun SettingScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
        content = {
            Text(
                text = "설정"
            )
        }
    )
}

@Composable
private fun PreviewSettingScreen() {
    DAILICTheme {
        SettingScreen()
    }
}