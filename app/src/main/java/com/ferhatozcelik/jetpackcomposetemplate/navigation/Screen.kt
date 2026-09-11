package com.ferhatozcelik.jetpackcomposetemplate.navigation

import kotlinx.serialization.Serializable

/**
 * Type-safe navigation destinations (Navigation Compose 2.8+).
 * Each destination is a serializable object/class instead of a raw string route,
 * so arguments are compile-time checked and refactor-safe.
 */
sealed interface Screen {

    @Serializable
    data object Main : Screen

    @Serializable
    data class Detail(val id: Int) : Screen
}
