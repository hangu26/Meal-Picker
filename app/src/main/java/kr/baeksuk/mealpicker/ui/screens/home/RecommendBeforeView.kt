package kr.baeksuk.mealpicker.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import kr.baeksuk.mealpicker.R
import kr.baeksuk.mealpicker.ui.dialog.ExcludeDialog
import kr.baeksuk.mealpicker.ui.theme.MealPickerTheme
import kr.baeksuk.mealpicker.ui.theme.pretendard
import kr.baeksuk.mealpicker.util.util.pxToDpFixedDpi
import kr.baeksuk.mealpicker.util.util.pxToSpFixedDpi

@Composable
fun RecommendBeforeView(onClick : () -> Unit){

    val showDialog = remember { mutableStateOf(false) }

    if (showDialog.value) {
        ExcludeDialog(onDismiss = { showDialog.value = false })
    }

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.white)),

        ) {
        val (recommendBtn, txExclude) = createRefs()

        val interactionSource = remember { MutableInteractionSource() }

        val modifier = Modifier
            .size(pxToDpFixedDpi(px = 695f))
            .constrainAs(recommendBtn) {
                centerHorizontallyTo(parent)
                centerVerticallyTo(parent)
            }
            .clickable(
                enabled = true,
                interactionSource = interactionSource,
                indication = rememberRipple(
                    bounded = true, radius = pxToDpFixedDpi(px = 348f)
                ),

                ) {}

        val btnColor = colorResource(id = R.color.btn_recommend_color)

        val btnIcon = painterResource(id = R.drawable.icon_food)

        val btnText = stringResource(id = R.string.tx_btn_recommend)

        val btnTextColor = colorResource(id = R.color.white)

        val isClickable = true

        TopRankFood()

        recommendButton(modifier, btnColor, btnIcon, btnText, btnTextColor, isClickable) {
            onClick()
        }

        val marginTopToRecommend = pxToDpFixedDpi(px = 83f)

        Text(modifier = Modifier
            .constrainAs(txExclude) {
                top.linkTo(recommendBtn.bottom, margin = marginTopToRecommend)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                showDialog.value = true
            },
            text = stringResource(id = R.string.tx_exclude_food),
            fontSize = pxToSpFixedDpi(px = 64f),
            color = colorResource(id = R.color.tx_exclude_food_color),
            fontFamily = pretendard,
            fontWeight = FontWeight.Normal
        )

    }
}

@Preview
@Composable
private fun preview(){
    MealPickerTheme {
        RecommendBeforeView(){}
    }
}