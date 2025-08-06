package com.najeeb.movies.screens.bill_details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.najeeb.movies.components.BaseDivider
import com.najeeb.movies.components.CustomAppTopBar
import com.najeeb.movies.components.BaseHeaderBackground
import com.najeeb.movies.components.GradientButton
import com.najeeb.movies.data.BillPaymentModel
import com.najeeb.movies.data.UpcomingBillsItem
import com.najeeb.movies.navigation.NavigationHelper
import com.najeeb.movies.ui.theme.BackgroundCardColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BillDetailsScreen(model: UpcomingBillsItem, navController: NavHostController) {
  val systemUiController = rememberSystemUiController()
  SideEffect {
    systemUiController.setStatusBarColor(Color.Transparent, darkIcons = false)
  }

  Scaffold(
    topBar = {
      CustomAppTopBar(
        title = "Bill Details",
        containerColor = Color.Transparent,
        titleColor = Color.White,
        onBackClick = { navController.popBackStack() },
        navigationIconColor = Color.White,
        actions = {
          Box(Modifier.padding(horizontal = 16.dp)) {
            Icon(
              imageVector = Icons.Default.MoreVert,
              contentDescription = "More options",
              tint = Color.White,
              modifier = Modifier.clickable { }
            )
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
          BodyBillDetailsScreen(model = model, navController = navController)
        }
      }
    }
  }
}

@Composable
fun BodyBillDetailsScreen(model: UpcomingBillsItem, navController: NavHostController) {
  var selectedPaymentMethod by remember { mutableStateOf("Debit Card") }

  Column(
    modifier = Modifier
      .padding(horizontal = 20.dp)
      .fillMaxSize()
      .verticalScroll(rememberScrollState()),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    SpacerHeight(20.dp)

    // Subscription Details
    Row(
      modifier = Modifier.fillMaxWidth(),
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.Start
    ) {

      Image(
        painter = painterResource(model.imageUri),
        contentDescription = "Service Icon",
        modifier = Modifier
          .size(70.dp)
          .clip(CircleShape)
          .background(BackgroundCardColor)
          .padding(12.dp)
      )

      Spacer(modifier = Modifier.width(20.dp))

      Column {
        Text(
          model.name,
          style = MaterialTheme.typography.titleLarge.copy(
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
          )
        )
        Text(
          model.date,
          style = MaterialTheme.typography.bodyMedium.copy(
            fontWeight = FontWeight.W400,
            color = Color.Gray
          )
        )
      }
    }

    SpacerHeight(30.dp)

    // Price Breakdown
    Column(
      modifier = Modifier.fillMaxWidth()
    ) {
      PriceRow("Price", model.price)
      SpacerHeight(8.dp)
      PriceRow("Fee", model.fee)

      SpacerHeight(15.dp)
      BaseDivider()
      SpacerHeight(15.dp)

      PriceRow("Total", model.total, isTotal = true)
    }

    SpacerHeight(30.dp)

    // Payment Method Selection
    Text(
      "Select payment method",
      style = MaterialTheme.typography.titleMedium.copy(
        fontWeight = FontWeight.W600,
        fontSize = 16.sp
      ),
      modifier = Modifier.fillMaxWidth()
    )

    SpacerHeight(15.dp)

    // Payment Options with RadioButton
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .selectableGroup()
    ) {
      PaymentMethodOption(
        icon = model.debitCardIcon,
        title = "Debit Card",
        isSelected = selectedPaymentMethod == "Debit Card",
        onSelectionChanged = { selectedPaymentMethod = "Debit Card" }
      )

      SpacerHeight(10.dp)

      PaymentMethodOption(
        icon = model.paypalIcon,
        title = "Paypal",
        isSelected = selectedPaymentMethod == "Paypal",
        onSelectionChanged = { selectedPaymentMethod = "Paypal" }
      )
    }

    SpacerHeight(40.dp)

    // Pay Now Button
    GradientButton(
      paddingHorizontal = 0,
      text = "Pay Now",
      onClick = {
        NavigationHelper.navigateToBillPayment(
          navController,

          BillPaymentModel(
            serviceName = model.name,
            serviceIcon = model.imageUri,
            paymentMethod = selectedPaymentMethod,
            price = model.price,
            fee = model.fee,
            total = model.total,
            date = model.date,
            time = model.time,
          )
        )
      }
    )

    SpacerHeight(120.dp)
  }
}

@Composable
fun PriceRow(
  label: String,
  amount: String,
  color: Color = MaterialTheme.typography.titleMedium.color,
  isTotal: Boolean = false
) {
  Row(
    modifier = Modifier.fillMaxWidth(),
    horizontalArrangement = Arrangement.SpaceBetween,
    verticalAlignment = Alignment.CenterVertically
  ) {
    Text(
      label,
      style = MaterialTheme.typography.bodyMedium.copy(
        fontWeight = if (isTotal) FontWeight.Bold else FontWeight.Normal
      )
    )
    Text(
      amount,
      style = MaterialTheme.typography.bodyMedium.copy(
        color = color,
        fontWeight = if (isTotal) FontWeight.Bold else FontWeight.W600
      )
    )
  }
}

@Composable
fun SpacerHeight(height: Dp) {
  Spacer(modifier = Modifier.height(height))
} 