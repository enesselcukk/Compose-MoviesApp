package com.enesselcuk.moviesui.navigation


import android.content.Intent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat.startActivity
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.enesselcuk.moviesui.ui.screen.activity.MainActivity
import com.enesselcuk.moviesui.ui.screen.likedScreen.LikedScreen
import com.enesselcuk.moviesui.ui.screen.movie.SharedViewModel
import com.enesselcuk.moviesui.ui.screen.movie.actorDetailScreen.ActorScreen
import com.enesselcuk.moviesui.ui.screen.movie.allSee.AllSeeScreenHome
import com.enesselcuk.moviesui.ui.screen.movie.homeSceen.HomeScreen
import com.enesselcuk.moviesui.ui.screen.movie.moviesdetailsScreen.DetailsScreen
import com.enesselcuk.moviesui.ui.screen.profile.ProfileScreen
import com.enesselcuk.moviesui.ui.screen.searchScreen.SearchScreen
import com.enesselcuk.moviesui.ui.screen.settings.SettingsScreen
import com.enesselcuk.moviesui.ui.screen.tv.TvDetailScreen
import com.enesselcuk.moviesui.util.NavigationItem.HOME
import com.enesselcuk.moviesui.util.NavigationItem.LIKED
import com.enesselcuk.moviesui.util.NavigationItem.MOVIES_NAME
import com.enesselcuk.moviesui.util.NavigationItem.MOVIE_ID
import com.enesselcuk.moviesui.util.NavigationItem.PEOPLE_DETAIL
import com.enesselcuk.moviesui.util.NavigationItem.PLAYER_ID
import com.enesselcuk.moviesui.util.NavigationItem.PROFILE
import com.enesselcuk.moviesui.util.NavigationItem.SEARCH
import com.enesselcuk.moviesui.util.NavigationItem.SETTINGS
import com.enesselcuk.moviesui.util.NavigationItem.TV_ID
import com.enesselcuk.moviesui.util.Screen


