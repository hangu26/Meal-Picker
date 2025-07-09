package kr.baeksuk.mealpicker.ui.screens.mainscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import kr.baeksuk.mealpicker.R
import kr.baeksuk.mealpicker.ui.navigation.BottomNavigation
import kr.baeksuk.mealpicker.ui.navigation.NavigationGraph
import kr.baeksuk.mealpicker.ui.theme.MealPickerTheme
import kr.baeksuk.mealpicker.ui.theme.pretendard
import kr.baeksuk.mealpicker.util.util.pxToDpFixedDpi
import kr.baeksuk.mealpicker.util.util.pxToSpFixedDpi

@Composable
fun mainScreen() {

    val navController = rememberNavController()

    Scaffold(
        topBar = {

            Column(

                modifier = Modifier
                    .background(colorResource(id = R.color.white))
                    .fillMaxWidth()
                    .padding(start = pxToDpFixedDpi(px = 90f), top = pxToDpFixedDpi(px = 60f))

            ) {

                Text(
                    text = "오늘 뭐 먹어?",
                    fontSize = pxToSpFixedDpi(px = 80f),
                    fontFamily = pretendard,
                    fontWeight = FontWeight.Bold
                )

            }
        },
        bottomBar = { BottomNavigation(navController = navController) }
    ) {
        Box(
            Modifier
                .padding(it)
        ) {
            NavigationGraph(navController = navController)
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