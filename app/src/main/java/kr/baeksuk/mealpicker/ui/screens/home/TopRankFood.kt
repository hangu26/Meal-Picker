package kr.baeksuk.mealpicker.ui.screens.home

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import kr.baeksuk.mealpicker.R
import kr.baeksuk.mealpicker.ui.theme.pretendard
import kr.baeksuk.mealpicker.util.util.pxToDpFixedDpi
import kr.baeksuk.mealpicker.util.util.pxToSpFixedDpi

@Composable
fun TopRankFood(){

    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = pxToDpFixedDpi(px = 83f), start = pxToDpFixedDpi(px = 92f)),
        text = stringResource(id = R.string.tx_rank_food), fontFamily = pretendard,
        textAlign = TextAlign.Start,
        fontWeight = FontWeight.Bold, fontSize = pxToSpFixedDpi(px = 60f),
        color = colorResource(id = R.color.black),

        )

}