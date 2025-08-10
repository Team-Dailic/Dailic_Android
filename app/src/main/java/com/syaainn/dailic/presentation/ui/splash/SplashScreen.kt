package com.syaainn.dailic.presentation.ui.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.syaainn.dailic.R
import com.syaainn.dailic.ui.theme.DAILICTheme
import com.syaainn.dailic.ui.theme.DailicTheme
import kotlinx.coroutines.delay

@Composable
fun SplashRoute(
    navigateToOccupation: () -> Unit
) {
    val systemUiController = rememberSystemUiController()
    val backgroundColor = DailicTheme.colors.primaryBeige1

    LaunchedEffect(Unit) {
        systemUiController.setStatusBarColor(color = backgroundColor)
        delay(2000)
        navigateToOccupation()
    }

    SplashScreen()
}

@Composable
fun SplashScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
        content = {
            Image(
                painter = painterResource(id = R.drawable.img_logo),
                contentDescription = null
            )
            Text(
                text = "꾸준히 학습하는 내 손 안의 자격증, 데일릭",
                modifier = Modifier.padding(top = 150.dp),
                color = DailicTheme.colors.gray800,
                style = DailicTheme.typography.body2Medium
            )
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewSplashScreen() {
    DAILICTheme {
        SplashScreen()
    }
}