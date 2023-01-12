package com.enesselcuk.moviesui.screens

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.enesselcuk.moviesui.screens.homeSceen.HomeScreen
import com.enesselcuk.moviesui.source.data.bottomNavItem
import com.enesselcuk.moviesui.screens.likedScreen.LikedScreen
import com.enesselcuk.moviesui.screens.profile.ProfileScreen
import com.enesselcuk.moviesui.screens.searchScreen.SearchScreen
import com.enesselcuk.moviesui.ui.theme.MoviesUiTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MoviesUiTheme {
                val navController = rememberNavController()

                Surface(color = Color.White) {
                    Scaffold(bottomBar = {
                        BottomNavigationBar(navController = navController)
                    }, content = { padding ->
                        NavHostContainer(
                            navController = navController,
                            paddingValues = padding,
                            context = baseContext
                        )
                    })
                }
            }
        }
    }
}


@Composable
private fun NavHostContainer(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    paddingValues: PaddingValues,
    context: Context
) {
    NavHost(navController = navController,
        startDestination = "home",
        modifier = modifier.padding(paddingValues = paddingValues),
        builder = {
            composable("home") {
                HomeScreen(context = context)
            }
            composable("search") {
                SearchScreen()
            }
            composable("liked") {
                LikedScreen()
            }
            composable("profile") {
                ProfileScreen()
            }

        })
}


@Composable
fun BottomNavigationBar(navController: NavHostController) {
    BottomNavigation(backgroundColor = Color.Black) {
        // observe the backstack
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        bottomNavItem.onEach { navItem ->
            BottomNavigationItem(
                /**
                 * selected -> currentRoute seçilen route'aya eşittir.
                 */
                selected = currentRoute == navItem.route,
                onClick = { navController.navigate(navItem.route) },
                selectedContentColor = Color.White,
                icon = {
                    Icon(
                        painter = painterResource(id = navItem.icon),
                        contentDescription = navItem.label,
                        tint = Color.White,
                    )
                },
                label = {
                    Text(text = navItem.label)
                },
                alwaysShowLabel = false
            )
        }
    }
}
