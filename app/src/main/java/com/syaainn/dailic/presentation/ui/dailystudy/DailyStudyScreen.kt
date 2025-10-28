package com.syaainn.dailic.presentation.ui.dailystudy

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
import com.syaainn.dailic.presentation.ui.component.DailicDialog
import com.syaainn.dailic.presentation.ui.component.DailicTextField
import com.syaainn.dailic.presentation.ui.component.DailicTopBar
import com.syaainn.dailic.presentation.util.roundedBackgroundWithBorder
import com.syaainn.dailic.ui.theme.DAILICTheme
import com.syaainn.dailic.ui.theme.DailicTheme

@Composable
fun DailyStudyRoute(
    viewModel: DailyStudyViewModel = hiltViewModel(),
    navigateToBack: () -> Unit,
    navigateToHome: () -> Unit,
) {
    val uiState by viewModel.state.collectAsStateWithLifecycle()
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(Unit) {
        viewModel.sideEffect.flowWithLifecycle(lifecycleOwner.lifecycle).collect { sideEffect ->
            when (sideEffect) {
                is DailyStudyContract.SideEffect.NavigateToBack -> navigateToBack()
                is DailyStudyContract.SideEffect.NavigateToHome -> navigateToHome()
            }
        }
    }

    DailyStudyScreen(
        uiState = uiState,
        onBackClick = { viewModel.setEvent(DailyStudyContract.Event.OnBackClick) },
        onConfirmExitDialog = { viewModel.setEvent(DailyStudyContract.Event.OnConfirmExitDialog) },
        onDismissExitDialog = { viewModel.setEvent(DailyStudyContract.Event.OnDismissExitDialog) },
        onAnswerClick = { optionNum ->
            viewModel.setEvent(
                DailyStudyContract.Event.OnAnswerClick(
                    optionNum
                )
            )
        },
        onSubmitClick = { viewModel.setEvent(DailyStudyContract.Event.OnSubmitClick) },
        onAiQuestionChange = { newValue -> viewModel.setEvent(DailyStudyContract.Event.OnAiQuestionChange(newValue)) },
        onNextQuestionClick = { viewModel.setEvent(DailyStudyContract.Event.OnNextQuestionClick) },
        onFinishClick = { viewModel.setEvent(DailyStudyContract.Event.OnFinishClick) },
        onConfirmFinishDialog = { viewModel.setEvent(DailyStudyContract.Event.OnConfirmFinishDialog) },
        onDismissFinishDialog = { viewModel.setEvent(DailyStudyContract.Event.OnDismissFinishDialog) }
    )
}

