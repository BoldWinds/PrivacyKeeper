package com.lbw.privacykeeper.ui.nav

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector

sealed class AppScreen(
    val route : String,
    val title : String,
    val icon : ImageVector
){
    object Password : AppScreen(
        route = "password",
        title = "password",
        icon = Icons.Default.Lock
    )

    object Image : AppScreen(
        route = "image",
        title = "image",
        icon = Icons.Default.Phone
    )

    object Video : AppScreen(
        route = "video",
        title = "video",
        icon = Icons.Default.Star
    )

    object User : AppScreen(
        route = "user",
        title = "user",
        icon = Icons.Default.Person
    )
}

sealed class AppSecondaryScreen(
    val route: String
){
    object Password : AppSecondaryScreen(
        route = "secondary_password"
    )

    object Image : AppSecondaryScreen(
        route = "secondary_image"
    )

    object Video : AppSecondaryScreen(
        route = "secondary_video"
    )

}

sealed class AppTertiaryScreen(
    val route: String
){
    object Image : AppTertiaryScreen(
        route = "tertiary_image"
    )

    object Video : AppTertiaryScreen(
        route = "tertiary_video"
    )

    fun withArgs(vararg args : String):String{
        return buildString {
            append(route)
            args.forEach {arg->
                append("/$arg")
            }
        }
    }
}



