package com.najeeb.movies.screens.wallet.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.najeeb.movies.R
import com.najeeb.movies.components.TweenCurrencyCount

import com.najeeb.movies.ui.theme.GrayColor


@Composable

fun TotalBalanceWithIcon() {
  Column(
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Text(
      "Total Balance", style = MaterialTheme.typography.bodyMedium.copy(
        fontSize = 18.sp, color = GrayColor
      )
    )
    Spacer(Modifier.height(10.dp))


    TweenCurrencyCount(
      targetAmount = 2548.00,
      durationMillis = 800,
      textStyle = MaterialTheme.typography.displaySmall.copy(
        fontWeight = FontWeight.Bold
      )
    )
    Spacer(Modifier.height(30.dp))
    Row(
      horizontalArrangement = Arrangement.Center,
      verticalAlignment = Alignment.CenterVertically
    ) {
      CircleIconWithTitle(
        icon = R.drawable.add_icon,
        title = "Add"
      )
      CircleIconWithTitle(
        icon = R.drawable.qr_code,
        title = "Pay"
      )
      CircleIconWithTitle(
        icon = R.drawable.sned_icon,
        title = "Send"

      )
    }

  }
}

@Composable
fun CircleIconWithTitle(icon: Int, title: String) {
  Column(
    modifier = Modifier.padding(horizontal = 14.dp),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Row(
      horizontalArrangement = Arrangement.Center,
      verticalAlignment = Alignment.CenterVertically
    ) {

      Spacer(Modifier.width(8.dp))
      OutlinedCard(
        modifier = Modifier.size(50.dp),
        shape = CircleShape,
        border = BorderStroke(
          width = 1.dp,
          color = MaterialTheme.colorScheme.primary
        )
      ) {
        Box(
          modifier = Modifier.fillMaxSize(),
          contentAlignment = Alignment.Center
        ) {
          Icon(
            painter = painterResource(icon),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(24.dp)
          )


        }
      }
    }
    Spacer(Modifier.height(10.dp))
    Text(title, style = MaterialTheme.typography.bodyMedium)
  }
}