package kr.baeksuk.mealpicker.ui.screens.mainscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.fontResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import kr.baeksuk.mealpicker.R
import kr.baeksuk.mealpicker.ui.theme.MealPickerTheme
import kr.baeksuk.mealpicker.ui.theme.pretendard
import kr.baeksuk.mealpicker.util.util.pxToDpFixedDpi
import kr.baeksuk.mealpicker.util.util.pxToSpFixedDpi

@Composable
fun mainScreen() {

    MaterialTheme {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = pxToDpFixedDpi(px = 96f), top = pxToDpFixedDpi(px = 60f),
                ),
        ) {

            Text(
                text = stringResource(id = R.string.app_name),
                fontSize = pxToSpFixedDpi(80f),
                fontFamily = pretendard,
                fontWeight = FontWeight.Bold
            )

        }

    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MealPickerTheme {
        mainScreen()
    }
}