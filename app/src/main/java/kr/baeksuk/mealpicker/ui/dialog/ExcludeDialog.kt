package kr.baeksuk.mealpicker.ui.dialog

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import kr.baeksuk.mealpicker.R
import kr.baeksuk.mealpicker.data.model.FoodCategory
import kr.baeksuk.mealpicker.ui.components.ExcludeDialogButton
import kr.baeksuk.mealpicker.ui.screens.home.HomeViewModel
import kr.baeksuk.mealpicker.ui.theme.MealPickerTheme
import kr.baeksuk.mealpicker.ui.theme.gasoek
import kr.baeksuk.mealpicker.ui.theme.pretendard
import kr.baeksuk.mealpicker.util.util.pxToDpFixedDpi
import kr.baeksuk.mealpicker.util.util.pxToSpFixedDpi
import kr.baeksuk.mealpicker.util.util.SpaceHeight
import kr.baeksuk.mealpicker.util.util.SpaceWidth

@Composable
fun ExcludeDialog(onDismiss: () -> Unit) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Surface(
            modifier = Modifier.width(pxToDpFixedDpi(px = 1100f)),
            color = colorResource(id = R.color.white),
            tonalElevation = 10.dp,
            shadowElevation = 15.dp,
            shape = RoundedCornerShape(pxToDpFixedDpi(px = 40f)),
        ) {
            ExcludeDialogContent(onDismiss = onDismiss)
        }
    }
}

@Composable
fun ExcludeDialogContent(onDismiss: () -> Unit) {

    val viewModel: HomeViewModel = hiltViewModel()
    val context = LocalContext.current

    val categories = FoodCategory.entries.toTypedArray()
    val selectedCategories = remember { mutableStateListOf<String>() }
    val selectedCount = remember {
        mutableIntStateOf(0)
    }

    Surface(
        tonalElevation = 10.dp,
        shadowElevation = 15.dp,
        shape = RoundedCornerShape(pxToDpFixedDpi(px = 40f)),
        color = colorResource(id = R.color.white)
    ) {

        Column(
            modifier = Modifier
                .wrapContentSize()
        ) {
            SpaceHeight(74f)

            Text(
                stringResource(id = R.string.tx_not_this_food),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(),
                color = colorResource(id = R.color.app_color),
                fontFamily = gasoek,
                fontWeight = FontWeight.Normal,
                fontSize = pxToSpFixedDpi(px = 100f),
            )

            SpaceHeight(99f)

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                categories.take(3).forEach { category ->
                    CategoryButton(
                        painter = painterResource(id = category.imageRes),
                        txCategory = stringResource(id = category.textRes),
                    ) { name, selected ->

                        if (selected) {
                            selectedCategories.add(name)
                            selectedCount.intValue++
                        } else {
                            selectedCategories.remove(name)
                            selectedCount.intValue--
                        }

                    }
                }
            }

            SpaceHeight(99f)

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                categories.drop(3).forEachIndexed { index, category ->
                    /** 버튼이 클릭(체크) 되면 selectedCategories에 해당 카테고리 이름 추가
                     * 클릭된 버튼 개수 selectedCount 증가
                     * -> viewModel 로 전달 -> viewModel 에서 실질적 제외 로직 처리 **/
                    CategoryButton(
                        painter = painterResource(id = category.imageRes),
                        txCategory = stringResource(id = category.textRes),
                    ) { name, selected ->

                        if (selected) {
                            selectedCategories.add(name)
                            selectedCount.intValue++
                        } else {
                            selectedCategories.remove(name)
                            selectedCount.intValue--
                        }

                    }
                    if (index != 1) {
                        SpaceWidth(120f)
                    }
                }
            }

            val btnCloseColor = colorResource(id = R.color.background_btn_cancel)

            val txClose = stringResource(id = R.string.tx_close)

            val closeModifier = Modifier
                .width(pxToDpFixedDpi(px = 333f))
                .height(pxToDpFixedDpi(px = 162f))

            SpaceHeight(spaceSize = 99f)

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {

                ExcludeDialogButton(
                    modifier = closeModifier,
                    btnColor = btnCloseColor,
                    txButton = txClose,
                    txColor = colorResource(
                        id = R.color.black
                    )
                ) {
                    onDismiss()
                }

                val btnExcludeColor = colorResource(id = R.color.app_color)

                val txExclude = stringResource(id = R.string.tx_exclude)

                val excludeModifier = Modifier
                    .width(pxToDpFixedDpi(px = 333f))
                    .height(pxToDpFixedDpi(px = 162f))

                SpaceWidth(spaceSize = 150f)

                ExcludeDialogButton(
                    modifier = excludeModifier,
                    btnColor = btnExcludeColor,
                    txButton = txExclude,
                    txColor = colorResource(
                        id = R.color.white
                    ),

                    ) {

                    if (selectedCategories.isNotEmpty()) {

                        /** 모든 카테고리를 제외하는걸 방지 **/
                        if (selectedCount.intValue < categories.size) {
                            /** viewModel에서 제외하는 로직 **/
                            viewModel.recommendFoodExcludingCategory(selectedCategories)
                            onDismiss()
                        } else {
                            Toast.makeText(context, "최소 하나의 카테고리를 남겨주세요.", Toast.LENGTH_SHORT)
                                .show()
                        }

                    }

                }

            }

            SpaceHeight(spaceSize = 99f)


        }

    }


}


