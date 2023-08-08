package com.ferhatozcelik.jetpackcomposetemplate.data.model

sealed class Resource<out T : Any> {
    object Loading : Resource<Nothing>()
    data class Success<out T : Any>(val data: Any) : Resource<T>()
    data class Error(val errorMessage: String) : Resource<Nothing>()
}