package com.najeeb.movies.screens.payment_successfully

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.material.icons.filled.CheckCircle
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
import com.najeeb.movies.navigation.NavigationHelper
import com.najeeb.movies.ui.theme.BackgroundCardColor
import androidx.compose.foundation.clickable
import com.najeeb.movies.components.BaseOutlinedButton
import com.najeeb.movies.data.BillPaymentModel
import com.najeeb.movies.screens.bill_details.PriceRow
import com.najeeb.movies.screens.bill_details.SpacerHeight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentSuccessfullyScreen(model: BillPaymentModel, navController: NavHostController) {
  val systemUiController = rememberSystemUiController()
  SideEffect {
    systemUiController.setStatusBarColor(Color.Transparent, darkIcons = false)
  }

  Scaffold(
    topBar = {
      CustomAppTopBar(
        title = "Payment Successfully",
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
          BodyPaymentSuccessfullyScreen(model = model, navController = navController)
        }
      }
    }
  }
}

@Composable
fun BodyPaymentSuccessfullyScreen(model: BillPaymentModel, navController: NavHostController) {
  Column(
    modifier = Modifier
      .padding(horizontal = 20.dp)
      .fillMaxSize()
      .verticalScroll(rememberScrollState()),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    SpacerHeight(30.dp)

    // Success Icon
    Icon(
      imageVector = Icons.Default.CheckCircle,
      contentDescription = "Payment Success",
      modifier = Modifier
        .size(120.dp)
        .clip(CircleShape)
        .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f))
        .padding(20.dp),
      tint = MaterialTheme.colorScheme.primary
    )

    SpacerHeight(30.dp)

    // Success Title
    Text(
      text = "Payment Successful!",
      style = MaterialTheme.typography.headlineMedium.copy(
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        color = MaterialTheme.colorScheme.primary
      ),
      textAlign = TextAlign.Center
    )

    SpacerHeight(15.dp)

    // Success Message
    Text(
      text = "Your payment has been processed successfully",
      style = MaterialTheme.typography.bodyLarge.copy(
        fontSize = 16.sp,
        color = Color.Gray
      ),
      textAlign = TextAlign.Center
    )

    SpacerHeight(40.dp)

    // Service Details
    Row(
      modifier = Modifier.fillMaxWidth(),
      verticalAlignment = Alignment.CenterVertically
    ) {
      Image(
        painter = painterResource(model.serviceIcon),
        contentDescription = "Service Icon",
        modifier = Modifier
          .size(50.dp)
          .clip(CircleShape)
          .background(BackgroundCardColor)
          .padding(8.dp)
      )

      Spacer(modifier = Modifier.weight(1f))

      Column {
        Text(
          model.serviceName,
          style = MaterialTheme.typography.titleLarge.copy(
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
          )
        )
        Text(
          model.paymentMethod,
          style = MaterialTheme.typography.bodyMedium.copy(
            color = Color.Gray
          )
        )
      }
    }

    SpacerHeight(30.dp)

    // Payment Details
    Column(
      modifier = Modifier.fillMaxWidth()
    ) {
      BaseDivider()
      SpacerHeight(20.dp)

      PriceRow("Payment Method", model.paymentMethod)
      SpacerHeight(8.dp)
      PriceRow("Time", model.time)
      SpacerHeight(8.dp)
      PriceRow("Date", model.date)
      SpacerHeight(8.dp)
      PriceRow("Transaction ID", model.transactionId)
      SpacerHeight(20.dp)
      BaseDivider()
      SpacerHeight(20.dp)
      PriceRow("Price Paid", model.price)
      SpacerHeight(8.dp)
      PriceRow("Fee", model.fee)
      SpacerHeight(20.dp)
      BaseDivider()
      SpacerHeight(20.dp)
      PriceRow("Total", model.total)

      SpacerHeight(20.dp)

    }

    Spacer(modifier = Modifier.weight(1f))

    // Action Buttons
    Column(
      modifier = Modifier.fillMaxWidth()
    ) {
      GradientButton(
        paddingHorizontal = 0,
        text = "Download Receipt",
        onClick = {
          NavigationHelper.navigateToHome(navController)
          Toast.makeText(
            navController.context,
            "Downloading receipt...",
            Toast.LENGTH_SHORT
          ).show()
          NavigationHelper.navigateToHome(navController)
        }
      )

      SpacerHeight(15.dp)

      BaseOutlinedButton(
//        paddingHorizontal = 0,
        title = "Back to Home",
        onClick = {
          NavigationHelper.navigateToHome(navController)
        }
      )
    }

    SpacerHeight(120.dp)
  }
}
