package com.enesselcuk.moviesui.util

import com.enesselcuk.moviesui.R
import com.enesselcuk.moviesui.util.NavigationItem.AUTH_SIGN
import com.enesselcuk.moviesui.util.NavigationItem.HOME
import com.enesselcuk.moviesui.util.NavigationItem.LIKED
import com.enesselcuk.moviesui.util.NavigationItem.MOVIES_NAME
import com.enesselcuk.moviesui.util.NavigationItem.MOVIE_ID
import com.enesselcuk.moviesui.util.NavigationItem.PEOPLE_DETAIL
import com.enesselcuk.moviesui.util.NavigationItem.PLAYER_ID
import com.enesselcuk.moviesui.util.NavigationItem.PROFILE
import com.enesselcuk.moviesui.util.NavigationItem.SEARCH
import com.enesselcuk.moviesui.util.NavigationItem.TV_ID


data class BottomNavItem(
    val label: Int,
    val icon: Int,
    val route: String
)

val bottomNavItem = listOf(
    BottomNavItem(label = R.string.home_title, icon = R.drawable.home_bottom_bar, HOME),
    BottomNavItem(label = R.string.search, icon = R.drawable.search_bottom, SEARCH),
    BottomNavItem(label = R.string.likeds_title, R.drawable.bottom_not_favorite, LIKED),
    BottomNavItem(label = R.string.profile_title, R.drawable.person, PROFILE)
)


object NavigationItem {
    const val MOVIE_ID = "movieId"
    const val TV_ID = "movieId"
    const val SIGN_IN = "SignIn"
    const val REGISTER = "Register"
    const val HOME = "home"
    const val SEARCH = "search"
    const val LIKED = "liked"
    const val PROFILE = "profile"
    const val MOVIES_NAME = "name"
    const val ALL_SEE = "all_see"
    const val PEOPLE_DETAIL = "people_detail"
    const val SPLASH = "splash"
    const val SETTINGS = "settings"
    const val PLAYER = "player"
    const val PLAYER_ID = "playersId"
    const val AUTH_SIGN = "authSign"
    const val BOTTOM_SHEET = "bottom"
}

sealed class Screen(val route: String) {
    object Detail : Screen(route = "detail/{$MOVIE_ID}")
    object AllSee : Screen(route = "AllMovies/{$MOVIES_NAME}")
    object PEOPLE : Screen(route = "people_detail/{$PEOPLE_DETAIL}")
    object TvDetail : Screen("tvDetail/{$TV_ID}")
    object Player : Screen(route = "player_Id/${PLAYER_ID}")
    object Auth : Screen(route = "AuthLogin/{${AUTH_SIGN}}")

}
