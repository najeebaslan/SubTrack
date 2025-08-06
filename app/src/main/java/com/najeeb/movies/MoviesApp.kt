@file:OptIn(ExperimentalMaterial3Api::class)

package com.najeeb.movies

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
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.najeeb.movies.data.TransactionDetailsModels
import com.najeeb.movies.data.UpcomingBillsItem
import com.najeeb.movies.data.BillPaymentModel
import com.najeeb.movies.navigation.ScreenRoutes
import com.najeeb.movies.screens.addExpense.AddExpenseScreen
import com.najeeb.movies.screens.bill_details.BillDetailsScreen
import com.najeeb.movies.screens.bill_payment.BillPaymentScreen
import com.najeeb.movies.screens.payment_successfully.PaymentSuccessfullyScreen
import com.najeeb.movies.screens.connect_wallet.ConnectWalletScreen
import com.najeeb.movies.screens.home.HomeScreen
import com.najeeb.movies.screens.navigation.MovieNavigationBar
import com.najeeb.movies.screens.profile.ProfileScreen
import com.najeeb.movies.screens.statistic.StatisticScreen
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

        onClick = { navController.navigate(ScreenRoutes.ADD_EXPENSE) }
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
    startDestination = ScreenRoutes.HOME,
    modifier = Modifier.padding(innerPadding)
  ) {
    composable(ScreenRoutes.HOME) {
      HomeScreen(onClickTransaction = {
        navController.currentBackStackEntry?.savedStateHandle?.set(ScreenRoutes.Keys.TRANSACTION_MODEL, it)
        navController.navigate(ScreenRoutes.TRANSACTION_DETAILS)
      })
    }
    composable(ScreenRoutes.STATISTIC) { StatisticScreen(navController = navController) }
    composable(ScreenRoutes.WALLET) { WalletScreen(navController) }
    composable(ScreenRoutes.CONNECT_WALLET) { ConnectWalletScreen(navController) }
    composable(ScreenRoutes.ADD_EXPENSE) { AddExpenseScreen(navController) }
    composable(ScreenRoutes.TRANSACTION_DETAILS) {
      // This remember ensures that the model is stored once when the screen first opens,
      // and not recomputed on recomposition or when the back stack changes.
      val model = remember {
        navController
          .previousBackStackEntry
          ?.savedStateHandle
          ?.get<TransactionDetailsModels>(ScreenRoutes.Keys.TRANSACTION_MODEL)
      }

      if (model != null) {
        TransactionDetailsScreen(model = model, navController)
      }
    }
    composable(ScreenRoutes.BILL_DETAILS) {
      // This remember ensures that the model is stored once when the screen first opens,
      // and not recomputed on recomposition or when the back stack changes.
      val model = remember {
        navController
          .previousBackStackEntry
          ?.savedStateHandle
          ?.get<UpcomingBillsItem>(ScreenRoutes.Keys.BILL_MODEL)
      }

      if (model != null) {
        BillDetailsScreen(model = model, navController)
      }
    }
    composable(ScreenRoutes.BILL_PAYMENT) {
      // This remember ensures that the model is stored once when the screen first opens,
      // and not recomputed on recomposition or when the back stack changes.
      val model = remember {
        navController
          .previousBackStackEntry
          ?.savedStateHandle
          ?.get<BillPaymentModel>(ScreenRoutes.Keys.BILL_PAYMENT_MODEL)
      }

      if (model != null) {
        BillPaymentScreen(model = model, navController)
      }
    }
    composable(ScreenRoutes.PAYMENT_SUCCESSFULLY) {
      // This remember ensures that the model is stored once when the screen first opens,
      // and not recomputed on recomposition or when the back stack changes.
      val model = remember {
        navController
          .previousBackStackEntry
          ?.savedStateHandle
          ?.get<BillPaymentModel>(ScreenRoutes.Keys.PAYMENT_SUCCESSFULLY_MODEL)
      }

      if (model != null) {
        PaymentSuccessfullyScreen(model = model, navController)
      }
    }
    composable(ScreenRoutes.PROFILE) { ProfileScreen(navController) }

  }
}

