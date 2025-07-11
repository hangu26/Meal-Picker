package kr.baeksuk.mealpicker.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import kr.baeksuk.mealpicker.R
import kr.baeksuk.mealpicker.ui.theme.MealPickerTheme
import kr.baeksuk.mealpicker.ui.theme.pretendard
import kr.baeksuk.mealpicker.util.util.pxToDpFixedDpi
import kr.baeksuk.mealpicker.util.util.pxToSpFixedDpi

@Composable
fun homeScreen(

    viewModel: HomeViewModel = hiltViewModel()

) {
    val foods by viewModel.foodList
    val context = LocalContext.current
    val recommendedFood by viewModel.recommendedFood

    LaunchedEffect(Unit) {
        viewModel.resetState()
        viewModel.loadFoodFromAsset()
    }



    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.white)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {

        val showTextExclude by viewModel.showTextExclude.collectAsState()
        if (!showTextExclude) {

            RecommendBeforeView { viewModel.btnRecommendClicked() }

        } else {

            recommendedFood?.let { RecommendAfterView(recommendedFood = it) }

        }

    }

}

@Composable
fun recommendButton(
    modifier: Modifier = Modifier,
    btnColor: Color,
    painter: Painter,
    btnText: String,
    btnTextColor: Color,
    onClick: () -> Unit
) {

    Surface(
        modifier = modifier.clickable { onClick() },
        shape = CircleShape,
        color = btnColor,
        tonalElevation = 15.dp,
        shadowElevation = 20.dp
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                modifier = Modifier
                    .width(pxToDpFixedDpi(px = 75f))
                    .height(pxToDpFixedDpi(px = 83.33f)),
                painter = painter,
                contentDescription = "음식 아이콘"
            )
            Text(
                text = btnText,
                fontFamily = pretendard,
                fontWeight = FontWeight.Black,
                fontSize = pxToSpFixedDpi(px = 70f),
                color = btnTextColor
            )

        }

    }

}

@Composable
fun actionButton(
    modifier: Modifier,
    btnColor: Color,
    btnIcon: Painter,
    txButton: String,
    txColor: Color,
    onClick: () -> Unit
) {

    Surface(
        modifier = modifier,
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

            Image(
                modifier = Modifier
                    .width(pxToDpFixedDpi(px = 76f))
                    .height(pxToDpFixedDpi(px = 76f)),
                painter = btnIcon,
                contentDescription = "다시 하기 버튼"
            )

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

@Preview
@Composable
private fun previewHome() {

    MealPickerTheme {

    }

}