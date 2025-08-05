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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.najeeb.movies.data.TransactionDetailsModels
import com.najeeb.movies.screens.addExpense.AddExpenseScreen
import com.najeeb.movies.screens.connect_wallet.ConnectWalletScreen
import com.najeeb.movies.screens.home.HomeScreen
import com.najeeb.movies.screens.navigation.MovieNavigationBar
import com.najeeb.movies.screens.profile.ProfileScreen
import com.najeeb.movies.screens.statistic.StatisticScreen
import com.najeeb.movies.screens.statistic.StatisticsViewModel
import com.najeeb.movies.screens.transaction_details.TransactionDetailsScreen
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

        onClick = { navController.navigate("addExpense") }
      ) {
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
    composable("home") {
      HomeScreen(onClickTransaction = {
        navController.currentBackStackEntry?.savedStateHandle?.set(
          "transactionModel",
          it
        )
        navController.navigate("transaction-details")
      })
    }
    composable("statistic") { StatisticScreen() }
    composable("wallet") { WalletScreen(navController) }
    composable("connect-wallet") { ConnectWalletScreen(navController) }
    composable("addExpense") { AddExpenseScreen(navController) }
    composable("transaction-details") {
      // This remember ensures that the model is stored once when the screen first opens,
      // and not recomputed on recomposition or when the back stack changes.
      val model = remember {
        navController
          .previousBackStackEntry
          ?.savedStateHandle
          ?.get<TransactionDetailsModels>("transactionModel")
      }

      if (model != null) {
        TransactionDetailsScreen(model = model, navController)
      }
    }
    composable("profile") { ProfileScreen() }

  }
}

