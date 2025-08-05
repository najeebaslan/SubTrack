package com.najeeb.movies.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.najeeb.movies.R
import com.najeeb.movies.core.HelperSize.defaultPadding
import com.najeeb.movies.data.TransactionDetailsExpenseModels
import com.najeeb.movies.data.TransactionDetailsIncomeModels
import com.najeeb.movies.data.TransactionDetailsModels
import com.najeeb.movies.screens.transaction_details.InfoRow
import com.najeeb.movies.ui.theme.ExpenseColor
import com.najeeb.movies.ui.theme.GreenColor


@Composable
fun TransactionsSection(
  transactions: List<TransactionDetailsModels>,
  onClickTransaction: (TransactionDetailsModels) -> Unit,
) {
  SeeAllRow(
    title = R.string.transactions_history,
    subtitle = R.string.see_all
  )

  Spacer(modifier = Modifier.height(10.dp))

  transactions.forEach { transaction ->

    when (transaction) {
      is TransactionDetailsIncomeModels -> HomeListItems(
        modifier = Modifier
          .padding(horizontal = 16.dp, vertical = 8.dp)
          .clickable(onClick = { onClickTransaction(transaction) }),
        imageUri = transaction.imageUri,
        name = transaction.from,
        date = transaction.date,
        amount = transaction.total,
        amountColor = GreenColor,
      )

      is TransactionDetailsExpenseModels -> HomeListItems(
        modifier = Modifier
          .padding(horizontal = 16.dp, vertical = 8.dp)
          .clickable(onClick = { onClickTransaction(transaction) }),
        imageUri = transaction.imageUri,
        name = transaction.total,
        date = transaction.date,
        amount = transaction.total,
        amountColor = ExpenseColor,
      )
    }

  }

  Spacer(modifier = Modifier.height(16.dp))
}

@Composable
fun SendAgainSection(images: List<Int>) {
  SeeAllRow(
    title = R.string.send_again,
    subtitle = R.string.see_all
  )

  LazyRow(
    modifier = Modifier.padding(vertical = 16.dp),
    horizontalArrangement = Arrangement.Start,
    contentPadding = PaddingValues(horizontal = 16.dp)
  ) {
    items(images.size) { index ->
      Image(
        painter = painterResource(images[index]),
        contentDescription = "avatar",
        contentScale = ContentScale.Crop,
        modifier = Modifier
          .padding(horizontal = 6.3.dp)
          .size(55.dp)
          .clip(CircleShape)
      )
    }
  }
}

@Composable
fun SeeAllRow(
  title: Int,
  subtitle: Int,
  onSubtitleClick: () -> Unit = {}
) {
  Row(
    horizontalArrangement = Arrangement.SpaceBetween,
    modifier = Modifier
      .fillMaxWidth()
      .padding(horizontal = defaultPadding.dp),
  ) {
    Text(
      text = stringResource(id = title),
      style = MaterialTheme.typography.titleLarge,
    )
    Text(
      text = stringResource(id = subtitle),
      style = MaterialTheme.typography.bodySmall,
      modifier = Modifier.clickable(onClick = onSubtitleClick)
    )
  }
}

data class BalanceInfo(
  val totalBalance: String,
  val income: String,
  val expense: String
)
