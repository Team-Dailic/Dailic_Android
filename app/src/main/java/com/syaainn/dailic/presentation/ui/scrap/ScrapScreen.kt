package com.syaainn.dailic.presentation.ui.scrap

import androidx.compose.foundation.Image
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
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
fun ScrapRoute(
    viewModel: ScrapViewModel = hiltViewModel(),
    navigateToBack: () -> Unit
) {
    val uiState by viewModel.state.collectAsStateWithLifecycle()
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(Unit) {
        viewModel.sideEffect.flowWithLifecycle(lifecycleOwner.lifecycle).collect { sideEffect ->
            when (sideEffect) {
                ScrapContract.SideEffect.NavigateToBack -> navigateToBack()
            }
        }
    }

    ScrapScreen(
        uiState = uiState,
        onBackClick = { viewModel.setEvent(ScrapContract.Event.OnBackClick) }
    )
}

@Composable
fun ScrapScreen(
    uiState: ScrapContract.State,
    onBackClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        // Top Bar
        DailicTopBar(
            title = "스크랩한 문제",
            leadingIcon = R.drawable.img_top_bar_back,
            onLeadingIconClick = onBackClick
        )
        Spacer(modifier = Modifier.height(40.dp))

        // Content Section
        Text(
            text = buildAnnotatedString {
                append("스크랩한 문제가 총 ")
                withStyle(style = SpanStyle(color = DailicTheme.colors.subGreen1)) {
                    append("${uiState.scrapList.size}문제")
                }
                append("가 있어요!")
            },
            modifier = Modifier.padding(start = 4.dp),
            style = DailicTheme.typography.title1Bold
        )
        Spacer(modifier = Modifier.height(10.dp))

        LazyColumn {
            itemsIndexed(uiState.scrapList) { index, scrap ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp)
                        .roundedBackgroundWithBorder(
                            cornerRadius = 8.dp,
                            backgroundColor = DailicTheme.colors.gray700,
                        ),
                    content = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "${scrap.id}. ",
                                modifier = Modifier
                                    .padding(start = 10.dp)
                                    .padding(vertical = 16.dp),
                                color = DailicTheme.colors.gray300,
                                style = DailicTheme.typography.body1Bold
                            )
                            Text(
                                text = scrap.content,
                                modifier = Modifier
                                    .padding(start = 5.dp)
                                    .padding(vertical = 16.dp)
                                    .weight(1f),
                                color = DailicTheme.colors.gray300,
                                overflow = TextOverflow.Ellipsis,
                                maxLines = 2,
                                style = DailicTheme.typography.body2Medium
                            )
                            Image(
                                painter = painterResource(id = R.drawable.img_scrap),
                                contentDescription = null,
                                modifier = Modifier.padding(horizontal = 10.dp),
                                colorFilter = ColorFilter.tint(color = if(scrap.isScraped != false) Color.Yellow else DailicTheme.colors.gray300)
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
private fun PreviewScrapScreen() {
    DAILICTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = DailicTheme.colors.primaryBeige1)
        ) {
            ScrapScreen(
                uiState = ScrapContract.State(),
                onBackClick = {}
            )
        }
    }
}