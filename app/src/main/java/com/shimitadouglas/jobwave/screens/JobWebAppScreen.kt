package com.shimitadouglas.jobwave.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import com.shimitadouglas.jobwave.data_class.DataJobItem
import com.shimitadouglas.jobwave.webhandler.WebViewHandler

@Composable
fun JobApplicationWebScreen(navController: NavHostController, previousJobItem: DataJobItem) {


    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {


        AndroidView(modifier = Modifier.fillMaxSize(), factory = {
            WebViewHandler(
                it,
            ).apply { loadExternalUrl(previousJobItem.link) }.webView
        }, update = { it.reload() })


    }

}