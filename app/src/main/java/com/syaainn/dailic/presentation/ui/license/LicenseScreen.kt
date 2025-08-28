package com.syaainn.dailic.presentation.ui.license

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import com.syaainn.dailic.R
import com.syaainn.dailic.presentation.model.License
import com.syaainn.dailic.presentation.model.Occupation
import com.syaainn.dailic.presentation.ui.component.DailicTopBar
import com.syaainn.dailic.presentation.util.roundedBackgroundWithBorder
import com.syaainn.dailic.ui.theme.DAILICTheme
import com.syaainn.dailic.ui.theme.DailicTheme

@Composable
fun LicenseRoute(
    viewModel: LicenseViewModel = hiltViewModel(),
    occupation: Occupation,
    navigateToBack: () -> Unit,
    navigateToHome: () -> Unit,
) {
    val uiState by viewModel.state.collectAsStateWithLifecycle()
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(Unit) {
        viewModel.sideEffect.flowWithLifecycle(lifecycleOwner.lifecycle).collect { sideEffect ->
            when (sideEffect) {
                LicenseContract.SideEffect.NavigateToBack -> navigateToBack()
                LicenseContract.SideEffect.NavigateToHome -> navigateToHome()
            }
        }
    }

    LicenseScreen(
        uiState = uiState,
        occupation = occupation,
        onBackClick = { viewModel.sendSideEffect(LicenseContract.SideEffect.NavigateToBack) },
        onLicenseClick = { license -> viewModel.setEvent(LicenseContract.Event.OnLicenseClick(license)) },
        onNextClick = { viewModel.setEvent(LicenseContract.Event.OnCompleteClick(occupation)) }
    )
}

@Composable
fun LicenseScreen(
    uiState: LicenseContract.State,
    occupation: Occupation,
    onBackClick: () -> Unit,
    onLicenseClick: (License) -> Unit,
    onNextClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Top Bar
        DailicTopBar(
            title = "자격증 선택",
            leadingIcon = R.drawable.img_top_bar_back,
            onLeadingIconClick = onBackClick
        )
        Spacer(modifier = Modifier.height(80.dp))

        // Heading Section
        Text(
            text = "이런 자격증은 어때요?",
            modifier = Modifier.padding(bottom = 8.dp),
            color = DailicTheme.colors.gray800,
            style = DailicTheme.typography.head2Bold
        )
        Text(
            text = "나중에도 변경할 수 있어요!",
            color = DailicTheme.colors.gray500,
            style = DailicTheme.typography.body2Medium
        )
        Spacer(modifier = Modifier.height(40.dp))

        // Content Section
        occupation.licenseList.forEach { license ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(onClick = { onLicenseClick(license) })
                    .roundedBackgroundWithBorder(
                        cornerRadius = 8.dp,
                        backgroundColor = DailicTheme.colors.gray300,
                        borderColor = if (uiState.selectedLicense == license) DailicTheme.colors.gray800 else DailicTheme.colors.gray300,
                        borderWidth = if (uiState.selectedLicense == license) 1.dp else 0.dp
                    )
                    .padding(horizontal = 20.dp),
                content = {
                    Column(
                        modifier = Modifier.padding(vertical = 12.dp),
                        content = {
                            Text(
                                text = license.title,
                                color = DailicTheme.colors.primaryBlack1,
                                style = DailicTheme.typography.title2Medium
                            )
                            Spacer(modifier = Modifier.padding(2.dp))
                            Text(
                                text = license.description,
                                color = DailicTheme.colors.gray500,
                                style = DailicTheme.typography.caption1Medium
                            )
                        }
                    )
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
        Spacer(modifier = Modifier.weight(1f))

        // Bottom Button Section
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .roundedBackgroundWithBorder(
                    cornerRadius = 8.dp,
                    backgroundColor = if (uiState.selectedLicense != null) DailicTheme.colors.gray800 else DailicTheme.colors.gray400)
                .clickable(enabled = uiState.selectedLicense != null, onClick = onNextClick),
            contentAlignment = Alignment.Center,
            content = {
                Text(
                    text = "다음",
                    modifier = Modifier.padding(vertical = 12.dp),
                    color = DailicTheme.colors.white,
                    style = DailicTheme.typography.title2Medium
                )
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewLicenseScreen() {
    DAILICTheme {
        LicenseScreen(
            uiState = LicenseContract.State(),
            occupation = Occupation.COMMON,
            onBackClick = {},
            onLicenseClick = {},
            onNextClick = {}
        )
    }
}