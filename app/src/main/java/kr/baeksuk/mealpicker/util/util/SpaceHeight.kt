package kr.baeksuk.mealpicker.util.util

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SpaceHeight(spaceSize: Float) {

    Spacer(
        modifier = Modifier
            .height(pxToDpFixedDpi(px = spaceSize))
            .fillMaxWidth()
    )

}