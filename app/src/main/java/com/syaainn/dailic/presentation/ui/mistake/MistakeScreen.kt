package com.syaainn.dailic.presentation.ui.mistake

import androidx.compose.foundation.background
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
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import com.syaainn.dailic.R
import com.syaainn.dailic.presentation.ui.component.DailicTopBar
import com.syaainn.dailic.presentation.util.roundedBackgroundWithBorder
import com.syaainn.dailic.ui.theme.DAILICTheme
import com.syaainn.dailic.ui.theme.DailicTheme

@Composable
fun MistakeRoute(
    viewModel: MistakeViewModel = hiltViewModel(),
    navigateToBack: () -> Unit
) {
    val uiState by viewModel.state.collectAsStateWithLifecycle()
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(Unit) {
        viewModel.sideEffect.flowWithLifecycle(lifecycleOwner.lifecycle).collect { sideEffect ->
            when (sideEffect) {
                MistakeContract.SideEffect.NavigateToBack -> navigateToBack()
            }
        }
    }

    MistakeScreen(
        uiState = uiState,
        onBackClick = { viewModel.setEvent(MistakeContract.Event.OnBackClick) }
    )
}

@Composable
fun MistakeScreen(
    uiState: MistakeContract.State,
    onBackClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Top Bar
        DailicTopBar(
            title = "오답노트",
            leadingIcon = R.drawable.img_top_bar_back,
            onLeadingIconClick = onBackClick
        )
        Spacer(modifier = Modifier.height(40.dp))

        // Content Section
        Text(
            text = buildAnnotatedString {
                append("오답노트에 총 ")
                withStyle(style = SpanStyle(color = DailicTheme.colors.subGreen1)) {
                    append("${uiState.mistakeList.size}문제")
                }
                append("가 있어요!")
            },
            modifier = Modifier.padding(start = 4.dp),
            style = DailicTheme.typography.title1Bold
        )
        Spacer(modifier = Modifier.height(10.dp))

        LazyColumn {
            itemsIndexed(uiState.mistakeList) { index, mistake ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp)
                        .roundedBackgroundWithBorder(
                            cornerRadius = 8.dp,
                            backgroundColor = DailicTheme.colors.gray700
                        ),
                    content = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "${mistake.id}. ",
                                modifier = Modifier
                                    .padding(start = 10.dp)
                                    .padding(vertical = 16.dp),
                                color = DailicTheme.colors.gray300,
                                style = DailicTheme.typography.body1Bold
                            )
                            Text(
                                text = mistake.content,
                                modifier = Modifier
                                    .padding(start = 5.dp)
                                    .padding(vertical = 16.dp)
                                    .weight(1f),
                                color = DailicTheme.colors.gray300,
                                overflow = TextOverflow.Ellipsis,
                                maxLines = 2,
                                style = DailicTheme.typography.body2Medium
                            )
                            Icon(
                                imageVector = ImageVector.vectorResource(
                                    id = R.drawable.ic_refresh_24
                                ),
                                contentDescription = null,
                                modifier = Modifier.padding(horizontal = 10.dp),
                                tint = DailicTheme.colors.gray300
                            )
                        }
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewMistakeScreen() {
    DAILICTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = DailicTheme.colors.primaryBeige1)
        ) {
            MistakeScreen(
                uiState = MistakeContract.State(),
                onBackClick = {}
            )
        }
    }
}
