package com.ferhatozcelik.jetpackcomposetemplate.data.model

/**
 * A generic wrapper describing the state of a piece of data as it flows from a
 * data source (network or database) up to the presentation layer.
 */
sealed class Resource<out T : Any> {
    data object Loading : Resource<Nothing>()
    data class Success<out T : Any>(val data: T) : Resource<T>()
    data class Error(val errorMessage: String) : Resource<Nothing>()
}
