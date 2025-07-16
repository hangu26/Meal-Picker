package kr.baeksuk.mealpicker.ui.screens.splash

import android.content.Intent
import androidx.compose.animation.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import kotlinx.coroutines.delay
import kr.baeksuk.mealpicker.MainActivity
import kr.baeksuk.mealpicker.R
import kr.baeksuk.mealpicker.ui.theme.MealPickerTheme
import kr.baeksuk.mealpicker.ui.theme.pretendard
import kr.baeksuk.mealpicker.util.util.pxToDpFixedDpi
import kr.baeksuk.mealpicker.util.util.pxToSpFixedDpi

@Composable
fun SplashScreen() {

    val alpha = remember {
        androidx.compose.animation.core.Animatable(0f)
    }

    val context = LocalContext.current

    LaunchedEffect(key1 = Unit){
        alpha.animateTo(
            targetValue = 1f,
            animationSpec = tween(1500)
        )
        delay(1500L)

        Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }.let { intent ->
            context.startActivity(intent)
        }

    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.white)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        splashAnimLoader()

        Text(
            text = stringResource(id = R.string.tx_splash_02), fontFamily = pretendard,
            fontWeight = FontWeight.Thin, fontSize = pxToSpFixedDpi(px = 58f),
        )

    }

}

@Composable
fun splashAnimLoader() {

    val composition by rememberLottieComposition(LottieCompositionSpec.Asset("tx_meal_picker.json"))

    val progress by animateLottieCompositionAsState(
        composition, iterations = 2
    )

    LottieAnimation(
        composition = composition,
        progress = { progress },
        modifier = Modifier
            .width(pxToDpFixedDpi(px = 960f))
            .height(pxToDpFixedDpi(px = 164f))
    )

}

@Preview
@Composable
fun prevSplash() {

    MealPickerTheme {

        SplashScreen()

    }

}