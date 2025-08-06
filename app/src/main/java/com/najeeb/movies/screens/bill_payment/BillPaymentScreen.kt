package com.najeeb.movies.screens.bill_payment

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
import androidx.compose.material.icons.filled.MoreVert
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.najeeb.movies.components.BaseDivider
import com.najeeb.movies.components.CustomAppTopBar
import com.najeeb.movies.components.BaseHeaderBackground
import com.najeeb.movies.components.GradientButton
import com.najeeb.movies.data.BillPaymentModel
import com.najeeb.movies.navigation.NavigationHelper
import com.najeeb.movies.ui.theme.BackgroundCardColor
import androidx.compose.foundation.clickable
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import com.najeeb.movies.R
import com.najeeb.movies.core.HelperSize.defaultPadding
import com.najeeb.movies.screens.bill_details.SpacerHeight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BillPaymentScreen(model: BillPaymentModel, navController: NavHostController) {
  val systemUiController = rememberSystemUiController()
  SideEffect {
    systemUiController.setStatusBarColor(Color.Transparent, darkIcons = false)
  }

  Scaffold(
    topBar = {
      CustomAppTopBar(
        title = "Bill Payment",
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
          BodyBillPaymentScreen(model = model, navController = navController)
        }
      }
    }
  }
}

@Composable
fun BodyBillPaymentScreen(model: BillPaymentModel, navController: NavHostController) {
  Column(
    modifier = Modifier
      .padding(horizontal = 20.dp)
      .fillMaxSize()
      .verticalScroll(rememberScrollState()),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    SpacerHeight(30.dp)

    // Service Icon
    Image(
      painter = painterResource(model.serviceIcon),
      contentDescription = "Service Icon",
      modifier = Modifier
        .size(80.dp)
        .clip(CircleShape)
        .background(BackgroundCardColor)
        .padding(16.dp)
    )

    SpacerHeight(10.dp)
    Text(
      text = buildAnnotatedString {
        withStyle(
          style = SpanStyle(
            fontSize = 16.sp
          )
        ) {
          append("You will pay ")
        }

        withStyle(
          style = SpanStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
          )
        ) {
          append("${model.serviceName}\n")
        }

        withStyle(
          style = SpanStyle(
            fontSize = 16.sp
          )
        ) {
          append(" for one month with ")
        }

        withStyle(
          style = SpanStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
          )
        ) {
          append(model.paymentMethod)
        }
      },
      modifier = Modifier.padding(defaultPadding.dp),
      textAlign = TextAlign.Center,
      style = MaterialTheme.typography.bodyLarge
    )


    SpacerHeight(30.dp)

    // Price Breakdown
    Column(
      modifier = Modifier.fillMaxWidth()
    ) {
      BaseDivider()
      SpacerHeight(20.dp)

      PriceRow("Price", model.price)
      SpacerHeight(8.dp)
      PriceRow("Fee", model.fee)

      SpacerHeight(20.dp)
      BaseDivider()
      SpacerHeight(20.dp)

      PriceRow("Total", model.total, isTotal = true)
    }

    Spacer(modifier = Modifier.weight(1f))
    SpacerHeight(30.dp)
    // Confirm and Pay Button
    GradientButton(
      paddingHorizontal = 0,
      text = "Confirm and Pay",
      onClick = {
        NavigationHelper.navigateToPaymentSuccessfully(navController, model)
      }
    )
    SpacerHeight(120.dp)
  }
}

@Composable
fun PriceRow(
  label: String,
  amount: String,
  isTotal: Boolean = false
) {
  Row(
    modifier = Modifier.fillMaxWidth(),
    horizontalArrangement = Arrangement.SpaceBetween,
    verticalAlignment = Alignment.CenterVertically
  ) {
    Text(
      label,
      style = MaterialTheme.typography.bodyLarge.copy(
        fontWeight = if (isTotal) FontWeight.Bold else FontWeight.Normal
      )
    )
    Text(
      amount,
      style = MaterialTheme.typography.bodyLarge.copy(
        fontWeight = if (isTotal) FontWeight.Bold else FontWeight.Normal
      )
    )
  }
}
