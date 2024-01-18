package com.shimitadouglas.jobwave.progressIndicators

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ProgressDialogLoading() {

    var isProgressing by rememberSaveable {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = true) {
        isProgressing = true
    }


    if (isProgressing) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator(
                modifier = Modifier.width(70.dp),
                color = MaterialTheme.colorScheme.surfaceVariant,
                trackColor = MaterialTheme.colorScheme.secondary,
            )

        }
    }

}

