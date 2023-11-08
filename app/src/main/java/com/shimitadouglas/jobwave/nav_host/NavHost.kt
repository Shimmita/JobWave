package com.shimitadouglas.jobwave.nav_host

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.shimitadouglas.jobwave.routes.routes
import com.shimitadouglas.jobwave.screens.Home
import com.shimitadouglas.jobwave.screens.JobsScreen
import com.shimitadouglas.jobwave.screens.SplashStarter

@Composable
fun NavHostMain(navController: NavHostController) {
    NavHost(navController = navController, startDestination = routes.SCREEN_SPLASH) {
        composable(route = routes.SCREEN_SPLASH) {
            SplashStarter(navController)
        }

        composable(route = routes.SCREEN_HOME) {
            Home(navController)
        }

        composable(
            route = "${routes.SCREEN_JOB}/{title}", arguments = listOf(
                navArgument(name = "title") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val parameterSearch= backStackEntry.arguments?.getString("title")
            JobsScreen(navController,parameterSearch)
        }
    }
}