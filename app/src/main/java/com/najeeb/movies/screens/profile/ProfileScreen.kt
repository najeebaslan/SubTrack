package com.najeeb.movies.screens.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.najeeb.movies.R
import com.najeeb.movies.components.CustomAppTopBar
import com.najeeb.movies.components.HeaderBackground
import com.najeeb.movies.core.HelperSize.defaultPadding
import com.najeeb.movies.screens.home.NotificationIcon
import com.najeeb.movies.ui.theme.BackgroundCardColor
import com.najeeb.movies.ui.theme.GrayColor
import com.najeeb.movies.ui.theme.toColor
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen() {
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
        title = "Profile",
        containerColor = Color.Transparent,
        titleColor = Color.White,
        navigationIconColor = Color.White,
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
        .padding()
        .verticalScroll(rememberScrollState()),
      horizontalAlignment = Alignment.Start,
      verticalArrangement = Arrangement.Top
    ) {
      HeaderBackground(modifier = Modifier.height(220.dp))
      UserInfo(modifier = Modifier.align(Alignment.CenterHorizontally))
      Column(
        modifier = Modifier
          .padding(horizontal = 16.dp)
          .offset(y = (-50).dp)
      ) {
        Spacer(Modifier.height(defaultPadding.dp))
        InviteFriends()
        Spacer(Modifier.height(defaultPadding.dp))
        Divider(
          color = "#EEEEEE".toColor(),
          modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
        )


        RowItems(
          imageVector = R.drawable.baseline_person_24,
          title = "Account info"
        )
        RowItems(
          imageVector = R.drawable.baseline_group_24,
          title = "Personal profile"
        )
        RowItems(
          imageVector = R.drawable.baseline_email_24,
          title = "Message center"
        )
        RowItems(
          imageVector = R.drawable.outline_security_24,
          title = "Login and security"
        )
        RowItems(
          imageVector = R.drawable.round_lock_24,
          title = "Data and privacy"
        )

      }
    }
  }
}

@Composable
fun UserInfo(modifier: Modifier = Modifier) {
  Column(
    modifier = modifier.offset(y = (-70).dp),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Image(
      modifier = modifier
        .size(120.dp)
        .clip(CircleShape),
      painter = painterResource(R.drawable.girl_image),
      contentDescription = "Image user profile"
    )
    Spacer(Modifier.height(defaultPadding.dp))
    Text(
      "Enjelin Morgeana",
      style = MaterialTheme.typography.titleLarge
    )
    Text(
      "@enjelin_morgeana",
      style = MaterialTheme.typography.bodyMedium.copy(
        color = MaterialTheme.colorScheme.primary
      )
    )
  }
}

@Composable
fun InviteFriends() {
  Column(horizontalAlignment = Alignment.CenterHorizontally) {

    Row(verticalAlignment = Alignment.CenterVertically) {
      Surface(
        shape = RoundedCornerShape(50.dp),
        color = BackgroundCardColor,
      ) {
        Image(
          modifier = Modifier
            .size(35.dp)
            .padding(5.dp),
          painter = painterResource(id = R.drawable.diamond),
          contentDescription = "Income",
        )
      }
      Spacer(Modifier.width(13.dp))
      Text("Invite Friends", style = MaterialTheme.typography.titleLarge)

    }
  }
}


@Composable
fun RowItems(imageVector: Int, title: String) {

  Row(
    verticalAlignment = Alignment.CenterVertically,
    modifier = Modifier.padding(vertical = 15.dp)
  ) {
    Icon(
      painter = painterResource(imageVector),
      contentDescription = null,
      Modifier.size(35.dp),
      tint = GrayColor
    )

    Spacer(Modifier.width(15.dp))
    Text(title, style = MaterialTheme.typography.titleLarge)

  }
}