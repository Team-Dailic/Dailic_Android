package com.syaainn.dailic.presentation.ui.additionalstudy

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
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
fun AdditionalStudyRoute(
    viewModel: AdditionalStudyViewModel = hiltViewModel(),
    navigateToHome: (License) -> Unit,
) {
    val uiState by viewModel.state.collectAsStateWithLifecycle()
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(Unit) {
        viewModel.sideEffect.flowWithLifecycle(lifecycleOwner.lifecycle).collect { sideEffect ->
            when (sideEffect) {
                is AdditionalStudyContract.SideEffect.NavigateToHome -> navigateToHome(License.DRIVING)
            }
        }
    }

    AdditionalStudyScreen(
        uiState = uiState,
        onBackClick = { viewModel.setEvent(AdditionalStudyContract.Event.OnBackClick) },
        onConfirmExitDialog = { viewModel.setEvent(AdditionalStudyContract.Event.OnConfirmExitDialog) },
        onDismissExitDialog = { viewModel.setEvent(AdditionalStudyContract.Event.OnDismissExitDialog) },
        onPreviousQuestionClick = { viewModel.setEvent(AdditionalStudyContract.Event.OnPreviousQuestionClick) },
        onNextQuestionClick = { viewModel.setEvent(AdditionalStudyContract.Event.OnNextQuestionClick) },
        onCompleteClick = { viewModel.setEvent(AdditionalStudyContract.Event.OnCompleteClick) },
        onConfirmCompleteDialog = { viewModel.setEvent(AdditionalStudyContract.Event.OnConfirmCompleteDialog) },
        onDismissCompleteDialog = { viewModel.setEvent(AdditionalStudyContract.Event.OnDismissCompleteDialog) },
    )
}

