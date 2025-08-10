package com.syaainn.dailic.presentation.ui.score

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import com.syaainn.dailic.R
import com.syaainn.dailic.presentation.model.License
import com.syaainn.dailic.presentation.ui.component.DailicDialog
import com.syaainn.dailic.presentation.ui.component.DailicTopBar
import com.syaainn.dailic.presentation.util.roundedBackgroundWithBorder
import com.syaainn.dailic.ui.theme.DAILICTheme
import com.syaainn.dailic.ui.theme.DailicTheme

@Composable
fun ScoreRoute(
    viewModel: ScoreViewModel = hiltViewModel(),
    score: List<Boolean>,
    navigateToHome: (License) -> Unit,
    navigateToAdditionalStudy: () -> Unit
) {
    val uiState by viewModel.state.collectAsStateWithLifecycle()
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(Unit) {
        viewModel.setEvent(ScoreContract.Event.SetScore(score))
    }

    LaunchedEffect(Unit) {
        viewModel.sideEffect.flowWithLifecycle(lifecycleOwner.lifecycle).collect { sideEffect ->
            when (sideEffect) {
                is ScoreContract.SideEffect.NavigateToHome -> navigateToHome(License.DRIVING)
                is ScoreContract.SideEffect.NavigateToAdditionalStudy -> navigateToAdditionalStudy()
            }
        }
    }

    ScoreScreen(
        uiState = uiState,
        onBackClick = { viewModel.setEvent(ScoreContract.Event.OnBackClick) },
        onConfirmExitDialog = { viewModel.setEvent(ScoreContract.Event.OnConfirmExitDialog) },
        onDismissExitDialog = { viewModel.setEvent(ScoreContract.Event.OnDismissExitDialog) },
        onAdditionalStudyClick = { viewModel.setEvent(ScoreContract.Event.OnAdditionalStudyClick) }
    )
}

@Composable
fun ScoreScreen(
    uiState: ScoreContract.State,
    onBackClick: () -> Unit,
    onConfirmExitDialog: () -> Unit,
    onDismissExitDialog: () -> Unit,
    onAdditionalStudyClick: () -> Unit
) {
    // BackHandler Control for Going Home
    BackHandler {
        onBackClick()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        // Top Bar
        DailicTopBar(
            title = "채점 결과",
            leadingIcon = R.drawable.img_top_bar_back,
            onLeadingIconClick = onBackClick
        )
        Spacer(modifier = Modifier.height(40.dp))

        // Content Section
        Text(
            text = buildAnnotatedString {
                append("${uiState.totalQuestion}문제 중 ")
                withStyle(style = SpanStyle(color = DailicTheme.colors.subGreen1)) {
                    append("${uiState.correctQuestion}문제")
                }
                append("를 맞췄어요!")
            },
            modifier = Modifier.padding(start = 4.dp),
            style = DailicTheme.typography.title1Bold
        )
        Spacer(modifier = Modifier.height(20.dp))
        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            itemsIndexed(uiState.score) { index, isCorrect ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(width = 0.5.dp, color = DailicTheme.colors.gray800)
                        .padding(vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "${index+1}",
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center,
                        style = DailicTheme.typography.body1Bold
                    )
                    VerticalDivider(
                        modifier = Modifier.height(20.dp),
                        thickness = 1.dp,
                        color = DailicTheme.colors.gray800
                    )
                    Text(
                        text = if(isCorrect) "O" else "X",
                        modifier = Modifier.weight(1f),
                        color = if(isCorrect) Color.Blue else Color.Red,
                        textAlign = TextAlign.Center,
                        style = DailicTheme.typography.body1Bold
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(20.dp))

        // Bottom Button Section
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .roundedBackgroundWithBorder(
                    cornerRadius = 8.dp,
                    backgroundColor = DailicTheme.colors.subGreen1,
                )
                .clickable(onClick = onAdditionalStudyClick),
            contentAlignment = Alignment.Center,
            content = {
                Text(
                    text = "추가학습 하러가기",
                    modifier = Modifier.padding(vertical = 12.dp),
                    color = DailicTheme.colors.primaryBeige1,
                    style = DailicTheme.typography.title2Medium
                )
            }
        )
    }

    if (uiState.showExitDialog) {
        DailicDialog(
            titleMassage = "처음으로 돌아갈까요?",
            descriptionMassage = "추가학습을 통해 문제들의 해설을 보고\n궁금한 점을 질문할 수 있어요.",
            confirmOption = "돌아갈래요",
            onConfirm = onConfirmExitDialog,
            dismissOption = "아니요",
            onDismiss = onDismissExitDialog
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewScoreScreen() {
    DAILICTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = DailicTheme.colors.primaryBeige1)
        ) {
            ScoreScreen(
                uiState = ScoreContract.State(
                    score = listOf(true, false, true, false, true,true, false, true, false, true,true, false, true, false, true,true, false, true, false, true),
                    showExitDialog = false
                ),
                onBackClick = {},
                onConfirmExitDialog = {},
                onDismissExitDialog = {},
                onAdditionalStudyClick = {}
            )
        }
    }
}