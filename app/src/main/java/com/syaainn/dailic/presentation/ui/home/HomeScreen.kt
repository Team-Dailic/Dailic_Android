package com.syaainn.dailic.presentation.ui.home

import android.app.Activity
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
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import com.syaainn.dailic.R
import com.syaainn.dailic.presentation.model.License
import com.syaainn.dailic.presentation.model.Occupation
import com.syaainn.dailic.presentation.ui.component.DailicPopupBottomSheet
import com.syaainn.dailic.presentation.ui.component.DailicTopBar
import com.syaainn.dailic.presentation.util.roundedBackgroundWithBorder
import com.syaainn.dailic.ui.theme.DAILICTheme
import com.syaainn.dailic.ui.theme.DailicTheme

@Composable
fun HomeRoute(
    viewModel: HomeViewModel = hiltViewModel(),
    license: License,
    navigateToSetting: () -> Unit,
    navigateToDailyStudy: () -> Unit,
    navigateToMistake: () -> Unit,
    navigateToScrap: () -> Unit
) {
    val uiState by viewModel.state.collectAsStateWithLifecycle()
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(Unit) {
        viewModel.setEvent(HomeContract.Event.SetLicense(license))
    }

    LaunchedEffect(Unit) {
        viewModel.sideEffect.flowWithLifecycle(lifecycleOwner.lifecycle).collect { sideEffect ->
            when (sideEffect) {
                is HomeContract.SideEffect.NavigateToSetting -> navigateToSetting()
                is HomeContract.SideEffect.NavigateToDailyStudy -> navigateToDailyStudy()
                is HomeContract.SideEffect.NavigateToMistake -> navigateToMistake()
                is HomeContract.SideEffect.NavigateToScrap -> navigateToScrap()
            }
        }
    }

    HomeScreen(
        uiState = uiState,
        onLicenseSelectorClick = { viewModel.setEvent(HomeContract.Event.ShowLicenseSelector) },
        onDismissClick = { viewModel.setEvent(HomeContract.Event.DismissLicenseSelector) },
        onOccupationClick = { selectedOccupation -> viewModel.setEvent(HomeContract.Event.OnOccupationClick(selectedOccupation)) },
        onLicenseClick = { selectedLicense -> viewModel.setEvent(HomeContract.Event.OnLicenseClick(selectedLicense)) },
        onChangeLicenseClick = { viewModel.setEvent(HomeContract.Event.ChangeLicense(uiState.selectedLicense!!)) },
        onSettingClick = { viewModel.sendSideEffect(HomeContract.SideEffect.NavigateToSetting) },
        onDailyStudyClick = { viewModel.sendSideEffect(HomeContract.SideEffect.NavigateToDailyStudy) },
        onMistakeClick = { viewModel.sendSideEffect(HomeContract.SideEffect.NavigateToMistake) },
        onScrapClick = { viewModel.sendSideEffect(HomeContract.SideEffect.NavigateToScrap) }
    )
}

