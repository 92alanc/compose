package com.alancamargo.compose.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alancamargo.compose.di.IoDispatcher
import com.alancamargo.compose.domain.usecase.GetMyModelListUseCase
import com.alancamargo.compose.tools.Logger
import com.alancamargo.compose.ui.mapping.toUi
import com.alancamargo.compose.ui.model.UiMyModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getMyModelListUseCase: GetMyModelListUseCase,
    private val logger: Logger,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _state = MutableStateFlow(MainUiState())
    private val _action = MutableSharedFlow<MainUiAction>()

    val state = _state.asStateFlow()
    val action = _action.asSharedFlow()

    fun onFabClicked() {
        viewModelScope.launch(dispatcher) {
            getMyModelListUseCase().onStart {
                logger.logMessage(message = "Loading...")
            }.onCompletion {
                logger.logMessage(message = "Operation completed. Whether it\'s successful or not, you decide")
            }.catch { exception ->
                logger.logError(message = "Something went wrong!", exception)
            }.collect { list ->
                val uiList = list.map { it.toUi() }
                _state.update { it.onListReceived(uiList) }
            }
        }
    }

    fun onListItemClicked(item: UiMyModel) {
        viewModelScope.launch(dispatcher) {
            val action = MainUiAction.ShowToast(item.text)
            _action.emit(action)
        }
    }
}
