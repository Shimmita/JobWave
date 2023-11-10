package com.shimitadouglas.jobwave.screens

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.shimitadouglas.jobwave.data_class.getBottomItemList
import com.shimitadouglas.jobwave.data_class.homeListData
import com.shimitadouglas.jobwave.routes.routes

@Composable
fun Home(navController: NavHostController) {

    ScaffoldParentHome(navController)
}


@Composable
fun HomeContent(navController: NavHostController, paddingValues: PaddingValues) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        state = rememberLazyGridState(),
        modifier = Modifier
            .background(if (isSystemInDarkTheme()) Color.Gray else Color.Unspecified)
            .padding(paddingValues),


        ) {
        items(homeListData().sortedBy { it.title }) { item ->
            GridItemHome(item.image, item.title, navController)
        }
    }

}


@Composable
fun GridItemHome(image: Int, title: String, navController: NavHostController) {
    Card(
        modifier = Modifier
            .size(250.dp)
            .padding(10.dp),
        elevation = CardDefaults.cardElevation(5.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)


    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            var titleQuery = ""
            val tokens = title.split(" ")
            titleQuery = if (tokens.size > 3) {
                tokens[2]
            } else {
                tokens[0].lowercase()
            }

            Image(
                painter = painterResource(image),
                contentDescription = title,
                modifier = Modifier
                    .size(100.dp)
                    .clip(shape = CircleShape)
                    .border(width = 1.dp, color = Color.White, shape = CircleShape),
                contentScale = ContentScale.Crop
            )

            Spacer(Modifier.height(height = 10.dp))

            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.secondary
            )

            Spacer(Modifier.height(height = 10.dp))

            OutlinedButton(onClick = {
                //navigate to the job page
                navController.navigate(route = "${routes.SCREEN_JOB}/$titleQuery")
            }) {
                Text(text = "View")
            }
        }
    }
}

@Composable
fun ScaffoldParentHome(navController: NavHostController) {
    var isEffect by rememberSaveable {
        mutableStateOf(false)
    }


    val alphaValue by animateFloatAsState(
        targetValue = if (isEffect) 1.0f else 0.0f,
        animationSpec = tween(durationMillis = 2500, easing = FastOutLinearInEasing),
        label = "home_alpha_effect"
    )

    LaunchedEffect(key1 = true) {
        isEffect = true
    }


    Scaffold(modifier = Modifier.alpha(alphaValue),
        floatingActionButton = {
            FloatingActionComposable()
        },
        bottomBar = {
            BottomNavigationComposable()
        }
    ) {
        it
        HomeContent(navController, it)

    }
}

@Composable
fun BottomNavigationComposable() {
    NavigationBar {
        getBottomItemList().forEachIndexed { index, dataBottom ->
            NavigationBarItem(selected = true, onClick = { /*TODO*/ }, icon = {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = dataBottom.selectedImage,
                        contentDescription = dataBottom.title
                    )
                    Text(text = dataBottom.title)
                }
            })
        }
    }
}

@Composable
fun FloatingActionComposable() {
    FloatingActionButton(onClick = { /*TODO*/ }) {
        Icon(imageVector = Icons.Default.Email, contentDescription = "floating")
    }
}


//preview
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_NO)
@Composable
fun PreviewHome() {
    val navController = rememberNavController()
    Home(navController = navController)
}
