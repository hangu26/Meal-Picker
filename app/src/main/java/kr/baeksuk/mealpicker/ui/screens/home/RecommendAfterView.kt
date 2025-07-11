package kr.baeksuk.mealpicker.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import kr.baeksuk.mealpicker.R
import kr.baeksuk.mealpicker.data.model.Food
import kr.baeksuk.mealpicker.ui.theme.MealPickerTheme
import kr.baeksuk.mealpicker.util.util.pxToDpFixedDpi

@Composable
fun RecommendAfterView(recommendedFood: Food) {

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.white)),

        ) {
        val (recommendBtn, retryBtn, goodBtn) = createRefs()
        val interactionSource = remember { MutableInteractionSource() }

        val modifier = Modifier
            .size(pxToDpFixedDpi(px = 695f))
            .constrainAs(recommendBtn) {
                centerHorizontallyTo(parent)
                centerVerticallyTo(parent)
            }
            .clickable(
                interactionSource = interactionSource,
                indication = rememberRipple(
                    bounded = true, radius = pxToDpFixedDpi(px = 348f)
                ),

                ) {}

        val mainBtnColor = colorResource(id = R.color.btn_recommend_selected_color)

        val mainBtnIcon = painterResource(id = R.drawable.icon_food)

        val mainBtnText = recommendedFood.name

        val mainBtnTextColor = colorResource(id = R.color.black)

        recommendButton(
            modifier, mainBtnColor, mainBtnIcon, mainBtnText, mainBtnTextColor
        ) {}

        val marginTopRetryBtn = pxToDpFixedDpi(px = 434f)

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
            ) {}

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

}

@Preview
@Composable
private fun preview(){
    MealPickerTheme {
        RecommendAfterView(Food())
    }
}