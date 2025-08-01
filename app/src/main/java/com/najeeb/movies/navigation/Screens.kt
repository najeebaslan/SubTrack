package com.najeeb.movies.navigation

sealed class Screen(val route: String) {
  data object Home : Screen("home")
  data object Profile : Screen("profile")
  data object Settings : Screen("settings/{userId}") {
    fun createRoute(userId: String) = "settings/$userId"
  }
}