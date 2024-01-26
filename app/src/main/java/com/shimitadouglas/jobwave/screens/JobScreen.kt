package com.shimitadouglas.jobwave.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.shimitadouglas.jobwave.base_url.BaseUrl
import com.shimitadouglas.jobwave.constant_objects.ObjectConstants
import com.shimitadouglas.jobwave.data_class.DataJobItem
import com.shimitadouglas.jobwave.data_class.DataJobResponse
import com.shimitadouglas.jobwave.findicon.findIcon
import com.shimitadouglas.jobwave.interface_service.GetAllPostInterface
import com.shimitadouglas.jobwave.progressIndicators.LoadingA
import com.shimitadouglas.jobwave.routes.Routes
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Composable
fun JobsScreen(navController: NavHostController, parameterSearch: String?) {
    //search display determinant
    var isSearchShown by rememberSaveable {
        mutableStateOf(false)
    }

    Scaffold(floatingActionButton = {
        FloatingActionButton(onClick = { isSearchShown = !isSearchShown }) {
            Image(imageVector = Icons.Filled.Search, contentDescription = "search")
        }
    }) {
        it
        //container for jobScreen
        JobScreenBodyContent(parameterSearch, it, navController, isSearchShown)
    }

}


@Composable
fun JobScreenBodyContent(
    parameterSearch: String?,
    paddingValues: PaddingValues,
    navController: NavHostController,
    isSearchShown: Boolean,
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(paddingValues)
            .padding(vertical = 10.dp)
    ) {

        //logging the selected job from the main via nav args
        Log.d("ARGS", "JobsScreen: $parameterSearch")

        //
        var mainArrayData by rememberSaveable {
            mutableStateOf(emptyList<DataJobItem>())
        }

        //init the retrofit builder
        val retrofitBuilder = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BaseUrl.BASE_URL).build().create(GetAllPostInterface::class.java)

        val retrofitData = retrofitBuilder.getAllJobs()

        //enqueue the retrofit data
        retrofitData.enqueue(object : Callback<DataJobResponse?> {

            //success response
            override fun onResponse(
                call: Call<DataJobResponse?>, response: Response<DataJobResponse?>
            ) {
                mainArrayData = response.body()?.data!!

            }

            //error response
            override fun onFailure(call: Call<DataJobResponse?>, t: Throwable) {
                Log.e("ERROR", "onFailure: ${t.message}")
            }

        })


        //if the main array is empty display data
        if (mainArrayData.isNotEmpty()) {
            //display fab


            /*//display search bar only if fab is clicked
            if (isSearchShown) {
                ComposableSearchBar(mainArrayData)
            }*/


            //displayJobType params searchText,array,controller
            WhatJobDisplayed(parameterSearch, mainArrayData, navController)
            //

        } else {
            //display progress
            LoadingA()
        }

    }

}

