package kr.baeksuk.mealpicker.ui.dialog

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import kr.baeksuk.mealpicker.R
import kr.baeksuk.mealpicker.data.model.FoodCategory
import kr.baeksuk.mealpicker.ui.components.ExcludeDialogButton
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

    val categories = FoodCategory.entries.toTypedArray()

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
                        txCategory = stringResource(id = category.textRes)
                    )
                }
            }

            SpaceHeight(99f)

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                categories.drop(3).forEachIndexed { index, category ->
                    CategoryButton(
                        painter = painterResource(id = category.imageRes),
                        txCategory = stringResource(id = category.textRes)
                    )
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

            Row (modifier = Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.Center){

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
                    )
                ) {

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
    onClickCheck: () -> Unit = {}
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
            .clickable { isSelected = !isSelected },
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

        Image(
            painter = painter,
            contentDescription = "음식",
            modifier = Modifier
                .width(pxToDpFixedDpi(px = 192f))
                .height(pxToDpFixedDpi(px = 200f))
        )

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

@Preview
@Composable
fun previewExcludeDialog() {

    MealPickerTheme {
        ExcludeDialog() {}
    }

}