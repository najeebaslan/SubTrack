package com.najeeb.movies.screens.wallet.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.najeeb.movies.ui.theme.BackgroundCardColor
import com.najeeb.movies.ui.theme.LightPrimaryColor
import com.najeeb.movies.ui.theme.toColor


@Composable
fun UpcomingBillsListItems(
  modifier: Modifier = Modifier,
  imageUri: Int,
  paddingImage: Dp,
  name: String,
  date: String,
  onClickPay: () -> Unit
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
    FilledTonalButton(
      onClick = onClickPay,
      colors = ButtonDefaults.filledTonalButtonColors(
        containerColor =LightPrimaryColor,
      )
    ) {
      Text(
        text = "Pay",
        style = MaterialTheme.typography.titleMedium.copy(
          color = MaterialTheme.colorScheme.primary
        )
      )
    }
  }

}
