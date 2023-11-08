package com.shimitadouglas.jobwave

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.shimitadouglas.jobwave.nav_host.NavHostMain
import com.shimitadouglas.jobwave.ui.theme.JobWaveTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JobWaveTheme {
                // A surface container using the 'background' color from the theme
                val navController = rememberNavController()
                NavHostMain(navController)
            }
        }
    }
}

