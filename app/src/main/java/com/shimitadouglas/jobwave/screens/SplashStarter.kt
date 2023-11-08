package com.shimitadouglas.jobwave.screens

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavHostController
import com.shimitadouglas.jobwave.routes.routes
import kotlinx.coroutines.delay

@Composable
fun SplashStarter(navController: NavHostController) {
    Surface {

        //variable hold state screen navigation
        var isNavigateMain by rememberSaveable {
            mutableStateOf(false)
        }

        val alphaValueTitle by animateFloatAsState(
            targetValue = if (isNavigateMain) 1.0f else 0.0f,
            animationSpec = tween(durationMillis = 2000, easing = FastOutSlowInEasing),
            label = "effectTitle"
        )




        LaunchedEffect(key1 = true) {
            isNavigateMain = true
            delay(4000)

            //prevent stack back effect
            navController.popBackStack()
            //after delay na main screen
            navController.navigate(route = routes.SCREEN_HOME)
        }

        //display contents of Splash
        SplashContent(alphaValueTitle)

    }
}


//holds splash content
@Composable
fun SplashContent(alphaValue: Float) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(if (isSystemInDarkTheme()) Color.Black else MaterialTheme.colorScheme.onPrimary),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ) {
        Text(
            text = "JOB WAVE",
            textAlign = TextAlign.End,
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
            color = if (isSystemInDarkTheme()) Color.White else Color.DarkGray,
            modifier = Modifier.alpha(alphaValue)

        )
        Text(
            text = "Jobs In The IT Industry",
            textAlign = TextAlign.End,
            style = MaterialTheme.typography.bodySmall,
            color = if (isSystemInDarkTheme()) Color.White else Color.DarkGray,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.alpha(alphaValue)

        )
    }

}


