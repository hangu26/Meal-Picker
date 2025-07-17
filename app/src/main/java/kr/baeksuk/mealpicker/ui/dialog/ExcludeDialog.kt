package kr.baeksuk.mealpicker.ui.dialog

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import kr.baeksuk.mealpicker.R
import kr.baeksuk.mealpicker.ui.theme.MealPickerTheme
import kr.baeksuk.mealpicker.ui.theme.gasoek
import kr.baeksuk.mealpicker.util.util.pxToDpFixedDpi
import kr.baeksuk.mealpicker.util.util.pxToSpFixedDpi

@Composable
fun ExcludeDialog() {

    Dialog(onDismissRequest = {}) {

        Surface(
            modifier = Modifier.wrapContentSize(), color = colorResource(id = R.color.white)
        ) {
            ExcludeDialogContent()
        }

    }

}

@Composable
fun ExcludeDialogContent() {

    Surface(
        tonalElevation = 10.dp,
        shadowElevation = 15.dp,
        shape = RoundedCornerShape(pxToDpFixedDpi(px = 40f)),
        color = colorResource(id = R.color.white)
    ) {

        Column(
            modifier = Modifier
                .width(pxToDpFixedDpi(px = 1100f))
                .height(pxToDpFixedDpi(px = 860f))


        ) {
            Spacer(
                modifier = Modifier
                    .height(pxToDpFixedDpi(px = 149f))
                    .fillMaxWidth()
            )
            Text(
                stringResource(id = R.string.tx_not_this_food),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(),
                color = colorResource(id = R.color.app_color),
                fontFamily = gasoek,
                fontWeight = FontWeight.Normal,
                fontSize = pxToSpFixedDpi(px = 84f),
            )
            Spacer(
                modifier = Modifier
                    .height(pxToDpFixedDpi(px = 69f))
                    .fillMaxWidth()
            )
            Button(
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                shape = RoundedCornerShape(24.dp)
            ) {
                Text("Enter", fontSize = 16.sp)
            }
            Spacer(
                modifier = Modifier
                    .height(12.dp)
                    .fillMaxWidth()
            )
        }

    }


}

@Preview
@Composable
fun previewExcludeDialog() {

    MealPickerTheme {
        ExcludeDialog()
    }

}