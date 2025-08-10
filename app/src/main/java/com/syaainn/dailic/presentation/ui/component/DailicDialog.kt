package com.syaainn.dailic.presentation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.syaainn.dailic.ui.theme.DAILICTheme
import com.syaainn.dailic.ui.theme.DailicTheme

@Composable
fun DailicDialog(
    titleMassage: String,
    descriptionMassage: String,
    confirmOption: String,
    onConfirm: () -> Unit,
    dismissOption: String,
    onDismiss: () -> Unit,
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            dismissOnClickOutside = false,
            usePlatformDefaultWidth = false,
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(DailicTheme.colors.black.copy(alpha = 0.5f))
                .padding(horizontal = 16.dp),
            contentAlignment = Alignment.Center,
            content = {
                Card(
                    modifier = Modifier.wrapContentHeight(),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = DailicTheme.colors.primaryBeige1),
                ) {
                    Column(
                        modifier = Modifier.padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // Title
                        Text(
                            text = titleMassage,
                            modifier = Modifier.padding(vertical = 8.dp),
                            color = DailicTheme.colors.gray800,
                            style = DailicTheme.typography.head3Bold,
                        )
                        // Description
                        Text(
                            text = descriptionMassage,
                            color = DailicTheme.colors.gray400,
                            textAlign = TextAlign.Center,
                            style = DailicTheme.typography.body3Regular,
                        )

                        Spacer(modifier = Modifier.height(24.dp))

                        // Buttons
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            content = {
                                Button(
                                    onClick = onDismiss,
                                    modifier = Modifier
                                        .weight(1f)
                                        .background(
                                            color = DailicTheme.colors.gray400,
                                            shape = RoundedCornerShape(8.dp),
                                        ),
                                    colors = ButtonDefaults.buttonColors(DailicTheme.colors.gray400),
                                ) {
                                    Text(
                                        text = dismissOption,
                                        color = DailicTheme.colors.primaryBeige1,
                                        style = DailicTheme.typography.body1Bold,
                                    )
                                }
                                Spacer(modifier = Modifier.width(8.dp))
                                Button(
                                    onClick = onConfirm,
                                    modifier = Modifier
                                        .weight(1f)
                                        .background(
                                            color = DailicTheme.colors.gray800,
                                            shape = RoundedCornerShape(8.dp),
                                        ),
                                    colors = ButtonDefaults.buttonColors(DailicTheme.colors.gray800),
                                ) {
                                    Text(
                                        text = confirmOption,
                                        color = DailicTheme.colors.primaryBeige1,
                                        style = DailicTheme.typography.body1Bold,
                                    )
                                }
                            }
                        )
                    }
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewDailyStudyExitDialog() {
    DAILICTheme {
        DailicDialog(
            titleMassage = "오늘의 학습을 종료할까요?",
            descriptionMassage = "지금까지 푼 기록은 저장되지 않고\n메인 화면으로 돌아가요.",
            confirmOption = "중료할래요",
            onConfirm = {},
            dismissOption = "아니요",
            onDismiss = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewDailyStudySubmitDialog() {
    DAILICTheme {
        DailicDialog(
            titleMassage = "답안을 제출할까요?",
            descriptionMassage = "정답을 채점하고\n오늘의 학습을 마무리해요.",
            confirmOption = "제출할래요",
            onConfirm = {},
            dismissOption = "아니요",
            onDismiss = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewScoreExitDialog() {
    DAILICTheme {
        DailicDialog(
            titleMassage = "처음으로 돌아갈까요?",
            descriptionMassage = "추가학습을 통해 문제들의 해설을 보고\n궁금한 점을 질문할 수 있어요.",
            confirmOption = "돌아갈래요",
            onConfirm = {},
            dismissOption = "아니요",
            onDismiss = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewAdditionalStudyExitDialog() {
    DAILICTheme {
        DailicDialog(
            titleMassage = "추가학습을 종료할까요?",
            descriptionMassage = "학습을 종료하고\n메인 화면으로 돌아가요.",
            confirmOption = "종료할래요",
            onConfirm = {},
            dismissOption = "아니요",
            onDismiss = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewAdditionalStudyCompleteDialog() {
    DAILICTheme {
        DailicDialog(
            titleMassage = "추가학습을 완료할까요?",
            descriptionMassage = "학습을 종료하고\n메인 화면으로 돌아가요.",
            confirmOption = "완료할래요",
            onConfirm = {},
            dismissOption = "아니요",
            onDismiss = {}
        )
    }
}