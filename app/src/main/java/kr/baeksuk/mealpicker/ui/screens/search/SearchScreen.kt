package kr.baeksuk.mealpicker.ui.screens.search

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
fun searchScreen(){

    Column(

        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.white)),

        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ) {
        
        Text(
            text = "검색 화면",
            fontSize = pxToSpFixedDpi(px = 80f),
            fontFamily = pretendard,
            fontWeight = FontWeight.Bold)
        
    }

}

@Preview
@Composable
fun previewSearch(){
    MealPickerTheme {
        searchScreen()
    }
}