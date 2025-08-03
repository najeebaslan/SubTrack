@file:OptIn(ExperimentalMaterial3Api::class)

package com.najeeb.movies

import android.content.Intent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.najeeb.movies.screens.addExpense.AddExpenseScreen
import com.najeeb.movies.screens.home.HomeScreen
import com.najeeb.movies.screens.navigation.MovieNavigationBar
import com.najeeb.movies.screens.profile.ProfileScreen
import com.najeeb.movies.screens.statistic.StatisticScreen
import com.najeeb.movies.screens.wallet.WalletScreen

@Composable
fun MoviesApp() {

  val navController = rememberNavController()
  Scaffold(
    contentWindowInsets = WindowInsets(0),
    bottomBar = { MovieNavigationBar(navController) },

    floatingActionButton = {
      FloatingActionButton(
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = Color.White,
        elevation =
          FloatingActionButtonDefaults.bottomAppBarFabElevation(
            defaultElevation = 10.dp,
            pressedElevation = 30.dp,
          ),

        onClick = {
          navController.navigate("addExpense")
        }) {
        Icon(Icons.Filled.Add, contentDescription = "Add")
      }
    },
  ) { innerPadding ->
    NavHostCompose(navController, innerPadding)
  }
}

@Composable
fun NavHostCompose(navController: NavHostController, innerPadding: PaddingValues) {
  NavHost(
    navController = navController,
    startDestination = "home",
    modifier = Modifier.padding(innerPadding)
  ) {
    composable("home") { HomeScreen() }
    composable("statistic") { StatisticScreen() }
    composable("wallet") { WalletScreen() }
    composable("addExpense") { AddExpenseScreen(navController) }
    composable("profile") { ProfileScreen() }

  }
}

