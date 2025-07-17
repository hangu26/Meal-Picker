package kr.baeksuk.mealpicker.ui.screens.home

import android.app.Application
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import kr.baeksuk.mealpicker.data.model.Food
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val application: Application) :
    AndroidViewModel(application) {

    private val _showToast = MutableSharedFlow<String>()
    val showToast = _showToast.asSharedFlow()

    private val _recommendLoading = MutableStateFlow<Boolean>(false)
    val recommendLoading: StateFlow<Boolean> = _recommendLoading

    private val _foodList = mutableStateOf<List<Food>>(emptyList())
    val foodList: State<List<Food>> = _foodList

    private val _showTextExclude = MutableStateFlow<Boolean>(false)
    val showTextExclude: StateFlow<Boolean> = _showTextExclude

    private val _recommendedFood = mutableStateOf<Food?>(null)
    val recommendedFood: State<Food?> = _recommendedFood

    fun resetState() {
        _showTextExclude.value = false
    }

    fun btnRecommendClicked() {
        viewModelScope.launch {

            startLoading()

            // 3. 2.5초 동안 로딩만 보여줌
            delay(2500)

            recommendFood()

            endLoading()
        }
    }

    private suspend fun startLoading() {

        _recommendLoading.value = true
//        _showTextExclude.value = false

    }

    private suspend fun endLoading() {

        _recommendLoading.value = false
        _showTextExclude.value = true

    }

    private suspend fun recommendFood() {

        if (_foodList.value.isNotEmpty()) {
            val randomFood = _foodList.value.random()
            _recommendedFood.value = randomFood
            _showToast.emit("추천 음식: ${randomFood.name}")
        } else {
            _showToast.emit("음식 리스트를 가져오는 중입니다.")
        }

    }

    fun loadFoodFromAsset() {

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val json = application.assets
                    .open("food_data_100.json")
                    .bufferedReader()
                    .use { it.readText() }

                val parsed = Gson().fromJson(json, Array<Food>::class.java).toList()
                _foodList.value = parsed

                _showToast.emit("음식 ${parsed.size}개 불러옴")

            } catch (e: Exception) {
                _showToast.emit("불러오기 실패: ${e.localizedMessage}")
            }
        }

    }

}