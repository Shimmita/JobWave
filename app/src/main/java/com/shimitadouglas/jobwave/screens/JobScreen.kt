package com.shimitadouglas.jobwave.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.DockedSearchBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.shimitadouglas.jobwave.R
import com.shimitadouglas.jobwave.base_url.BaseUrl
import com.shimitadouglas.jobwave.data_class.DataJobItem
import com.shimitadouglas.jobwave.data_class.DataJobResponse
import com.shimitadouglas.jobwave.handlers.ProgressDialogLoading
import com.shimitadouglas.jobwave.interface_service.GetAllPostInterface
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
            .padding(paddingValues).padding(vertical = 10.dp)
    ) {

        //logging the selected job from the main via nav args
        Log.d("ARGS", "JobsScreen: $parameterSearch")

        //
        var mainArrayData by rememberSaveable {
            mutableStateOf(emptyList<DataJobItem>())
        }

        //init the retrofit buildr
        val retrofitBuilder =
            Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BaseUrl.baseUrl).build().create(GetAllPostInterface::class.java)

        val retrofitData = retrofitBuilder.getAllJobs()

        //enqueue the retrofit data
        retrofitData.enqueue(object : Callback<DataJobResponse?> {

            //success response
            override fun onResponse(
                call: Call<DataJobResponse?>,
                response: Response<DataJobResponse?>
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

            //display search bar only if fab is clicked
            if (isSearchShown) {
                ComposableSearchBar()
            }

            //using Lazy Column To load data stored in the array
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(mainArrayData) { item: DataJobItem ->

                    DisplayDataItem(
                        title = item.title,
                        organisation = item.organisation,
                        link = item.link,
                        suggestIcon = R.drawable.nodejs
                    )


                }
            }


        } else {
            //display progress
            ProgressDialogLoading()
        }

    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComposableSearchBar() {
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.TopCenter) {
        DockedSearchBar(
            query = "",
            onQueryChange = { "" },
            onSearch = {

            },
            active = true,
            onActiveChange = {},
            leadingIcon = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Filled.Search, contentDescription = "search icon")
                }
            },
            placeholder = {
                Text(text = "search android, node, django, react ")
            },
            modifier = Modifier.height(
                50.dp
            )
        ) {

        }
    }
}

@Composable
fun DisplayDataItem(title: String, organisation: String, link: String, suggestIcon: Int) {
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
                painter = painterResource(id = R.drawable.cpp),
                contentDescription = "icon",
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .weight(0.26f),
                contentScale = ContentScale.Crop,
            )
            Spacer(modifier = Modifier.width(10.dp))

            //column for title and description
            Column(
                modifier = Modifier.weight(0.74f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                //title text
                Text(
                    text = title,
                    fontWeight = FontWeight.Bold,
                    fontStyle = MaterialTheme.typography.titleSmall.fontStyle, maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
                Spacer(modifier = Modifier.height(5.dp))
                //organisation Text
                Text(
                    text = organisation.uppercase(),
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.Light,
                    maxLines = 1,
                )
                Spacer(modifier = Modifier.height(5.dp))

                //button apply
                OutlinedButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(text = "Apply")
                }
            }


        }
    }
}


/*
@Preview
@Composable
private fun PreviewItem() {
    DisplayDataItem(
        title = "IOS Application Developer",
        organisation = "Palmer Enterprises",
        "www",
        suggestIcon = "IOS"
    )
}*/