@Composable
fun NavHostContainer(
    navController: NavHostController,
    paddingValues: PaddingValues,
    callbackLogin:() -> Unit
) {
    val context = LocalContext.current
    val sharedViewModel = hiltViewModel<SharedViewModel>()
    NavHost(navController = navController,
        startDestination = HOME,
        modifier = Modifier.padding(paddingValues = paddingValues),
        builder = {

            composable(HOME) {
                HomeScreen(
                    isVisibleBottom = { sharedViewModel.isBottomNavVisible.value = it },
                    isVisibleTopBar = { sharedViewModel.isTopVisible.value = it },
                    isVisibleTopBarBack = { sharedViewModel.isTopBackVisible.value = it },
                    screenName = { sharedViewModel.isScreenName.value = it },
                    click = { navController.navigate("AllMovies/${it}") },
                    clickID = { navController.navigate("detail/${it}") },
                    clickIdToMovie = { navController.navigate("detail/${it}") },
                    clickIdToTv = { navController.navigate("tvDetail/${it}") },
                    isActionInTopBar = { sharedViewModel.isActionInTopBar.value = it }
                )
            }
            composable(SEARCH) {
                SearchScreen(
                    navController = navController,
                    peopleClick = { navController.navigate("people_detail/${it}") },
                    isVisibleBottom = { sharedViewModel.isBottomNavVisible.value = it },
                    isVisibleTopBar = { sharedViewModel.isTopVisible.value = it },
                    isVisibleTopBarBack = { sharedViewModel.isTopBackVisible.value = it },
                    screenName = { sharedViewModel.isScreenName.value = it },
                    isActionInTopBar = { sharedViewModel.isActionInTopBar.value = it }
                )
            }
            composable(LIKED) {
                LikedScreen(
                    isVisibleBottom = { sharedViewModel.isBottomNavVisible.value = it },
                    isVisibleTopBar = { sharedViewModel.isTopVisible.value = it },
                    isVisibleTopBarBack = { sharedViewModel.isTopBackVisible.value = it },
                    isChooseTopBar = { sharedViewModel.isSelect.value = it },
                    screenName = { sharedViewModel.isScreenName.value = it },
                    isActionInTopBar = { sharedViewModel.isActionInTopBar.value = it },
                    sharedViewModel = sharedViewModel,
                    toDetail = { navController.navigate("detail/${it}") },
                    toDetailTv = { navController.navigate("tvDetail/${it}") }
                )
            }
            composable(PROFILE) {
                ProfileScreen(
                    isVisibleBottom = { sharedViewModel.isBottomNavVisible.value = it },
                    isVisibleTopBar = { sharedViewModel.isTopVisible.value = it },
                    isVisibleTopBarBack = { sharedViewModel.isTopBackVisible.value = it },
                    screenName = { sharedViewModel.isScreenName.value = it },
                    isActionInTopBar = { sharedViewModel.isActionInTopBar.value = it },
                    isChooseLiked = { sharedViewModel.isSelect.value = it },
                    isVisibleSetting = { sharedViewModel.isVisibleSetting.value = it },
                    goToLogin = { callbackLogin() },
                    sharedViewModel = sharedViewModel,
                    goToSettings = { navController.navigate(SETTINGS) { launchSingleTop = true } }
                )
            }

            composable(SETTINGS) {
                SettingsScreen(
                    isVisibleBottom = { sharedViewModel.isBottomNavVisible.value = it },
                    isVisibleTopBar = { sharedViewModel.isTopVisible.value = it },
                    isVisibleTopBarBack = { sharedViewModel.isTopBackVisible.value = it },
                    screenName = { sharedViewModel.isScreenName.value = it },
                    isActionInTopBar = { sharedViewModel.isActionInTopBar.value = it },
                    isChooseLiked = { sharedViewModel.isSelect.value = it },
                    isVisibleSettings = { sharedViewModel.isVisibleSetting.value = it },
                    sharedViewModel = sharedViewModel,
                )
            }

            composable(
                route = Screen.PEOPLE.route,
                arguments = listOf(navArgument(PEOPLE_DETAIL) { type = NavType.IntType })
            ) {
                val bundle = it.arguments?.getInt(PEOPLE_DETAIL) ?: 0
                ActorScreen(
                    id = bundle,
                    isVisibleBottom = { sharedViewModel.isBottomNavVisible.value = it },
                    isVisibleTopBar = { sharedViewModel.isTopVisible.value = it },
                    isVisibleTopBarBack = { sharedViewModel.isTopBackVisible.value = it },
                    screenName = { sharedViewModel.isScreenName.value = it },
                    itemClick = { navController.navigate("detail/${it}") },
                    itemTvClick = { navController.navigate("tvDetail/${it}") },
                    isActionInTopBar = { sharedViewModel.isActionInTopBar.value = it }
                )
            }

            composable(
                route = Screen.AllSee.route,
                arguments = listOf(navArgument(MOVIES_NAME) { type = NavType.StringType }),

                ) {
                val getBundle = it.arguments?.getString(MOVIES_NAME).orEmpty()
                AllSeeScreenHome(
                    titleName = getBundle,
                    isActionInTopBar = { sharedViewModel.isActionInTopBar.value = it })
            }

            composable(
                route = Screen.Detail.route,
                arguments = listOf(navArgument(MOVIE_ID) { type = NavType.IntType })
            ) {
                val getBundle = it.arguments?.getInt(MOVIE_ID) ?: 0
                DetailsScreen(
                    moviesId = getBundle,
                    isVisibleBottom = { sharedViewModel.isBottomNavVisible.value = it },
                    isVisibleTopBar = { sharedViewModel.isTopVisible.value = it },
                    isVisibleTopBarBack = { sharedViewModel.isTopBackVisible.value = it },
                    screeName = { sharedViewModel.isScreenName.value = it },
                    isActionInTopBar = { sharedViewModel.isActionInTopBar.value = it },
                    sharedViewModel = sharedViewModel,
                    onClick = { navController.navigate(PLAYER_ID) }
                )
            }

            composable(
                route = Screen.TvDetail.route,
                arguments = listOf(navArgument(TV_ID) { type = NavType.IntType })
            ) {
                val getId = it.arguments?.getInt(TV_ID) ?: 0
                TvDetailScreen(
                    tvId = getId,
                    isActionInTopBar = { sharedViewModel.isActionInTopBar.value = it },
                    isVisibleBottom = { sharedViewModel.isBottomNavVisible.value = it },
                    isVisibleTopBar = { sharedViewModel.isTopVisible.value = it },
                    isVisibleTopBarBack = { sharedViewModel.isTopBackVisible.value = it },
                    screenName = { sharedViewModel.isScreenName.value = it },
                    sharedViewModel = sharedViewModel,
                    clickTvId = { navController.navigate(PLAYER_ID) }
                )
            }

            composable(PLAYER_ID) {
                //MoviesOrTvScreenPlayer(sharedViewModel)
            }

            /*
                        composable(
                            route = Screen.Player.route,
                            arguments = listOf(navArgument(PLAYER_ID) { type = NavType.IntType })
                        ) {
                            val getUrl = it.arguments?.getInt(PLAYER_ID) ?: 0
                            MoviesOrTvScreenPlayer(playerId = getUrl)
                        }*/

        })

}