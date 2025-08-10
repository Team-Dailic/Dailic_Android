package com.syaainn.dailic.presentation.ui.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.syaainn.dailic.R
import com.syaainn.dailic.ui.theme.DAILICTheme
import com.syaainn.dailic.ui.theme.DailicTheme

@Composable
fun DailicTopBar(
    title: String,
    modifier: Modifier = Modifier,
    @DrawableRes leadingIcon: Int? = null,
    onLeadingIconClick: () -> Unit = {},
    @DrawableRes trailingIcon: Int? = null,
    onTrailingIconClick: () -> Unit = {}
) {
    Box(
        modifier = modifier.fillMaxWidth(),
        content = {
            if (leadingIcon != null) {
                Image(
                    painter = painterResource(id = leadingIcon),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .clickable(onClick = onLeadingIconClick)
                )
            }
            Text(
                text = title,
                modifier = Modifier.align(Alignment.Center),
                color = DailicTheme.colors.gray800,
                style = DailicTheme.typography.head3Bold
            )
            if (trailingIcon != null) {
                Image(
                    painter = painterResource(id = trailingIcon),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .clickable(onClick = onTrailingIconClick)
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewOccupationTopBar() {
    DAILICTheme {
        DailicTopBar(
            title = "직군 선택",
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewLicenseTopBar() {
    DAILICTheme {
        DailicTopBar(
            title = "자격증 선택",
            leadingIcon = R.drawable.img_top_bar_back,
            onLeadingIconClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewTodayStudyTopBar() {
    DAILICTheme {
        DailicTopBar(
            title = "오늘의 학습",
            onLeadingIconClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewTodayStudyResultTopBar() {
    DAILICTheme {
        DailicTopBar(
            title = "채점 결과",
            onLeadingIconClick = {}
        )
    }
}