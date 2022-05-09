package com.lbw.privacykeeper.ui.nav

import privacykeeperv1.R

sealed class AppScreen(
    val route : String,
    val title : String,
    val icon : Int
){
    object Password : AppScreen(
        route = "password",
        title = "password",
        icon = R.drawable.ic_lock_fill0_wght400_grad0_opsz48
    )

    object Image : AppScreen(
        route = "image",
        title = "image",
        icon = R.drawable.ic_image1_foreground
    )

    object Video : AppScreen(
        route = "video",
        title = "video",
        icon = R.drawable.ic_video_foreground
    )

    object User : AppScreen(
        route = "user",
        title = "user",
        icon = R.drawable.ic_person_foreground
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



