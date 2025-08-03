package com.najeeb.movies.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.najeeb.movies.R
import com.najeeb.movies.components.HeaderBackground
import com.najeeb.movies.core.HelperSize.defaultPadding
import com.najeeb.movies.core.HelperSize.paddingBalanceCard
import com.najeeb.movies.ui.theme.BackgroundCardColor

@Composable
fun HomeHeader(
  username: String,
  balanceInfo: BalanceInfo,
  modifier: Modifier = Modifier,
  onNotificationClick: () -> Unit = {},
  onMenuClick: () -> Unit = {}
) {
  Box(
    modifier = modifier.height(220.dp),
    contentAlignment = Alignment.TopCenter
  ) {

    HeaderBackground(modifier)

//    HeaderContent(username, onNotificationClick)

    BalanceCard(
      balanceInfo = balanceInfo,
      modifier = Modifier
        .fillMaxWidth()
        .align(Alignment.BottomCenter)
        .offset(y = paddingBalanceCard.dp),
      onMenuClick = onMenuClick
    )
  }
}


@Composable
 fun HeaderContent(username: String, onNotificationClick: () -> Unit) {
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .padding(horizontal = 16.dp)
      .systemBarsPadding()
      .padding(top = defaultPadding.dp),
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.SpaceBetween
  ) {
    UserGreeting(username)
    NotificationIcon(onNotificationClick)
  }
}

@Composable
 fun UserGreeting(username: String) {
  Column {
    Text(
      text = stringResource(id = R.string.good_afternoon),
      style = MaterialTheme.typography.bodyMedium.copy(color = Color.White)
    )
    Spacer(modifier = Modifier.height(4.dp))
    Text(
      text = username,
      style = MaterialTheme.typography.titleLarge.copy(color = Color.White)
    )
  }
}

@Composable
 fun NotificationIcon(onClick: () -> Unit) {
  Image(
    modifier = Modifier
      .clip(RoundedCornerShape(10.dp))
      .clickable(onClick = onClick),
    painter = painterResource(id = R.drawable.notifications_icon),
    contentDescription = "Notifications"
  )
}


@Composable
fun BalanceRow(
  title: String,
  amount: String,
  onMenuClick: () -> Unit
) {
  Row(modifier = Modifier.padding(16.dp)) {
    Column(modifier = Modifier.weight(1f)) {
      Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
          text = title,
          style = MaterialTheme.typography.bodyLarge.copy(
            color = Color.White,
            fontWeight = FontWeight.SemiBold
          )
        )
        Icon(
          Icons.Filled.KeyboardArrowUp,
          contentDescription = "Increase",
          tint = Color.White,
          modifier = Modifier
            .size(20.dp)
            .padding(start = 3.dp)

        )
      }

      Spacer(modifier = Modifier.height(4.dp))

      Text(
        text = amount,
        style = MaterialTheme.typography.displaySmall.copy(
          color = Color.White,
          fontSize = 27.sp,
        )
      )
    }
//    painter = painterResource(R.drawable.more_hor_icon),

    Icon(
      Icons.Filled.MoreVert,
      contentDescription = "Menu",
      tint = Color.White,
      modifier = Modifier.clickable(onClick = onMenuClick)
    )
  }
}


@Composable
fun HomeListItems(
  modifier: Modifier = Modifier,
  imageUri:Int,
  paddingImage: Dp,
  name: String,
  date: String,
  amount: String,
  amountColor:Color,

  ) {
  Row(
    modifier = modifier
      .fillMaxWidth(),
    horizontalArrangement = Arrangement.SpaceBetween,
    verticalAlignment = Alignment.CenterVertically
  ) {

    Row(
      verticalAlignment = Alignment.CenterVertically
    ) {
      Surface(
        modifier = Modifier
          .size(70.dp)
          .padding(10.dp),
        shape = RoundedCornerShape(10.dp),
        color = BackgroundCardColor,
      ) {
        Image(
          modifier = Modifier.padding(paddingImage),
          painter = painterResource(id = imageUri),
          contentDescription = "Income",
        )
      }
      Column(modifier = Modifier.padding(start = 2.dp)) {
        Text(name, style = MaterialTheme.typography.titleMedium)

        Spacer(modifier = Modifier.height(4.dp))
        Text(date, style = MaterialTheme.typography.bodySmall)
      }
    }
    Text(
      text = amount,
      style = MaterialTheme.typography.titleMedium.copy(
        color =amountColor,
        fontSize = 17.sp,
      )
    )
  }

}