@Composable
fun CategoryButton(
    painter: Painter,
    txCategory: String,
    onClickCheck: (String, Boolean) -> Unit
) {

    var isSelected by remember {
        mutableStateOf(false)
    }

    val borderColor =
        if (!isSelected) colorResource(id = R.color.black) else colorResource(id = R.color.app_color)

    val backgroundColor =
        if (!isSelected) colorResource(id = R.color.white) else colorResource(id = R.color.selected_category_background)

    val checkColor =
        if (!isSelected) colorResource(id = R.color.white) else colorResource(id = R.color.app_color)

    Column(
        modifier = Modifier
            .width(pxToDpFixedDpi(px = 260f))
            .border(
                pxToDpFixedDpi(px = 5f),
                borderColor,
                shape = RoundedCornerShape(pxToDpFixedDpi(px = 20f))
            )
            .background(backgroundColor)
            .clickable {
                isSelected = !isSelected
                onClickCheck(txCategory, isSelected)
            },
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        SpaceHeight(20f)

        Surface(
            modifier = Modifier
                .size(pxToDpFixedDpi(px = 25f)),
            shape = CircleShape,
            color = checkColor,
            border = BorderStroke(pxToDpFixedDpi(px = 1f), Color.Black),
        ) {}

        SpaceHeight(5f)

        /**
        Image(
        painter = painter,
        contentDescription = "음식",
        modifier = Modifier
        .width(pxToDpFixedDpi(px = 192f))
        .height(pxToDpFixedDpi(px = 200f))
        )

         **/

        Box(
            modifier = Modifier
                .width(pxToDpFixedDpi(192f))
                .height(pxToDpFixedDpi(200f))
        ) {

            Image(
                painter = painter,
                contentDescription = "음식",
                modifier = Modifier.fillMaxSize()
            )

            if (isSelected) {

                AnimExcludeLoader(modifier = Modifier.align(Alignment.Center))

            }

        }

        Text(
            txCategory,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .wrapContentSize(),
            color = colorResource(id = R.color.black),
            fontFamily = pretendard,
            fontWeight = FontWeight.Bold,
            fontSize = pxToSpFixedDpi(px = 48f),
            lineHeight = pxToSpFixedDpi(px = 35f)
        )

        SpaceHeight(10f)

    }

}

@Composable
fun AnimExcludeLoader(modifier: Modifier) {

    val composition by rememberLottieComposition(LottieCompositionSpec.Asset("anim_exclude.json"))

    val progress by animateLottieCompositionAsState(composition = composition, iterations = 1)

    LottieAnimation(
        composition = composition, progress = { progress }, modifier = modifier
    )

}

@Preview
@Composable
fun previewExcludeDialog() {

    MealPickerTheme {
        ExcludeDialogContent() {}
    }

}