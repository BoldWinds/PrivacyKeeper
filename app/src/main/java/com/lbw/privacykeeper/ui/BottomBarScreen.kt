package com.lbw.privacykeeper.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val route : String,
    val title : String,
    val icon : ImageVector
){
    object Password : BottomBarScreen(
        route = "password",
        title = "password",
        icon = Icons.Default.Lock
    )

    object Image : BottomBarScreen(
        route = "image",
        title = "image",
        icon = Icons.Default.Phone
    )

    object Video : BottomBarScreen(
        route = "video",
        title = "video",
        icon = Icons.Default.Star
    )

    object User : BottomBarScreen(
        route = "user",
        title = "user",
        icon = Icons.Default.Person
    )
}



