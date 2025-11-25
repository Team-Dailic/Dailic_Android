package com.syaainn.dailic.presentation.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.LocalTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.isUnspecified
import com.syaainn.dailic.ui.theme.DAILICTheme
import com.syaainn.dailic.ui.theme.DailicTheme

@Composable
fun DailicTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val textStyle = LocalTextStyle.current
    val density = LocalDensity.current

    // lineHeight가 지정되지 않은 경우 대비해서 fontSize 기반으로 계산
    val lineHeightTextUnit = if (textStyle.lineHeight.isUnspecified) {
        textStyle.fontSize * 1.2f // 대충 줄간격 1.2배 정도 가정
    } else {
        textStyle.lineHeight
    }

    // TextUnit(Sp) -> Dp 변환 + 3줄 높이
    val minHeight: Dp = with(density) {
        (lineHeightTextUnit * 3).toDp()
    }

    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        textStyle = textStyle,
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = minHeight)   // ✅ 이제 Dp 타입
            .padding(horizontal = 1.dp)
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewDailicTextField() {
    DAILICTheme {
        DailicTextField(
            value = "",
            onValueChange = {}
        )
    }
}
