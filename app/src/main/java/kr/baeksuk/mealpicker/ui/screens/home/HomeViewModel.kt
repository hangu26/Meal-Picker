package kr.baeksuk.mealpicker.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {

    private val _showToast = MutableSharedFlow<String>()
    val showToast = _showToast.asSharedFlow()

    fun btnRecommendClicked() {

        viewModelScope.launch {
            _showToast.emit("버튼 눌림")
        }

    }

}