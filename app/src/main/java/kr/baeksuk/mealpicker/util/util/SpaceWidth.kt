package kr.baeksuk.mealpicker.util.util

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SpaceWidth(spaceSize: Float) {

    Spacer(
        modifier = Modifier
            .width(pxToDpFixedDpi(px = spaceSize))

    )

}