@Composable
fun HomeScreen(
    uiState: HomeContract.State,
    onLicenseSelectorClick: () -> Unit,
    onDismissClick: () -> Unit,
    onOccupationClick: (Occupation) -> Unit,
    onLicenseClick: (License) -> Unit,
    onChangeLicenseClick: () -> Unit,
    onSettingClick: () -> Unit,
    onDailyStudyClick: () -> Unit,
    onMistakeClick: () -> Unit,
    onScrapClick: () -> Unit
) {
    // BackHandler Control for Exit App
    var backPressedTime by remember { mutableLongStateOf(0L) }
    val backPressThreshold = 2000
    val context = LocalContext.current

    BackHandler {
        val currentTime = System.currentTimeMillis()
        if (currentTime - backPressedTime <= backPressThreshold) {
            (context as? Activity)?.finish()
        } else {
            backPressedTime = currentTime
        }
    }

    val itemHeight = 48.dp
    val visibleItems = 4
    val listHeight = itemHeight * visibleItems

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Top Bar
        Box(
            modifier = Modifier.fillMaxWidth(),
            content = {
                Column(
                    modifier = Modifier.align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    content = {
                        Text(
                            text = "학습 중인 자격증",
                            color = DailicTheme.colors.gray800,
                            style = DailicTheme.typography.caption1Medium
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.clickable(onClick = onLicenseSelectorClick),
                            content = {
                                Text(
                                    text = uiState.license?.title ?: "알 수 없음",
                                    modifier = Modifier.padding(start = 16.dp),
                                    color = DailicTheme.colors.primaryBlack1,
                                    style = DailicTheme.typography.head3Bold
                                )
                                Image(
                                    painter = painterResource(id = R.drawable.img_down),
                                    contentDescription = null,
                                    modifier = Modifier.padding(start = 4.dp)
                                )
                            }
                        )
                    }
                )
                Image(
                    painter = painterResource(id = R.drawable.img_top_bar_setting),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .clickable(onClick = onSettingClick)
                )
            }
        )

        // Content Section
        Image(
            painter = painterResource(id = R.drawable.img_home_logo),
            contentDescription = null,
            modifier = Modifier.padding(20.dp)
        )

        Text(
            text = "${uiState.license?.title} 문제 완독까지 ${uiState.solved} / ${uiState.total} 문제 (${String.format("%.1f", uiState.progress * 100)}%)",
            modifier = Modifier.align(Alignment.Start),
            color = DailicTheme.colors.gray800,
            overflow = TextOverflow.Ellipsis,
            style = DailicTheme.typography.body1Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
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
        Spacer(modifier = Modifier.height(40.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(8.dp))
                .background(color = DailicTheme.colors.gray700)
                .clickable(onClick = onDailyStudyClick)
                .padding(24.dp),
            content = {
                Text(
                    text = "오늘의 학습 하러가기",
                    color = DailicTheme.colors.primaryBeige1,
                    style = DailicTheme.typography.head3Bold
                )
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            content = {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .clip(shape = RoundedCornerShape(8.dp))
                        .background(color = DailicTheme.colors.gray700)
                        .clickable(onClick = onMistakeClick)
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    content = {
                        Image(
                            painter = painterResource(id = R.drawable.img_home_mistake),
                            contentDescription = null,
                            colorFilter = ColorFilter.tint(color = DailicTheme.colors.gray300)
                        )
                        Text(
                            text = "오답노트",
                            color = DailicTheme.colors.primaryBeige1,
                            style = DailicTheme.typography.body1Bold
                        )
                    }
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .clip(shape = RoundedCornerShape(8.dp))
                        .background(color = DailicTheme.colors.gray700)
                        .clickable(onClick = onScrapClick)
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    content = {
                        Image(
                            painter = painterResource(id = R.drawable.img_home_scrap),
                            contentDescription = null,
                            colorFilter = ColorFilter.tint(color = DailicTheme.colors.gray300)
                        )
                        Text(
                            text = "스크랩",
                            color = DailicTheme.colors.primaryBeige1,
                            style = DailicTheme.typography.body1Bold
                        )
                    }
                )
            }
        )
    }

    // License Selector (BottomSheet)
    if (uiState.showChangeLicenseBottomSheet) {
        DailicPopupBottomSheet(
            content = {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    content = {
                        DailicTopBar(
                            title = "다른 자격증 학습하기",
                            modifier = Modifier.padding(12.dp),
                            trailingIcon = R.drawable.img_x,
                            onTrailingIconClick = onDismissClick
                        )
                        HorizontalDivider(
                            modifier = Modifier.fillMaxWidth(),
                            thickness = 1.dp,
                            color = DailicTheme.colors.gray300
                        )
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(listHeight),
                            content = {
                                LazyColumn(
                                    modifier = Modifier.weight(1f),
                                    content = {
                                        item {
                                            Occupation.entries.forEach { occupation ->
                                                Box(
                                                    modifier = Modifier
                                                        .weight(1f)
                                                        .height(itemHeight)
                                                        .clickable(onClick = { onOccupationClick(occupation) })
                                                        .background(color =
                                                            if (uiState.selectedOccupation == occupation) DailicTheme.colors.black.copy(alpha = 0.2f)
                                                            else Color.Transparent
                                                        )
                                                    ,
                                                    content = {
                                                        Text(
                                                            text = occupation.title,
                                                            modifier = Modifier
                                                                .fillMaxWidth()
                                                                .padding(12.dp),
                                                            color = DailicTheme.colors.primaryBlack1,
                                                            textAlign = TextAlign.Center,
                                                            style = DailicTheme.typography.title2Medium
                                                        )
                                                    }
                                                )
                                            }
                                        }
                                    }
                                )
                                LazyColumn(
                                    modifier = Modifier.weight(1f),
                                    content = {
                                        item {
                                            uiState.selectedOccupation.licenseList.forEach { license ->
                                                Box(
                                                    modifier = Modifier
                                                        .weight(1f)
                                                        .height(itemHeight)
                                                        .clickable(onClick = { onLicenseClick(license) })
                                                        .background(color =
                                                            if (uiState.selectedLicense == license) DailicTheme.colors.black.copy(alpha = 0.2f)
                                                            else Color.Transparent
                                                        ),
                                                    content = {
                                                        Text(
                                                            text = license.title,
                                                            modifier = Modifier
                                                                .fillMaxWidth()
                                                                .padding(12.dp),
                                                            color = DailicTheme.colors.primaryBlack1,
                                                            textAlign = TextAlign.Center,
                                                            style = DailicTheme.typography.title2Medium
                                                        )
                                                    }
                                                )
                                            }
                                        }
                                    }
                                )
                            }
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                                .roundedBackgroundWithBorder(
                                    cornerRadius = 8.dp,
                                    backgroundColor = if(uiState.selectedLicense != null) DailicTheme.colors.gray600 else DailicTheme.colors.gray300,
                                )
                                .clickable(onClick = onChangeLicenseClick),
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
                )
            },
            onDismissRequest = onDismissClick
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewHomeScreen() {
    DAILICTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = DailicTheme.colors.primaryBeige1),
            content = {
                HomeScreen(
                    uiState = HomeContract.State(
                        license = License.DRIVING,
                        showChangeLicenseBottomSheet = false
                    ),
                    onLicenseSelectorClick = {},
                    onDismissClick = {},
                    onOccupationClick = {},
                    onLicenseClick = {},
                    onChangeLicenseClick = {},
                    onSettingClick = {},
                    onDailyStudyClick = {},
                    onMistakeClick = {},
                    onScrapClick = {}
                )
            }
        )
    }
}