package kr.baeksuk.mealpicker.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import kr.baeksuk.mealpicker.R
import kr.baeksuk.mealpicker.ui.theme.MealPickerTheme
import kr.baeksuk.mealpicker.ui.theme.pretendard
import kr.baeksuk.mealpicker.util.util.pxToSpFixedDpi

@Composable
fun homeScreen(){

    Column(

        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.black)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

        ) {

        Text(
            text = "홈 화면",
            color = colorResource(id = R.color.white),
            fontSize = pxToSpFixedDpi(px = 80f),
            fontFamily = pretendard,
            fontWeight = FontWeight.Bold
        )

    }

}

@Preview
@Composable
fun previewHome(){

    MealPickerTheme {
        homeScreen()
    }

}