package com.alancamargo.compose.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.alancamargo.compose.di.IoDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

private const val TEXT_1 = "BWAAAAAAA"
private const val TEXT_2 = "BWEEEEEEE"

@HiltViewModel
class MainViewModel @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _state = MutableStateFlow(MainUiState())

    private var currentText: String? = null
    private var fabClickCount = 0

    val state = _state.asStateFlow()

    fun onButtonClicked() {
        if (currentText == TEXT_1) {
            _state.update { it.onTextChanged(TEXT_2) }
            currentText = TEXT_2
        } else {
            _state.update { it.onTextChanged(TEXT_1) }
            currentText = TEXT_1
        }
    }

    fun onFabClicked() {
        _state.update { it.onFabClickCountChanged(++fabClickCount) }
    }
}
