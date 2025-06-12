package com.syaainn.dailic.presentation.ui.occupation

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
import com.syaainn.dailic.presentation.model.Occupation
import com.syaainn.dailic.presentation.ui.component.DailicTopBar
import com.syaainn.dailic.presentation.util.roundedBackgroundWithBorder
import com.syaainn.dailic.ui.theme.DAILICTheme
import com.syaainn.dailic.ui.theme.DailicTheme

@Composable
fun OccupationRoute(
    viewModel: OccupationViewModel = hiltViewModel(),
    navigateToLicense: () -> Unit
) {
    val uiState by viewModel.state.collectAsStateWithLifecycle()
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(Unit) {
        viewModel.sideEffect.flowWithLifecycle(lifecycleOwner.lifecycle).collect { sideEffect ->
            when (sideEffect) {
                OccupationContract.SideEffect.NavigateToBack -> {}
                OccupationContract.SideEffect.NavigateToLicense -> navigateToLicense()
            }
        }
    }

    OccupationScreen(
        uiState = uiState,
        onOccupationClick = { occupation ->
            viewModel.setEvent(
                OccupationContract.Event.OnOccupationClick(
                    occupation
                )
            )
        },
        onNextClick = { viewModel.sendSideEffect(OccupationContract.SideEffect.NavigateToLicense) }
    )
}

@Composable
fun OccupationScreen(
    uiState: OccupationContract.State,
    onOccupationClick: (Occupation) -> Unit,
    onNextClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        DailicTopBar(
            title = "직군 선택",
            onLeadingIconClick = {}
        )
        Spacer(modifier = Modifier.height(80.dp))

        // Heading Section
        Text(
            text = "학습을 원하는 직군을 선택해주세요.",
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
        Occupation.entries.forEach { occupation ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(onClick = { onOccupationClick(occupation) })
                    .roundedBackgroundWithBorder(
                        cornerRadius = 8.dp,
                        backgroundColor = DailicTheme.colors.gray300,
                        borderColor = if (uiState.selectedOccupation == occupation) DailicTheme.colors.gray800 else DailicTheme.colors.gray300,
                        borderWidth = if (uiState.selectedOccupation == occupation) 1.dp else 0.dp
                    )
                    .padding(horizontal = 20.dp),
                content = {
                    Text(
                        text = occupation.title,
                        modifier = Modifier.padding(vertical = 12.dp),
                        color = DailicTheme.colors.primaryBlack1,
                        style = DailicTheme.typography.title2Medium
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
                    backgroundColor = DailicTheme.colors.gray600,
                )
                .clickable(onClick = onNextClick),
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
private fun PreviewOccupationScreen() {
    DAILICTheme {
        OccupationScreen(
            uiState = OccupationContract.State(),
            onOccupationClick = {},
            onNextClick = {}
        )
    }
}