@Composable
private fun DailyStudyScreen(
    uiState: DailyStudyContract.State,
    onBackClick: () -> Unit,
    onConfirmExitDialog: () -> Unit,
    onDismissExitDialog: () -> Unit,
    onAnswerClick: (Int) -> Unit,
    onSubmitClick: () -> Unit,
    onAiQuestionChange: (String) -> Unit,
    onNextQuestionClick: () -> Unit,
    onFinishClick: () -> Unit,
    onConfirmFinishDialog: () -> Unit,
    onDismissFinishDialog: () -> Unit,
) {
    // Back Handler Control for Go Home
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
            title = "오늘의 학습",
            leadingIcon = R.drawable.img_top_bar_back,
            onLeadingIconClick = onBackClick
        )

        // Progress Bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp)
                .clip(shape = RoundedCornerShape(4.dp)),
            verticalAlignment = Alignment.CenterVertically,
            content = {
                Spacer(
                    modifier = Modifier
                        .weight(uiState.progressWeight)
                        .height(10.dp)
                        .background(color = DailicTheme.colors.primaryGreen1)
                )
                Spacer(
                    modifier = Modifier
                        .weight(1f - uiState.progressWeight)
                        .height(10.dp)
                        .background(color = DailicTheme.colors.gray300)
                )
            }
        )

        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            item {
                // Question Section
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp),
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
                            .padding(vertical = 4.dp)
                            .clickable(
                                enabled = uiState.dailyStudyState == DailyStudyState.IDLE,
                                onClick = { onAnswerClick(index + 1) }
                            ),
                        color = when {
                            uiState.selectedAnswer == index + 1 -> DailicTheme.colors.subGreen1
                            uiState.dailyStudyState != DailyStudyState.IDLE &&
                                    uiState.todayQuestions[uiState.currentQuestionNum - 1].answerNum == index + 1 -> Color.Blue

                            else -> DailicTheme.colors.gray800
                        },
                        style = DailicTheme.typography.body4Regular
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }

            item {
                if (uiState.dailyStudyState != DailyStudyState.IDLE) {
                    HorizontalDivider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 30.dp),
                        thickness = 1.dp,
                        color = DailicTheme.colors.gray400
                    )
                    // Answer Section
                    Text(
                        text = "[ 해설 ]",
                        modifier = Modifier
                            .align(Alignment.Start)
                            .padding(bottom = 10.dp),
                        color = DailicTheme.colors.gray700,
                        style = DailicTheme.typography.title2Medium
                    )
                    Text(
                        text = uiState.todayQuestions[uiState.currentQuestionNum - 1].answerContent,
                        modifier = Modifier.padding(bottom = 20.dp),
                        color = DailicTheme.colors.gray600,
                        lineHeight = 1.5.em,
                        style = DailicTheme.typography.body4Regular
                    )
                    // Ai Question Section
                    Column {
                        Text(
                            text = "[ AI에게 질문하기 ]",
                            modifier = Modifier
                                .align(Alignment.Start)
                                .padding(bottom = 10.dp),
                            color = DailicTheme.colors.gray700,
                            style = DailicTheme.typography.title2Medium
                        )
                        DailicTextField(
                            value = uiState.aiQuestion,
                            onValueChange = onAiQuestionChange,
                            modifier = Modifier.padding(vertical = 4.dp)
                        )
                        Box(
                            modifier = Modifier
                                .align(Alignment.End)
                                .roundedBackgroundWithBorder(
                                    cornerRadius = 8.dp,
                                    backgroundColor = if (uiState.aiQuestion.isNotBlank()) DailicTheme.colors.gray800 else DailicTheme.colors.gray300
                                )
                                .clickable(
                                    enabled = uiState.aiQuestion.isNotBlank(),
                                    onClick = { }
                                ),
                            contentAlignment = Alignment.Center,
                            content = {
                                Text(
                                    text = "전송하기",
                                    modifier = Modifier.padding(12.dp),
                                    color = DailicTheme.colors.primaryBeige1,
                                    style = DailicTheme.typography.body1Bold,
                                )
                            }
                        )
                    }
                }
            }
        }

        // Bottom Button Section
        when (uiState.dailyStudyState) {
            DailyStudyState.IDLE -> {
                DailyStudyBottomButton(
                    text = "정답제출",
                    enabled = uiState.selectedAnswer != null,
                    onClick = onSubmitClick,
                    modifier = Modifier
                )
            }

            DailyStudyState.SUBMIT -> {
                DailyStudyBottomButton(
                    text = "다음문제",
                    enabled = true,
                    onClick = onNextQuestionClick,
                    modifier = Modifier
                )
            }

            DailyStudyState.FINIAL_QUESTION -> {
                DailyStudyBottomButton(
                    text = "완료하기",
                    enabled = uiState.selectedAnswer != null,
                    onClick = onFinishClick,
                    modifier = Modifier
                )
            }
        }
    }

    if (uiState.showExitDialog) {
        DailicDialog(
            titleMassage = "오늘의 학습을 종료할까요?",
            descriptionMassage = "지금까지 푼 기록은 저장되지 않고\n메인 화면으로 돌아가요.",
            confirmOption = "중료할래요",
            onConfirm = onConfirmExitDialog,
            dismissOption = "아니요",
            onDismiss = onDismissExitDialog
        )
    }

    if (uiState.showFinishDialog) {
        DailicDialog(
            titleMassage = "답안을 제출할까요?",
            descriptionMassage = "정답을 채점하고\n오늘의 학습을 마무리해요.",
            confirmOption = "제출할래요",
            onConfirm = onConfirmFinishDialog,
            dismissOption = "아니요",
            onDismiss = onDismissFinishDialog
        )
    }
}

@Composable
private fun DailyStudyBottomButton(
    text: String,
    enabled: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .roundedBackgroundWithBorder(
                cornerRadius = 8.dp,
                backgroundColor = if (enabled) DailicTheme.colors.gray800 else DailicTheme.colors.gray300
            )
            .clickable(
                enabled = enabled,
                onClick = onClick
            ),
        contentAlignment = Alignment.Center,
        content = {
            Text(
                text = text,
                modifier = Modifier.padding(vertical = 12.dp),
                color = DailicTheme.colors.primaryBeige1,
                style = DailicTheme.typography.body1Bold,
            )
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewDailyStudyScreen() {
    DAILICTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = DailicTheme.colors.primaryBeige1),
        ) {
            DailyStudyScreen(
                uiState = DailyStudyContract.State(
                    dailyStudyState = DailyStudyState.SUBMIT
                ),
                onBackClick = {},
                onConfirmExitDialog = {},
                onDismissExitDialog = {},
                onAnswerClick = {},
                onSubmitClick = {},
                onAiQuestionChange = { _ -> },
                onNextQuestionClick = {},
                onFinishClick = {},
                onConfirmFinishDialog = {},
                onDismissFinishDialog = {}
            )
        }
    }
}