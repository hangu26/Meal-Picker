package kr.baeksuk.mealpicker.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import kr.baeksuk.mealpicker.R
import kr.baeksuk.mealpicker.ui.theme.MealPickerTheme
import kr.baeksuk.mealpicker.ui.theme.pretendard
import kr.baeksuk.mealpicker.util.util.pxToDpFixedDpi
import kr.baeksuk.mealpicker.util.util.pxToSpFixedDpi

@Composable
fun RecommendLoadingView(
    isLoading: Boolean,
    onClick: () -> Unit
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = pxToDpFixedDpi(px = 60f))
    ) {

        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.8f))
                    .clickable(enabled = true, onClick = {})
                    .padding(bottom = 125.dp),
                contentAlignment = Alignment.Center

            ) {

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(0.dp)
                ) {
                    AnimLoader()

                    Text(
                        text = stringResource(id = R.string.tx_recommend_loading),
                        color = colorResource(
                            id = R.color.white
                        ),
                        fontFamily = pretendard,
                        fontSize = pxToSpFixedDpi(px = 75f),
                        fontWeight = FontWeight.Bold
                    )
                }


            }
        }

    }

}

@Composable
fun AnimLoader() {

    val composition by rememberLottieComposition(LottieCompositionSpec.Asset("food_prepare.json"))

    val progress by animateLottieCompositionAsState(
        composition,
        iterations = Int.MAX_VALUE
    )

    LottieAnimation(
        composition = composition, progress = { progress },
        modifier = Modifier.size(300.dp)
    )

}

@Preview
@Composable
fun previewLoading() {
    MealPickerTheme {
        RecommendLoadingView(false) {}
    }
}