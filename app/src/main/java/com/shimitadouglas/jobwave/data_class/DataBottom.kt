package com.shimitadouglas.jobwave.data_class

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector

data class DataBottom(
    val title: String,
    val selectedImage: ImageVector,
    val unselectedImage: ImageVector,
)


fun getBottomItemList(): MutableList<DataBottom> {

    return mutableListOf(
        DataBottom(
            title = "Home",
            selectedImage = Icons.Filled.Home,
            unselectedImage = Icons.Outlined.Home
        ),
        DataBottom(
            title = "notification",
            selectedImage = Icons.Filled.Notifications,
            unselectedImage = Icons.Outlined.Notifications
        ),
        DataBottom(
            title = "Settings",
            selectedImage = Icons.Filled.Settings,
            unselectedImage = Icons.Outlined.Settings
        ),
        DataBottom(
            title = "Login",
            selectedImage = Icons.Filled.AccountCircle,
            unselectedImage = Icons.Outlined.AccountCircle
        ),


        )
}