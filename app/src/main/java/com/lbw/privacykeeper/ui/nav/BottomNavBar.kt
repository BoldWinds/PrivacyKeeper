package com.lbw.privacykeeper.ui.nav

import android.content.res.Configuration
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.lbw.privacykeeper.ui.theme.PrivacyKeeperTheme

@Composable
fun BottomNavBar(navController : NavHostController) {
    val screens = listOf(
        AppScreen.Password,
        AppScreen.Image,
        AppScreen.Video,
        AppScreen.User
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()

    val currentDestination = navBackStackEntry?.destination


    NavigationBar(
        modifier = Modifier.padding(all = 2.dp),
        tonalElevation = 4.dp,
    ) {
        screens.forEachIndexed { _, screen ->
            NavigationBarItem(
                label = {
                    Text(text = screen.title)
                },
                icon = {
                    Icon(
                        imageVector = screen.icon,
                        contentDescription = "Navigation Icon"
                    )
                },
                selected = currentDestination?.hierarchy?.any{
                    it.route == screen.route
                } == true,
                onClick = {
                    navController.navigate(screen.route)
                },
                alwaysShowLabel = false
            )
        }
    }
}


@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewAppNavBar() {
    PrivacyKeeperTheme() {
        val navController = rememberNavController()
        BottomNavBar(navController = navController)
    }
}

