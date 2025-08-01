package com.najeeb.movies.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun MovieNavigationBar(navController: NavHostController) {
  val items = listOf(
    BottomNavItem("Inbox", Icons.Default.Email, "inbox"),
    BottomNavItem("Sent", Icons.Default.Send, "sent"),
    BottomNavItem("Trash", Icons.Default.Delete, "trash")
  )

  NavigationBar {
    val currentDestination = navController.currentBackStackEntryAsState().value?.destination?.route

    items.forEach { item ->
      NavigationBarItem(
        selected = currentDestination == item.route,
        onClick = { navController.navigate(item.route) },
        icon = { Icon(item.icon, contentDescription = item.label) },
        label = { Text(item.label) }
      )
    }
  }
}

data class BottomNavItem(val label: String, val icon: ImageVector, val route: String)
