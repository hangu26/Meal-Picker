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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
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
import kr.baeksuk.mealpicker.data.model.Food
import kr.baeksuk.mealpicker.ui.theme.MealPickerTheme
import kr.baeksuk.mealpicker.ui.theme.pretendard
import kr.baeksuk.mealpicker.util.util.pxToDpFixedDpi
import kr.baeksuk.mealpicker.util.util.pxToSpFixedDpi

@Composable
fun RecommendAfterView(recommendedFood: Food,
                       onRetryClick : () -> Unit) {

    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center // 정가운데 정렬!
    ) {


        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(id = R.color.white)),

            ) {

            val (recommendBtn, retryBtn, goodBtn) = createRefs()
            val interactionSource = remember { MutableInteractionSource() }
            /**

            val modifier = Modifier
            .size(pxToDpFixedDpi(px = 695f))
            .constrainAs(recommendBtn) {
            centerHorizontallyTo(parent)
            centerVerticallyTo(parent)
            }
            .clickable(interactionSource = interactionSource,
            indication = null,
            enabled = false,
            onClick = {})

            val mainBtnColor = colorResource(id = R.color.btn_recommend_selected_color)

            val mainBtnIcon = painterResource(id = R.drawable.icon_food)

            val mainBtnText = recommendedFood.name

            val mainBtnTextColor = colorResource(id = R.color.black)

            val isClickable = false

            recommendButton(
            modifier, mainBtnColor, mainBtnIcon, mainBtnText, mainBtnTextColor, isClickable
            ) {}
             **/

            val foodIcon = when(recommendedFood.category){
                "한식" -> painterResource(id = R.drawable.icon_korean_food)
                "중식" -> painterResource(id = R.drawable.icon_chinese_food)
                "일식" -> painterResource(id = R.drawable.icon_japan_food)
                "양식" -> painterResource(id = R.drawable.icon_western_food)
                "퓨전" -> painterResource(id = R.drawable.icon_western_food)
                else ->{
                    painterResource(id = R.drawable.icon_food)
                }
            }

            TopRankFood()

            Column(
                modifier = Modifier
                    .wrapContentSize()
                    .constrainAs(recommendBtn) {
                        centerHorizontallyTo(parent)
                        centerVerticallyTo(parent)
                    },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = stringResource(id = R.string.tx_today_food), fontFamily = pretendard,
                    fontWeight = FontWeight.Bold, fontSize = pxToSpFixedDpi(px = 92f)
                )

                Spacer(modifier = Modifier.height(pxToDpFixedDpi(px = 150f)))

                Text(
                    text = recommendedFood.name, fontFamily = pretendard,
                    fontWeight = FontWeight.Black, fontSize = pxToSpFixedDpi(px = 98f),
                )

                Image(modifier = Modifier
                    .width(pxToDpFixedDpi(px = 677f))
                    .height(pxToDpFixedDpi(px = 677f)),
                    painter = foodIcon,
                    contentDescription = "음식 아이콘"
                )

            }

            val marginTopRetryBtn = pxToDpFixedDpi(px = 298f)

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(retryBtn) {
                        top.linkTo(recommendBtn.bottom, margin = marginTopRetryBtn)
                    }, horizontalArrangement = Arrangement.SpaceEvenly

            ) {

                val retryModifier = Modifier
                    .width(pxToDpFixedDpi(px = 550f))
                    .height(pxToDpFixedDpi(px = 175f))
                    .clickable(
                        interactionSource = interactionSource,
                        indication = rememberRipple(
                            bounded = true, radius = pxToDpFixedDpi(px = 348f)
                        ),

                        ) {

                    }

                val btnRetryColor = colorResource(id = R.color.btn_retry_color)

                val btnRetryIcon = painterResource(id = R.drawable.icon_retry)

                val txRetry = stringResource(id = R.string.tx_retry)

                val txRetryColor = colorResource(id = R.color.tx_retry_color)

                actionButton(
                    retryModifier, btnRetryColor, btnRetryIcon, txRetry, txRetryColor
                ) {
                    onRetryClick()
                }

                val goodModifier = Modifier
                    .width(pxToDpFixedDpi(px = 550f))
                    .height(pxToDpFixedDpi(px = 175f))
                    .clickable(
                        interactionSource = interactionSource,
                        indication = rememberRipple(
                            bounded = true, radius = pxToDpFixedDpi(px = 348f)
                        ),

                        ) {

                    }

                val btnGoodColor = colorResource(id = R.color.btn_recommend_color)

                val btnGoodIcon = painterResource(id = R.drawable.icon_good)

                val txGood = stringResource(id = R.string.tx_good)

                val txGoodColor = Color.White

                actionButton(goodModifier, btnGoodColor, btnGoodIcon, txGood, txGoodColor) {}

            }

        }

        SparkleLoader()

    }

}

@Composable
fun SparkleLoader() {

    val composition by rememberLottieComposition(LottieCompositionSpec.Asset("sparkle.json"))

    val progress by animateLottieCompositionAsState(
        composition, iterations = 2
    )

    LottieAnimation(
        composition = composition, progress = { progress }, modifier = Modifier.size(450.dp)
    )

}


@Preview
@Composable
private fun preview() {
    MealPickerTheme {
        RecommendAfterView(Food()){}
    }
}