@Composable
fun AdditionalStudyScreen(
    uiState: AdditionalStudyContract.State,
    onBackClick: () -> Unit,
    onConfirmExitDialog: () -> Unit,
    onDismissExitDialog: () -> Unit,
    onPreviousQuestionClick: () -> Unit,
    onNextQuestionClick: () -> Unit,
    onCompleteClick: () -> Unit,
    onConfirmCompleteDialog: () -> Unit,
    onDismissCompleteDialog: () -> Unit,
) {
    // BackHandler Control for Going Home
    BackHandler {
        onBackClick()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Top Bar
        DailicTopBar(
            title = "추가 학습",
            leadingIcon = R.drawable.img_top_bar_back,
            onLeadingIconClick = onBackClick
        )
        Spacer(modifier = Modifier.height(20.dp))

        // Content Section
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(4.dp)),
            verticalAlignment = Alignment.CenterVertically,
            content = {
                Spacer(
                    modifier = Modifier
                        .weight(uiState.progressWeight)
                        .height(10.dp)
                        .background(color = DailicTheme.colors.subGreen1)
                )
                Spacer(
                    modifier = Modifier
                        .weight(1f - uiState.progressWeight)
                        .height(10.dp)
                        .background(color = DailicTheme.colors.gray300)
                )
            }
        )

        // Question Section
        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 24.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    content = {
                        Text(
                            text = "문제 ${uiState.currentQuestionNum}.",
                            color = DailicTheme.colors.gray800,
                            style = DailicTheme.typography.title1Bold
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Image(
                            painter = painterResource(id = R.drawable.img_scrap),
                            contentDescription = null,
                            modifier = Modifier.padding(12.dp),
                            colorFilter = ColorFilter.tint(color = if (uiState.todayQuestions[uiState.currentQuestionNum - 1].isScraped == true) Color.Yellow else DailicTheme.colors.gray300)
                        )
                    }
                )
                Text(
                    text = uiState.todayQuestions[uiState.currentQuestionNum - 1].content,
                    modifier = Modifier.fillMaxWidth(),
                    color = DailicTheme.colors.gray800,
                    lineHeight = 1.5.em,
                    style = DailicTheme.typography.body2Medium
                )
                Spacer(modifier = Modifier.height(20.dp))
                uiState.todayQuestions[uiState.currentQuestionNum - 1].options.forEachIndexed { index, option ->
                    Text(
                        text = "${index + 1}. $option",
                        modifier = Modifier
                            .align(Alignment.Start)
                            .padding(vertical = 4.dp),
                        color = if (uiState.todayQuestions[uiState.currentQuestionNum - 1].answerNum == index + 1)
                            Color.Blue
                        else
                            DailicTheme.colors.gray800,
                        style = DailicTheme.typography.body4Regular
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }

            item {
                HorizontalDivider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp, bottom = 30.dp),
                    thickness = 1.dp,
                    color = DailicTheme.colors.gray300
                )
            }

            item {
                Text(
                    text = "해설",
                    color = DailicTheme.colors.gray800,
                    style = DailicTheme.typography.title1Bold
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = uiState.todayQuestions[uiState.currentQuestionNum-1].answerContent,
                    color = DailicTheme.colors.gray800,
                    lineHeight = 1.5.em,
                    style = DailicTheme.typography.body2Medium
                )
            }
        }


        // Bottom Button Section
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            content = {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .roundedBackgroundWithBorder(
                            cornerRadius = 8.dp,
                            backgroundColor = if (uiState.currentQuestionNum > 1) DailicTheme.colors.gray800 else DailicTheme.colors.gray400)
                        .clickable(enabled = uiState.currentQuestionNum > 1, onClick = onPreviousQuestionClick),
                    contentAlignment = Alignment.Center,
                    content = {
                        Text(
                            text = "이전 문제",
                            modifier = Modifier.padding(vertical = 12.dp),
                            color = DailicTheme.colors.primaryBeige1,
                            style = DailicTheme.typography.body1Bold,
                        )
                    }
                )
                Spacer(modifier = Modifier.width(8.dp))
                if(uiState.currentQuestionNum == 20) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .roundedBackgroundWithBorder(
                                cornerRadius = 8.dp,
                                backgroundColor = DailicTheme.colors.subGreen1)
                            .clickable(onClick = onCompleteClick),
                        contentAlignment = Alignment.Center,
                        content = {
                            Text(
                                text = "완료하기",
                                modifier = Modifier.padding(vertical = 12.dp),
                                color = DailicTheme.colors.primaryBeige1,
                                style = DailicTheme.typography.body1Bold,
                            )
                        }
                    )
                } else {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .roundedBackgroundWithBorder(
                                cornerRadius = 8.dp,
                                backgroundColor = DailicTheme.colors.gray800)
                            .clickable(onClick = onNextQuestionClick),
                        contentAlignment = Alignment.Center,
                        content = {
                            Text(
                                text = "다음 문제",
                                modifier = Modifier.padding(vertical = 12.dp),
                                color = DailicTheme.colors.primaryBeige1,
                                style = DailicTheme.typography.body1Bold,
                            )
                        }
                    )
                }
            }
        )
    }

    if(uiState.showExitDialog) {
        DailicDialog(
            titleMassage = "추가학습을 종료할까요?",
            descriptionMassage = "학습을 종료하고\n메인 화면으로 돌아가요.",
            confirmOption = "종료할래요",
            onConfirm = onConfirmExitDialog,
            dismissOption = "아니요",
            onDismiss = onDismissExitDialog
        )
    }

    if(uiState.showCompleteDialog) {
        DailicDialog(
            titleMassage = "추가학습을 완료할까요?",
            descriptionMassage = "학습을 종료하고\n메인 화면으로 돌아가요.",
            confirmOption = "완료할래요",
            onConfirm = onConfirmCompleteDialog,
            dismissOption = "아니요",
            onDismiss = onDismissCompleteDialog
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewAdditionalStudyScreen() {
    DAILICTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = DailicTheme.colors.primaryBeige1)
        ) {
            AdditionalStudyScreen(
                uiState = AdditionalStudyContract.State(
                    showCompleteDialog = true
                ),
                onBackClick = {},
                onConfirmExitDialog = {},
                onDismissExitDialog = {},
                onPreviousQuestionClick = {},
                onNextQuestionClick = {},
                onCompleteClick = {},
                onConfirmCompleteDialog = {},
                onDismissCompleteDialog = {},
            )
        }
    }
}