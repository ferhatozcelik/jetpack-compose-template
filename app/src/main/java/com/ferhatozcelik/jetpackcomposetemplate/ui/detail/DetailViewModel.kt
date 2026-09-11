package com.ferhatozcelik.jetpackcomposetemplate.ui.detail

import androidx.lifecycle.ViewModel
import com.ferhatozcelik.jetpackcomposetemplate.data.repository.ExampleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val exampleRepository: ExampleRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow<DetailUiState>(DetailUiState.Loading)
    val uiState: StateFlow<DetailUiState> = _uiState.asStateFlow()

    /** Single entry point for every user intent coming from [DetailScreen]. */
    fun onEvent(event: DetailEvent) {
        when (event) {
            is DetailEvent.LoadDetail -> _uiState.value = DetailUiState.Content(event.id)
        }
    }
}
