package com.najeeb.movies.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.najeeb.movies.R
import com.najeeb.movies.core.shadowBottom
import com.najeeb.movies.ui.theme.toColor


@Composable
fun BalanceCard(
  balanceInfo: BalanceInfo,
  modifier: Modifier = Modifier,
  onMenuClick: () -> Unit = {}
) {


  Card(

    elevation = CardDefaults.cardElevation(
      defaultElevation = 20.dp,

      ),
    shape = RoundedCornerShape(20.dp),
    modifier = modifier
      .padding(horizontal = 16.dp)
      .fillMaxWidth()
      .then(
        Modifier.shadowBottom(
          color =
            MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.6f),
          offsetY = 0.dp,
          offsetX = 15.dp,
          blurRadius = 20.dp,
        ),
      ),
    colors = CardDefaults.cardColors(
      containerColor = MaterialTheme.colorScheme.primary
    )
  ) {
    Column {
      // Main balance row
      BalanceRow(
        title = "Total Balance",
        amount = balanceInfo.totalBalance,
        onMenuClick = onMenuClick
      )

      // Income/Expense row
      Row(
        modifier = Modifier
          .padding(16.dp)
          .fillMaxWidth(),

        horizontalArrangement = Arrangement.SpaceBetween
      ) {
        BalanceItem(
          title = "Income",
          amount = balanceInfo.income,
          imageUri = R.drawable.income_icon,
        )

        Spacer(modifier = Modifier.width(16.dp))

        BalanceItem(
          title = "Expense",
          horizontalAlignment = Alignment.End,
          amount = balanceInfo.expense,
          imageUri = R.drawable.expenses_icon
        )
      }
    }
  }
}

@Composable
private fun BalanceItem(
  title: String,
  amount: String,
  horizontalAlignment: Alignment.Horizontal = Alignment.Start,
  imageUri: Int
) {
  Column(horizontalAlignment = horizontalAlignment) {
    Row(verticalAlignment = Alignment.CenterVertically) {
      Image(
        modifier = Modifier
          .size(25.dp)
          .padding(end = 4.dp),
        painter = painterResource(id = imageUri),
        contentDescription = "$title Icon",
      )
      Text(
        text = title,
        style = MaterialTheme.typography.bodyLarge.copy(
          color = "#D0E5E4".toColor(),
          fontWeight = FontWeight.Normal,

          )
      )
    }

    Spacer(modifier = Modifier.height(4.dp))

    Text(
      text = amount,
      style = MaterialTheme.typography.titleLarge.copy(color = Color.White)
    )
  }
}
