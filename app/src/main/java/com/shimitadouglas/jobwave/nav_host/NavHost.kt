package com.shimitadouglas.jobwave.nav_host

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.shimitadouglas.jobwave.routes.Routes
import com.shimitadouglas.jobwave.screens.Home
import com.shimitadouglas.jobwave.screens.JobApplicationWebScreen
import com.shimitadouglas.jobwave.screens.JobsScreen
import com.shimitadouglas.jobwave.screens.SplashStarter

@Composable
fun NavHostMain(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Routes.SCREEN_SPLASH) {

        //splash screen
        composable(route = Routes.SCREEN_SPLASH) {
            SplashStarter(navController)
        }

        //home screen
        composable(route = Routes.SCREEN_HOME) {
            Home(navController)
        }

        //jobs screen with param title job type
        composable(
            route = "${Routes.SCREEN_JOB}/{title}", arguments = listOf(
                navArgument(name = "title") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val parameterSearch = backStackEntry.arguments?.getString("title")
            JobsScreen(navController, parameterSearch)
        }


        //job application screen with link of the job as param
        composable(
            route = "${Routes.SCREEN_JOB_APPLICATION}/{link}",
            listOf(navArgument(name = "link") {
                type = NavType.StringType
            })
        ) { navBackStackEntry ->
            val jobApplicationLink = navBackStackEntry.arguments?.getString("link")

            if (jobApplicationLink != null) {
                JobApplicationWebScreen(jobUrlLink = jobApplicationLink)
            }


        }


    }
}