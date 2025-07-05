package kr.baeksuk.mealpicker.util.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun pxToDpFixedDpi(px: Float, dpi: Float = 560f): Dp {
    // dp = px × (160 / dpi)
    val dpValue = px * (160f / dpi)
    return dpValue.dp
}

@Composable
fun pxToSpFixedDpi(px: Float, dpi: Float = 560f): TextUnit {
    return (px / (dpi / 160f)).sp
}


