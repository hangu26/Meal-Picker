package kr.baeksuk.mealpicker.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import kr.baeksuk.mealpicker.R
import kr.baeksuk.mealpicker.ui.theme.MealPickerTheme
import kr.baeksuk.mealpicker.ui.theme.pretendard
import kr.baeksuk.mealpicker.util.util.SpaceWidth
import kr.baeksuk.mealpicker.util.util.pxToDpFixedDpi
import kr.baeksuk.mealpicker.util.util.pxToSpFixedDpi

@Composable
fun TopRankFood() {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = pxToDpFixedDpi(px = 83f))
    ) {

        Text(
            modifier = Modifier
                .wrapContentWidth()
                .padding(start = pxToDpFixedDpi(px = 92f)),
            text = stringResource(id = R.string.tx_rank_food), fontFamily = pretendard,
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.Bold, fontSize = pxToSpFixedDpi(px = 60f),
            color = colorResource(id = R.color.black),

            )

        SpaceWidth(spaceSize = 33f)

        Surface(
            modifier = Modifier.wrapContentSize(),
            shape = RoundedCornerShape(pxToDpFixedDpi(px = 150f)),
            color = colorResource(id = R.color.app_color)
        ) {
            Text(
                text = stringResource(id = R.string.tx_hot),
                fontFamily = pretendard,
                fontWeight = FontWeight.Normal,
                color = Color.White,
                fontSize = pxToSpFixedDpi(px = 42f),
                modifier = Modifier.padding(
                    start = pxToDpFixedDpi(px = 26f), end = pxToDpFixedDpi(
                        px = 26f
                    )
                )
            )
        }

    }


}

@Preview
@Composable
fun prevTopRankFood() {

    MealPickerTheme {
        TopRankFood()
    }

}