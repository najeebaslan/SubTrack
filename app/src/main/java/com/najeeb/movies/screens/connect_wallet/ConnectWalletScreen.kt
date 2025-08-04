package com.najeeb.movies.screens.connect_wallet
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.najeeb.movies.components.CustomAppTopBar
import com.najeeb.movies.components.HeaderBackground
import com.najeeb.movies.screens.connect_wallet.components.BodyConnectWallet
import com.najeeb.movies.screens.home.NotificationIcon


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConnectWalletScreen(
//  navController: NavHostController,
  ) {
  val systemUiController = rememberSystemUiController()
  SideEffect {
    systemUiController.setStatusBarColor(
      color = Color.Transparent,
      darkIcons = false
    )
  }

  Scaffold(
    topBar = {
      CustomAppTopBar(
        title = "Connect Wallet",
        containerColor = Color.Transparent,
        titleColor = Color.White,
        navigationIconColor = Color.White,
//        onBackClick = { navController.popBackStack() },
        actions = {
          Box(modifier = Modifier.padding(horizontal = 16.dp)) {
            NotificationIcon(onClick = {})
          }
        }
      )
    }
  ) { padding ->
    Column(
      modifier = Modifier
        .fillMaxSize()
        .padding(),
      horizontalAlignment = Alignment.Start,
      verticalArrangement = Arrangement.Top
    ) {
      Box {
        HeaderBackground(modifier = Modifier.height(170.dp))

        Surface(
          color = MaterialTheme.colorScheme.background,
          modifier = Modifier
            .fillMaxSize()
            .offset(y = 100.dp),
          shape = RoundedCornerShape(30.dp)
        ) {
          BodyConnectWallet()

        }
      }
    }
  }
}


