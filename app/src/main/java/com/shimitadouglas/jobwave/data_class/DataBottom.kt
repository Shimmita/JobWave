package com.shimitadouglas.jobwave.data_class

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.key.Key.Companion.Home

data class DataBottom(
    val title: String,
    val selectedImage: ImageVector,
    val unselectedImage: ImageVector,
    val isSelected: Boolean
)


fun getBottomItemList(): MutableList<DataBottom> {

    return mutableListOf(
        DataBottom(
            title = "Home",
            selectedImage = Icons.Filled.Home,
            unselectedImage = Icons.Outlined.Home, isSelected = false
        ),
        DataBottom(
            title = "Account",
            selectedImage = Icons.Filled.AccountCircle,
            unselectedImage = Icons.Outlined.AccountCircle, isSelected = false
        ),
        DataBottom(
            title = "Settings",
            selectedImage = Icons.Filled.Settings,
            unselectedImage = Icons.Outlined.Settings, isSelected = false
        ),

        )
}