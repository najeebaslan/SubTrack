package com.najeeb.movies.screens.transaction_details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.najeeb.movies.components.BaseDivider
import com.najeeb.movies.components.CustomAppTopBar
import com.najeeb.movies.components.BaseHeaderBackground
import com.najeeb.movies.components.BaseOutlinedButton
import com.najeeb.movies.data.TransactionDetailsExpenseModels
import com.najeeb.movies.data.TransactionDetailsIncomeModels
import com.najeeb.movies.data.TransactionDetailsModels
import com.najeeb.movies.screens.home.NotificationIcon
import com.najeeb.movies.ui.theme.BackgroundCardColor
import com.najeeb.movies.ui.theme.ExpenseColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun TransactionDetailsScreen(model: TransactionDetailsModels, navController: NavHostController) {
  val systemUiController = rememberSystemUiController()
  SideEffect {
    systemUiController.setStatusBarColor(Color.Transparent, darkIcons = false)
  }

  Scaffold(
    topBar = {
      CustomAppTopBar(
        title = "Transaction Details",
        containerColor = Color.Transparent,
        titleColor = Color.White,
        onBackClick = { navController.popBackStack() },
        navigationIconColor = Color.White,
        actions = {
          Box(Modifier.padding(horizontal = 16.dp)) {
            NotificationIcon(onClick = {})
          }
        }
      )
    }
  ) { padding ->
    Column(
      modifier = Modifier
        .fillMaxSize()
        .padding()
    ) {
      Box {
        BaseHeaderBackground(modifier = Modifier.height(170.dp))
        Surface(
          modifier = Modifier
            .fillMaxSize()
            .offset(y = 100.dp),
          shape = RoundedCornerShape(30.dp),
          color = MaterialTheme.colorScheme.background
        ) {
          BodyTransactionDetailsScreen(model = model)
        }
      }
    }
  }
}

@Composable
fun BodyTransactionDetailsScreen(model: TransactionDetailsModels) {
  val transactionColor =
    if (model is TransactionDetailsIncomeModels)
      MaterialTheme.colorScheme.primary else ExpenseColor

  Column(
    modifier = Modifier
      .padding(horizontal = 20.dp)
      .fillMaxSize()
      .verticalScroll(rememberScrollState()),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    SpacerHeight(10.dp)
    Image(
      painter = painterResource(model.imageUri),
      contentDescription = "User Image",
      modifier = Modifier
        .size(80.dp)
        .clip(CircleShape)
        .background(BackgroundCardColor)
        .padding(10.dp)
    )

    SpacerHeight(8.dp)

    StatusCard(model.status, transactionColor)

    SpacerHeight(10.dp)

    Text(
      model.total,
      style = MaterialTheme.typography.displaySmall.copy(
        fontWeight = FontWeight.Bold,
        fontSize = 23.sp
      )
    )

    SpacerHeight(30.dp)

    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically
    ) {
      Text(
        "Transaction details",
        style = MaterialTheme.typography.titleLarge.copy(
          fontWeight = FontWeight.W500,
          fontSize = 18.sp
        )
      )
      Icon(
        imageVector = Icons.Default.KeyboardArrowUp,
        contentDescription = "Expand",
        tint = MaterialTheme.typography.titleLarge.color
      )
    }

    SpacerHeight(20.dp)

    InfoRow("Status", model.status, transactionColor)
    SpacerHeight(8.dp)

    when (model) {
      is TransactionDetailsIncomeModels -> InfoRow("From", model.from)
      is TransactionDetailsExpenseModels -> InfoRow("To", model.to)
    }
    SpacerHeight(8.dp)

    InfoRow("Time", model.time)
    SpacerHeight(8.dp)

    InfoRow("Date", model.date)

    SpacerHeight(15.dp)
    BaseDivider()
    SpacerHeight(15.dp)
    when (model) {
      is TransactionDetailsIncomeModels -> InfoRow("Earnings", model.earnings)
      is TransactionDetailsExpenseModels -> InfoRow("Spending", model.spending)
    }
    SpacerHeight(8.dp)

    InfoRow("Fee", model.fee)

    SpacerHeight(15.dp)
    BaseDivider()
    SpacerHeight(15.dp)

    InfoRow("Total", model.total)

    SpacerHeight(40.dp)

    BaseOutlinedButton(title = "Download Receipt", onClick = {})

    SpacerHeight(150.dp)
  }
}

@Composable
fun InfoRow(
  titleKey: String,
  titleValue: String,
  color: Color = MaterialTheme.typography.titleMedium.color
) {
  Row(
    modifier = Modifier.fillMaxWidth(),
    horizontalArrangement = Arrangement.SpaceBetween
  ) {
    Text(titleKey, style = MaterialTheme.typography.bodyMedium)
    Text(
      titleValue,
      style = MaterialTheme.typography.bodyMedium.copy(
        color = color,
        fontWeight = FontWeight.W600
      )
    )
  }
}

@Composable
fun StatusCard(status: String, color: Color) {
  Card(
    modifier = Modifier.size(width = 80.dp, height = 25.dp),
    shape = RoundedCornerShape(40.dp),
    colors = CardDefaults.cardColors(
      containerColor = color.copy(alpha = 0.1f),
      contentColor = color
    )
  ) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
      Text(
        text = status,
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.bodyLarge.copy(
          color = color,
          fontWeight = FontWeight.W400
        )
      )
    }
  }
}

@Composable
fun SpacerHeight(height: Dp) {
  Spacer(modifier = Modifier.height(height))
}
