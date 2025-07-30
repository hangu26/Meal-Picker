package kr.baeksuk.mealpicker.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kr.baeksuk.mealpicker.R
import kr.baeksuk.mealpicker.ui.theme.MealPickerTheme
import kr.baeksuk.mealpicker.ui.theme.gasoek
import kr.baeksuk.mealpicker.ui.theme.pretendard
import kr.baeksuk.mealpicker.util.util.SpaceHeight
import kr.baeksuk.mealpicker.util.util.pxToDpFixedDpi
import kr.baeksuk.mealpicker.util.util.pxToSpFixedDpi

@Composable
fun homeScreen(

    viewModel: HomeViewModel = hiltViewModel()

) {
    val foods by viewModel.foodList
    val context = LocalContext.current
    val recommendedFood by viewModel.recommendedFood
    val recommendLoading by viewModel.recommendLoading.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.resetState()
        viewModel.loadFoodFromAsset()
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.white)),

        ) {

        val beforeRecommend by viewModel.showTextExclude.collectAsState()
        if (!beforeRecommend) {

            RecommendBeforeView() { viewModel.btnRecommendClicked() }

        } else {

            recommendedFood?.let {

                RecommendAfterView(
                    recommendedFood = it,
                ) { viewModel.btnRecommendClicked() }

            } ?: run {

            }

        }

        if (recommendLoading) {
            RecommendLoadingView(isLoading = true) {

            }
        }

    }
}

/** 메인 버튼(추천 -> 랜덤 음식 이름) **/
@Composable
fun recommendButton(
    modifier: Modifier = Modifier,
    btnColor: Color,
    painter: Painter,
    btnText: String,
    btnTextColor: Color,
    isClickable: Boolean,
    onClick: () -> Unit
) {

    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(pxToDpFixedDpi(px = 48f)),
        color = btnColor,
//        onClick = onClick,
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth(),
        ) {
            /**
            Text(
            text = btnText,
            fontFamily = gasoek,
            fontWeight = FontWeight.Normal,
            fontSize = pxToSpFixedDpi(px = 90f),
            color = btnTextColor
            )
             **/

            SpaceHeight(spaceSize = 72f)

            Text(
                text = stringResource(id = R.string.tx_btn_recommend_01),
                color = Color.White,
                fontFamily = pretendard,
                fontWeight = FontWeight.Bold,
                fontSize = pxToSpFixedDpi(px = 60f)
            )

            SpaceHeight(spaceSize = 23f)

            Text(
                text = stringResource(id = R.string.tx_btn_recommend_02),
                color = Color.White,
                fontFamily = pretendard,
                fontWeight = FontWeight.Normal,
                fontSize = pxToSpFixedDpi(px = 42f)
            )

            SpaceHeight(spaceSize = 47f)

            val interactionSource = remember { MutableInteractionSource() }

            Surface(
                modifier = Modifier
                    .clickable(
                        enabled = true,
                        interactionSource = interactionSource,
                        indication = rememberRipple(
                            bounded = true, radius = pxToDpFixedDpi(px = 36f)
                        ),

                        ) { }
                    .width(pxToDpFixedDpi(px = 572f))
                    .height(pxToDpFixedDpi(px = 144f)),
                shape = RoundedCornerShape(pxToDpFixedDpi(px = 36f)),
                onClick = onClick,
            ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                ) {

                    Text(
                        text = stringResource(id = R.string.tx_today_recommend_food),
                        fontFamily = pretendard,
                        fontWeight = FontWeight.Bold,
                        fontSize = pxToSpFixedDpi(px = 48f),
                        color = colorResource(id = R.color.app_color)
                    )

                }

            }

            SpaceHeight(spaceSize = 72f)

        }

    }

}

@Composable
fun btnRecommendAnimLoader(onClick: () -> Unit) {
    val composition by rememberLottieComposition(LottieCompositionSpec.Asset("btn_recommend.json"))

    val progress by animateLottieCompositionAsState(
        composition = composition, iterations = Int.MAX_VALUE
    )

    LottieAnimation(composition = composition, progress = { progress }, modifier = Modifier
        .size(
            pxToDpFixedDpi(px = 695f)
        )
        .background(colorResource(id = R.color.white))
        .clickable { onClick() })
}

/** 다시하기, 좋아요 버튼 ui **/
@Composable
fun actionButton(
    modifier: Modifier,
    btnColor: Color,
    btnIcon: Painter?,
    txButton: String,
    txColor: Color,
    onClick: () -> Unit
) {

    Surface(
        modifier = modifier.clickable { onClick() },
        shape = RoundedCornerShape(pxToDpFixedDpi(px = 90f)),
        color = btnColor,
        tonalElevation = 15.dp,
        shadowElevation = 20.dp
    ) {

        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {

            if (btnIcon != null) {
                Image(
                    modifier = Modifier
                        .width(pxToDpFixedDpi(px = 76f))
                        .height(pxToDpFixedDpi(px = 76f)),
                    painter = btnIcon,
                    contentDescription = "다시 하기 버튼"
                )
            }

            Spacer(modifier = Modifier.width(pxToDpFixedDpi(24f)))

            Text(
                text = txButton,
                fontFamily = pretendard,
                fontWeight = FontWeight.Normal,
                fontSize = pxToSpFixedDpi(px = 65f),
                color = txColor
            )

        }

    }
}

@Composable
private fun previewHomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.white)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {

        RecommendBeforeView() { }

    }
}

@Preview
@Composable
private fun previewHome() {

    MealPickerTheme {
        previewHomeScreen()
    }

}