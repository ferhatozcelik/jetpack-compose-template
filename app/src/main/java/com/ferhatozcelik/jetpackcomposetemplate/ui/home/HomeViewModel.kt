package com.ferhatozcelik.jetpackcomposetemplate.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ferhatozcelik.jetpackcomposetemplate.data.model.Resource
import com.ferhatozcelik.jetpackcomposetemplate.domain.usecase.GetExamplesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getExamplesUseCase: GetExamplesUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Idle)
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    /** Single entry point for every user intent coming from [MainScreen]. */
    fun onEvent(event: HomeEvent) {
        when (event) {
            HomeEvent.LoadExamples -> loadExamples()
        }
    }

    private fun loadExamples() {
        viewModelScope.launch {
            getExamplesUseCase().collect { resource ->
                _uiState.value = when (resource) {
                    is Resource.Loading -> HomeUiState.Loading
                    is Resource.Success -> HomeUiState.Success(resource.data)
                    is Resource.Error -> HomeUiState.Error(resource.errorMessage)
                }
            }
        }
    }
}