@Composable
fun WhatJobDisplayed(
    parameterSearch: String?,
    mainArrayData: List<DataJobItem>,
    navController: NavHostController
) {
    //using Lazy Column To load data stored in the array
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        //all jobs
        if (parameterSearch!!.contains("all")) {
            items(mainArrayData) { item: DataJobItem ->
                DisplayDataItem(
                    suggestIcon = findIcon(item.title),
                    navController, item = item
                )
            }
        }

        //android
        if (parameterSearch.contains("android")) {
            items(mainArrayData) { item: DataJobItem ->
                if (item.title.lowercase().contains(parameterSearch)) {
                    DisplayDataItem(
                        suggestIcon = findIcon(item.title),
                        navController = navController, item = item
                    )
                }

            }
        }

        //angular
        if (parameterSearch.contains("angular")) {
            items(mainArrayData) { item: DataJobItem ->
                if (item.title.lowercase().contains(parameterSearch)) {
                    DisplayDataItem(
                        suggestIcon = findIcon(item.title),
                        navController = navController, item = item
                    )
                }

            }
        }

        //ML/AI
        if (parameterSearch.contains("artificial")) {
            items(mainArrayData) { item: DataJobItem ->
                if (item.title.lowercase().contains(parameterSearch) || item.title.lowercase()
                        .contains("machine") || item.title.lowercase()
                        .contains("ml/ai") || item.title.lowercase()
                        .contains("generative") || item.title.lowercase().contains("intelligence")
                ) {
                    DisplayDataItem(
                        suggestIcon = findIcon(item.title),
                        navController = navController, item = item
                    )
                }

            }
        }

        //backend developer
        if (parameterSearch.contains("backend")) {
            items(mainArrayData) { item: DataJobItem ->
                if (item.title.lowercase().contains(parameterSearch) || item.title.lowercase()
                        .contains("back end") || item.title.lowercase().contains(
                        "back-end"
                    )
                ) {
                    DisplayDataItem(
                        suggestIcon = findIcon(item.title),
                        navController = navController, item = item
                    )
                }

            }
        }

        //sharp or .net
        if (parameterSearch.contains(".net", ignoreCase = true)) {
            items(mainArrayData) { item: DataJobItem ->
                if (item.title.lowercase()
                        .contains(parameterSearch, ignoreCase = true) || item.title.lowercase()
                        .contains("C#", ignoreCase = true) || item.title.lowercase().contains(
                        "microsoft", ignoreCase = true
                    ) || item.title.lowercase().contains(
                        "asp", ignoreCase = true
                    )
                ) {
                    DisplayDataItem(
                        suggestIcon = findIcon(item.title),
                        navController = navController, item = item
                    )
                }

            }
        }


        //locating the cpp job
        if (parameterSearch.contains("c++", ignoreCase = true)) {
            items(mainArrayData) { item: DataJobItem ->
                if (item.title.lowercase().contains(parameterSearch) || item.title.lowercase()
                        .contains("cpp", ignoreCase = true) || item.title.lowercase().contains(
                        "c+", ignoreCase = true
                    ) || item.title.lowercase().contains(
                        "c-pp", ignoreCase = true
                    )
                ) {
                    DisplayDataItem(
                        suggestIcon = findIcon(item.title),
                        navController = navController, item = item
                    )
                }

            }
        }


        //locating the cybersecurity jobs
        if (parameterSearch.contains("cybersecurity", ignoreCase = true)) {
            items(mainArrayData) { item: DataJobItem ->
                if (item.title.lowercase().contains(parameterSearch) || item.title.lowercase()
                        .contains("security") || item.title.lowercase().contains(
                        "ethical", ignoreCase = true
                    ) || item.title.lowercase().contains(
                        "linux", ignoreCase = true
                    ) || item.title.lowercase().contains(
                        "forensic", ignoreCase = true
                    ) || item.title.lowercase().contains(
                        "malware", ignoreCase = true
                    )
                ) {
                    DisplayDataItem(
                        suggestIcon = findIcon(item.title),
                        navController = navController, item = item
                    )
                }

            }
        }


        //flutter jobs
        if (parameterSearch.contains("flutter", ignoreCase = true)) {
            items(mainArrayData) { item: DataJobItem ->
                if (item.title.lowercase().contains(parameterSearch) || item.title.lowercase()
                        .contains("dart", ignoreCase = true)
                ) {
                    DisplayDataItem(
                        suggestIcon = findIcon(item.title),
                        navController = navController, item = item
                    )
                }

            }
        }

        //data science jobs
        if (parameterSearch.contains("data", ignoreCase = true)) {
            items(mainArrayData) { item: DataJobItem ->
                if (item.title.lowercase()
                        .contains(parameterSearch, ignoreCase = true) || item.title.lowercase()
                        .contains("data-analyst", ignoreCase = true) || item.title.lowercase()
                        .contains(
                            "data analyst", ignoreCase = true
                        ) || item.title.lowercase().contains(
                        "data science", ignoreCase = true
                    ) || item.title.lowercase().contains(
                        "data-science", ignoreCase = true
                    )
                ) {
                    DisplayDataItem(
                        suggestIcon = findIcon(item.title),
                        navController = navController, item = item
                    )
                }

            }
        }

        //frontend jobs
        if (parameterSearch.contains("frontend", ignoreCase = true)) {
            items(mainArrayData) { item: DataJobItem ->
                if (item.title.lowercase().contains(parameterSearch) || item.title.lowercase()
                        .contains("front-end", ignoreCase = true) || item.title.lowercase()
                        .contains("front", ignoreCase = true)
                ) {
                    DisplayDataItem(
                        suggestIcon = findIcon(item.title),
                        navController = navController, item = item
                    )
                }

            }
        }

        //fullstack developer jobs
        if (parameterSearch.contains("fullstack", ignoreCase = true)) {
            items(mainArrayData) { item: DataJobItem ->
                if (item.title.lowercase().contains(parameterSearch) || item.title.lowercase()
                        .contains("fullstack", ignoreCase = true) || item.title.lowercase()
                        .contains("full-stack", ignoreCase = true) || item.title.lowercase()
                        .contains("full stack", ignoreCase = true) || item.title.lowercase()
                        .contains("full", ignoreCase = true)
                ) {
                    DisplayDataItem(
                        suggestIcon = findIcon(item.title),
                        navController = navController, item = item
                    )
                }

            }
        }

        //game developer jobs
        if (parameterSearch.contains("game", ignoreCase = true)) {
            items(mainArrayData) { item: DataJobItem ->
                if (item.title.lowercase().contains(parameterSearch) || item.title.lowercase()
                        .contains("unity", ignoreCase = true) || item.title.lowercase()
                        .contains("unreal", ignoreCase = true)
                ) {
                    DisplayDataItem(
                        suggestIcon = findIcon(item.title),
                        navController = navController, item = item
                    )
                }

            }
        }

        //IOS developer jobs 
        if (parameterSearch.contains("ios", ignoreCase = true)) {
            items(mainArrayData) { item: DataJobItem ->
                if (item.title.lowercase().contains(parameterSearch) || item.title.lowercase()
                        .contains("iphone", ignoreCase = true) || item.title.lowercase()
                        .contains("swift", ignoreCase = true) || item.title.lowercase()
                        .contains("objective", ignoreCase = true) || item.title.lowercase()
                        .contains("objective-c", ignoreCase = true) || item.title.lowercase()
                        .contains("objective c") || item.title.lowercase().contains("xcode")
                ) {
                    DisplayDataItem(
                        suggestIcon = findIcon(item.title),
                        navController = navController, item = item
                    )
                }

            }
        }

        //laravel jobs and php
        if (parameterSearch.contains("laravel", ignoreCase = true)) {
            items(mainArrayData) { item: DataJobItem ->
                if (item.title.lowercase().contains(parameterSearch) || item.title.lowercase()
                        .contains("php", ignoreCase = true) || item.title.lowercase()
                        .contains("symfony", ignoreCase = true) || item.title.lowercase()
                        .contains("cup-cake", ignoreCase = true) || item.title.lowercase()
                        .contains("cupcake", ignoreCase = true)
                ) {
                    DisplayDataItem(
                        suggestIcon = findIcon(item.title),
                        navController = navController, item = item
                    )
                }

            }
        }

        //java developer
        if (parameterSearch.contains(
                "java", ignoreCase = true
            ) && !parameterSearch.contains("javascript", ignoreCase = true)
        ) {
            val notString = "javascript"
            items(mainArrayData) { item: DataJobItem ->
                if (item.title.lowercase().contains(parameterSearch) || item.title.lowercase()
                        .contains("springboot", ignoreCase = true) || item.title.lowercase()
                        .contains("spring", ignoreCase = true) || item.title.lowercase()
                        .contains("spring-boot", ignoreCase = true)
                ) {
                    if (!item.title.lowercase().contains(notString)) {
                        DisplayDataItem(
                            suggestIcon = findIcon(item.title),
                            navController = navController, item = item
                        )
                    }
                }

            }
        }

        //javascript developer
        if (parameterSearch.contains("javascript", ignoreCase = true)) {

            items(mainArrayData) { item: DataJobItem ->
                if (item.title.lowercase().contains(parameterSearch)) {
                    DisplayDataItem(
                        suggestIcon = findIcon(item.title),
                        navController = navController, item = item
                    )
                }

            }
        }

        //networking jobs
        if (parameterSearch.contains("network", ignoreCase = true)) {
            items(mainArrayData) { item: DataJobItem ->
                if (item.title.lowercase().contains(parameterSearch) || item.title.lowercase()
                        .contains("cisco", ignoreCase = true) || item.title.lowercase()
                        .contains("huawei", ignoreCase = true) || item.title.lowercase()
                        .contains("dhcp", ignoreCase = true) || item.title.lowercase()
                        .contains("firewall", ignoreCase = true)
                ) {
                    DisplayDataItem(
                        suggestIcon = findIcon(item.title),
                        navController = navController, item = item
                    )
                }

            }
        }

        //next js jobs
        if (parameterSearch.contains(
                "nextjs", ignoreCase = true
            )
        ) {
            items(mainArrayData) { item: DataJobItem ->
                if (item.title.lowercase().contains(parameterSearch) || item.title.lowercase()
                        .contains("next.js", ignoreCase = true)
                ) {
                    DisplayDataItem(
                        suggestIcon = findIcon(item.title),
                        navController = navController, item = item
                    )
                }

            }
        }

        //python django jobs
        if (parameterSearch.contains("django", ignoreCase = true)) {
            items(mainArrayData) { item: DataJobItem ->
                if (item.title.lowercase().contains(parameterSearch)) {
                    DisplayDataItem(
                        suggestIcon = findIcon(item.title),
                        navController = navController, item = item
                    )
                }

            }
        }

        //python flask jobs
        if (parameterSearch.contains("flask", ignoreCase = true)) {
            items(mainArrayData) { item: DataJobItem ->
                if (item.title.lowercase().contains(parameterSearch)) {
                    DisplayDataItem(
                        suggestIcon = findIcon(item.title),
                        navController = navController, item = item
                    )
                }

            }
        }

        //react jobs
        if (parameterSearch.contains(
                "react", ignoreCase = true
            ) && !parameterSearch.contains("reactnative", ignoreCase = true)
        ) {
            items(mainArrayData) { item: DataJobItem ->
                if (item.title.lowercase().contains(parameterSearch)) {
                    DisplayDataItem(
                        suggestIcon = findIcon(item.title),
                        navController = navController, item = item
                    )
                }

            }
        }

        //reactnative jobs
        if (parameterSearch.contains("reactnative", ignoreCase = true)) {
            items(mainArrayData) { item: DataJobItem ->
                if (item.title.lowercase().contains(parameterSearch) || item.title.lowercase()
                        .contains("react-native") || item.title.lowercase().contains("react native")
                ) {
                    DisplayDataItem(
                        suggestIcon = findIcon(item.title),
                        navController = navController, item = item
                    )
                }

            }
        }

        //system analyst jobs
        if (parameterSearch.contains("system", ignoreCase = true)) {
            items(mainArrayData) { item: DataJobItem ->
                if (item.title.lowercase().contains(parameterSearch)) {
                    DisplayDataItem(
                        suggestIcon = findIcon(item.title),
                        navController = navController, item = item
                    )
                }

            }
        }

        //UI_UX designer analyst jobs
        if (parameterSearch.contains("ui_ux", ignoreCase = true)) {
            val notString = "linux"
            items(mainArrayData) { item: DataJobItem ->
                if (!item.title.lowercase().contains(notString) && (item.title.lowercase()
                        .contains("designer") || item.title.lowercase()
                        .contains("ux") || item.title.lowercase()
                        .contains("ui/ux") || item.title.lowercase().contains(parameterSearch)
                            )
                ) {

                    DisplayDataItem(
                        suggestIcon = findIcon(item.title),
                        navController = navController, item = item
                    )

                }

            }
        }


        //node.js jobs
        if (parameterSearch.contains("nodejs", ignoreCase = true)) {
            items(mainArrayData) { item: DataJobItem ->
                if (item.title.lowercase().contains(parameterSearch) || item.title.lowercase()
                        .contains("node.js", ignoreCase = true)
                ) {
                    DisplayDataItem(
                        suggestIcon = findIcon(item.title),
                        navController = navController, item = item
                    )
                }

            }
        }


        //docker and kubernetes
        if (parameterSearch.contains("docker", ignoreCase = true)) {
            items(mainArrayData) { item: DataJobItem ->
                if (item.title.lowercase().contains(parameterSearch) || item.title.lowercase()
                        .contains("kuber", ignoreCase = true)
                ) {
                    DisplayDataItem(
                        suggestIcon = findIcon(item.title),
                        navController = navController, item = item
                    )
                }

            }
        }

        //kotlin jobs
        if (parameterSearch.contains("kotlin", ignoreCase = true)) {
            items(mainArrayData) { item: DataJobItem ->
                if (item.title.lowercase().contains(parameterSearch) || item.title.lowercase()
                        .contains("ktor", ignoreCase = true)
                ) {
                    DisplayDataItem(
                        suggestIcon = findIcon(item.title),
                        navController = navController, item = item
                    )
                }

            }
        }


        //golang jobs
        if (parameterSearch.contains("golang", ignoreCase = true)) {
            items(mainArrayData) { item: DataJobItem ->
                if (item.title.lowercase().contains(parameterSearch) || item.title.lowercase()
                        .contains("google go", ignoreCase = true)
                ) {
                    DisplayDataItem(
                        suggestIcon = findIcon(item.title),
                        navController = navController, item = item
                    )
                }

            }
        }


        //kotlin jobs
        if (parameterSearch.contains("python", ignoreCase = true)) {
            items(mainArrayData) { item: DataJobItem ->
                if (item.title.lowercase().contains(parameterSearch) || item.title.lowercase()
                        .contains("python", ignoreCase = true)
                ) {
                    DisplayDataItem(
                        suggestIcon = findIcon(item.title),
                        navController = navController, item = item
                    )
                }

            }
        }

        //kotlin jobs
        if (parameterSearch.contains("linux", ignoreCase = true)) {
            items(mainArrayData) { item: DataJobItem ->
                if (item.title.lowercase().contains(parameterSearch) || item.title.lowercase()
                        .contains("linux", ignoreCase = true) || item.title.lowercase()
                        .contains("ubuntu", ignoreCase = true)
                ) {
                    DisplayDataItem(
                        suggestIcon = findIcon(item.title),
                        navController = navController,
                        item = item
                    )
                }

            }
        }

    }
}

