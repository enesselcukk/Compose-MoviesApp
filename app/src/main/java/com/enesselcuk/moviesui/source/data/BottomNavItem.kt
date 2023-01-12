package com.enesselcuk.moviesui.source.data

import com.enesselcuk.moviesui.R

data class BottomNavItem(
    val label: String,
    val icon: Int,
    val route: String
)

val bottomNavItem = listOf(
    BottomNavItem(label = "Home", icon = R.drawable.home_bottom_bar, "home"),
    BottomNavItem(label = "Search", icon = R.drawable.search_bottom, "search"),
    BottomNavItem(label = "Liked", R.drawable.bottom_not_favorite, "liked"),
    BottomNavItem(label = "Profile", R.drawable.profile, "profile")
)
