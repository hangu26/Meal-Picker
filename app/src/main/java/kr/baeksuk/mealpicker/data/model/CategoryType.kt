package kr.baeksuk.mealpicker.data.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.painter.Painter
import kr.baeksuk.mealpicker.R

enum class FoodCategory(
    @DrawableRes val imageRes: Int,
    @StringRes val textRes: Int
) {
    KOREAN(R.drawable.btn_korean_food, R.string.tx_koren_food),
    CHINESE(R.drawable.btn_chinese_food, R.string.tx_chinese_food),
    JAPANESE(R.drawable.btn_japanese_food, R.string.tx_japan_food),
    WESTERN(R.drawable.btn_western_food, R.string.tx_western_food),
    FUSION(R.drawable.btn_fusion_food, R.string.tx_fusion_food_02)
}