/*
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComposableSearchBar(mainArrayData: List<DataJobItem>) {
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.TopCenter) {
        var activeStateSearch by rememberSaveable {
            mutableStateOf(false)
        }
        var queryText by rememberSaveable {
            mutableStateOf("")
        }

        DockedSearchBar(query = queryText, onQueryChange = {
            queryText = it
        }, onSearch = { searchString ->
            queryText = searchString
        }, active = activeStateSearch, onActiveChange = {
            activeStateSearch = it
        },

            leadingIcon = {
                IconButton(onClick = { *//*TODO*//* }) {
                    Icon(imageVector = Icons.Filled.Search, contentDescription = "search icon")
                }
            }, placeholder = {
                Text(text = "search job: react, django, mobile, AI ..")
            }, modifier = Modifier.fillMaxWidth()

        ) {
            mainArrayData.takeLast(mainArrayData.size - 1).forEach {
                if (it.title.lowercase().contains(queryText)) {
                    ListItem(headlineContent = {
                        DisplayDataItem(
                            title = it.title,
                            organisation = it.organisation,
                            link = it.link,
                            suggestIcon = R.drawable.sharp
                        )
                    })
                }
            }
        }
    }
}*/

@Composable
fun DisplayDataItem(
    suggestIcon: Int,
    navController: NavHostController,
    item: DataJobItem
) {
    Card(elevation = CardDefaults.cardElevation(5.dp), modifier = Modifier.padding(8.dp)) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(5.dp)
                .fillMaxSize()
                .height(120.dp)
        ) {
            //leading text or can replaced with an image or card
            Image(
                painter = painterResource(id = suggestIcon),
                contentDescription = "icon",
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .weight(0.26f)
                    .border(width = 20.dp, shape = CircleShape, color = Color.White),
                contentScale = ContentScale.Crop,

                )
            Spacer(modifier = Modifier.width(8.dp))

            //column for title and description
            Column(
                modifier = Modifier.weight(0.74f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                //title text
                Text(
                    text = item.title,
                    fontWeight = FontWeight.Bold,
                    fontStyle = MaterialTheme.typography.titleSmall.fontStyle, maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
                Spacer(modifier = Modifier.height(5.dp))
                //organisation Text
                Text(
                    text = item.organisation.uppercase(),
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.Light,
                    maxLines = 1,
                )
                Spacer(modifier = Modifier.height(5.dp))

                //button apply
                OutlinedButton(
                    onClick = {
                        //navigate to the job application screen with item object passed as backStack Entry
                        funNavigateJobApplicationScreen(item, navController)
                    }, modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Apply")
                }
            }


        }
    }
}

//triggers navigation to the job application window
fun funNavigateJobApplicationScreen(item: DataJobItem, navController: NavHostController) {
    //putting the job parcel into the back stack entry
    navController.currentBackStackEntry?.savedStateHandle?.set(
        key = ObjectConstants.KEY_ITEM_OBJECT,
        value = item
    )
    //begin route navigation
    navController.navigate(route = Routes.SCREEN_JOB_APPLICATION)
}


/*@Preview
@Composable
private fun SearchIconPreview() {
    val listPreview = listOf<DataJobItem>(
        DataJobItem(
            title = "Android Developer", link = "www", organisation = "BlackSpot"
        ),

        DataJobItem(
            title = "Microservices Developer", link = "www", organisation = "Micro"
        ),
        DataJobItem(
            title = "React Developer", link = "www", organisation = "ReactOrg"
        ),
        DataJobItem(
            title = "Django Developer", link = "www", organisation = "DjangoInc"
        ),
        DataJobItem(
            title = "Android Developer", link = "www", organisation = "MMIC Corp"
        ),
        DataJobItem(
            title = "Android Developer", link = "www", organisation = "MacMillan"
        ),

        )

    ComposableSearchBar(listPreview)
}*/
