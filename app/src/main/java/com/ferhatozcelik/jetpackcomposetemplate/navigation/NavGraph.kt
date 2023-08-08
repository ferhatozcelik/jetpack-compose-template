package com.ferhatozcelik.jetpackcomposetemplate.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.ferhatozcelik.jetpackcomposetemplate.ui.detail.DetailScreen
import com.ferhatozcelik.jetpackcomposetemplate.ui.home.MainScreen

@Composable
fun NavGraph(navController: NavHostController) {

    NavHost(
        navController = navController, startDestination = Screen.Main.route
    ) {

        composable(Screen.Main.route) {
            MainScreen(navController = navController)
        }
        composable(
            "${Screen.Detail.route}/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) {
            DetailScreen(navController = navController, id = it.arguments?.getInt("id") ?: 0)
        }
    }
}
