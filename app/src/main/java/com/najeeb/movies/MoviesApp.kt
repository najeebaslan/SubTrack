package com.najeeb.movies
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.najeeb.movies.navigation.MovieNavigationBar
import com.najeeb.movies.navigation.NavHostCompose

@Composable
fun MoviesApp() {
  val navController = rememberNavController()
  Scaffold(
    bottomBar = { MovieNavigationBar(navController) }
  ) { innerPadding ->
    NavHostCompose(navController, innerPadding)

  }
}