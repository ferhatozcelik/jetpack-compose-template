package com.ferhatozcelik.jetpackcomposetemplate.ui.home

import androidx.lifecycle.ViewModel
import com.ferhatozcelik.jetpackcomposetemplate.data.repository.ExampleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val exampleRepository: ExampleRepository) : ViewModel() {
    private val TAG = HomeViewModel::class.java.simpleName


}