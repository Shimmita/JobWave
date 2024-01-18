package com.shimitadouglas.jobwave.nav_host

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.shimitadouglas.jobwave.constant_objects.ObjectConstants
import com.shimitadouglas.jobwave.data_class.DataJobItem
import com.shimitadouglas.jobwave.routes.Routes
import com.shimitadouglas.jobwave.screens.Home
import com.shimitadouglas.jobwave.screens.JobApplicationWebScreen
import com.shimitadouglas.jobwave.screens.JobsScreen
import com.shimitadouglas.jobwave.screens.LoginScreen
import com.shimitadouglas.jobwave.screens.RegisterScreen
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

        //login Screen
        composable(route = Routes.SCREEN_LOGIN) {
            LoginScreen(navController)
        }

        //register screen
        composable(route = Routes.SCREEN_REGISTER) {
            RegisterScreen(navController)
        }

        //job application screen
        composable(route = Routes.SCREEN_JOB_APPLICATION) {
            it
            var previousJobItem: DataJobItem by rememberSaveable {
                mutableStateOf(DataJobItem(link = "", title = "", organisation = ""))
            }

            //get item object of the previous backstackEntry
            LaunchedEffect(key1 = it) {
                previousJobItem =
                    navController.previousBackStackEntry?.savedStateHandle?.get<DataJobItem>(key = ObjectConstants.KEY_ITEM_OBJECT)!!

                Log.d(
                    "ARG_ITEM_PREV_LAUNCH",
                    "NavHostMain: title:${previousJobItem.title}\n link:${previousJobItem.link}\n org:${previousJobItem.organisation}"
                )

            }

            if (previousJobItem.title.trim().isNotEmpty()) {
                JobApplicationWebScreen(navController = navController, previousJobItem)

            }

        }


    }
}