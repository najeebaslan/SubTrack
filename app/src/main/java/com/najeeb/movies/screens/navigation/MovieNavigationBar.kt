package com.najeeb.movies.screens.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.najeeb.movies.R
import com.najeeb.movies.ui.theme.toColor

@Composable
fun MovieNavigationBar(navController: NavHostController) {
  val isDarkTheme = isSystemInDarkTheme()

  val items = listOf(
    BottomNavItem(
      label = "Home",
      icon = R.drawable.home_icon,
      selectedIcon = R.drawable.home_icon_fill,
      route = "home"
    ),
    BottomNavItem(
      label = "Statistic",
      icon = R.drawable.statistic_icon,
      selectedIcon = R.drawable.statistic_icon_fill,
      route = "statistic"
    ),

    BottomNavItem(
      label = "Wallet",
      icon = R.drawable.wallet_icon,
      selectedIcon = R.drawable.wallet_icon_fill,
      route = "wallet"
    ),
    BottomNavItem(
      label = "Profile",
      icon = R.drawable.profile_icon,
      selectedIcon = R.drawable.profile_icon_fill,
      route = "profile"
    )
  )

  NavigationBar(

    containerColor = colorScheme.background,
    contentColor = colorScheme.background,
    modifier = Modifier
      .height(110.dp)

      .drawBehind {// Shadow NavigationBar
        drawRect(
          brush = Brush.verticalGradient(
            colors = listOf(
              Color.Transparent, Color.Gray.copy(
                alpha = if (isDarkTheme) 0.2f else 0.5f
              )
            ),
            startY = -40f,
            endY = size.height
          ),
          topLeft = Offset(0f, -40f)
        )
      },
  ) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    items.forEach { item ->
      val selected = currentRoute == item.route
      NavigationBarItem(
        selected = selected,

        colors = NavigationBarItemDefaults.colors(
          selectedIconColor = colorScheme.primary,
          unselectedIconColor = colorScheme.onSurfaceVariant,
          selectedTextColor =
            if (isDarkTheme) colorScheme.onPrimary
            else colorScheme.primary,
          unselectedTextColor =
            "#AAAAAA".toColor(),
          indicatorColor = Color.Transparent
        ),
        onClick = {
          navController.navigate(item.route) {
            // Navigation behavior options
            popUpTo(navController.graph.findStartDestination().id) {
              saveState = true
            }
            launchSingleTop = true
            restoreState = true
          }
        },
        icon = {
          Image(
            modifier = Modifier.size(
              width = 23.dp,
              height = 23.dp
            ),
            painter = painterResource(
              id = if (selected) item.selectedIcon else item.icon
            ),

            contentDescription = item.label
          )
        },
        label = {
          Text(
            item.label,
            letterSpacing = 0.4.sp,
          )
        }
      )
    }
  }
}


sealed class Screen(val route: String) {
  data object Home : Screen("home")
  data object Profile : Screen("profile")
  data object Settings : Screen("settings/{userId}") {
    fun createRoute(userId: String) = "settings/$userId"
  }
}

data class BottomNavItem(
  val label: String,
  val icon: Int,
  val selectedIcon: Int,
  val route: String
)