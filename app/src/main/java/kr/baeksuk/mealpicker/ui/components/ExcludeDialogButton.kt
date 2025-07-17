package kr.baeksuk.mealpicker.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kr.baeksuk.mealpicker.R
import kr.baeksuk.mealpicker.ui.theme.MealPickerTheme
import kr.baeksuk.mealpicker.ui.theme.pretendard
import kr.baeksuk.mealpicker.util.util.pxToDpFixedDpi
import kr.baeksuk.mealpicker.util.util.pxToSpFixedDpi

@Composable
fun ExcludeDialogButton(
    modifier: Modifier,
    btnColor: Color,
    txButton: String,
    txColor: Color,
    onClick: () -> Unit
) {

    Surface(
        modifier = modifier.clickable { onClick() },
        shape = RoundedCornerShape(pxToDpFixedDpi(px = 23f)),
        color = btnColor,
        tonalElevation = 15.dp,
        shadowElevation = 20.dp
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {

            Spacer(modifier = Modifier.width(pxToDpFixedDpi(24f)))

            Text(
                text = txButton,
                fontFamily = pretendard,
                fontWeight = FontWeight.SemiBold,
                fontSize = pxToSpFixedDpi(px = 46f),
                color = txColor
            )

        }

    }
}

@Preview
@Composable
fun PreviewExcludeDialogButton() {
    MealPickerTheme {
        ExcludeDialogButton(
            modifier = Modifier
                .width(pxToDpFixedDpi(px = 333f))
                .height(pxToDpFixedDpi(px = 162f)),
            btnColor = colorResource(id = R.color.background_btn_cancel),
            txButton = "취소하기",
            txColor = Color.Black,
            onClick = {}
        )
    }
}