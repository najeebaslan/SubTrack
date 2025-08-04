package com.najeeb.movies.screens.connect_wallet.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.najeeb.movies.R
import com.najeeb.movies.components.CustomTextField


@Composable
fun CardPage() {
  var state by remember { mutableStateOf(CardFormState()) }

  Column {

    DebitCardPreview(state)

    Spacer(Modifier.height(24.dp))

    Text("Add your debit Card", style = MaterialTheme.typography.titleMedium)
    Spacer(Modifier.height(3.dp))

    Text(
      "This card must be connected to a bank account under your name",
      style = MaterialTheme.typography.bodySmall
    )

    Spacer(Modifier.height(16.dp))

    CustomTextField(
      value = state.name,
      onValueChange = { state = state.copy(name = it) },
      hint = "NAME ON CARD"
    )

    Spacer(Modifier.height(8.dp))

    CustomTextField(
      value = state.number,
      onValueChange = {
        val formatted = it.filter { it.isDigit() }.chunked(4).joinToString(" ")
        if (formatted.length <= 19) {
          state = state.copy(number = formatted)
        }
      },
      hint = "DEBIT CARD NUMBER"
    )

    Spacer(Modifier.height(8.dp))

    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
      CustomTextField(
        modifier = Modifier.weight(1f),
        value = state.cvc,
        onValueChange = {
          if (it.length <= 3 && it.all(Char::isDigit)) {
            state = state.copy(cvc = it)
          }
        },
        hint = "CVC"
      )

      CustomTextField(
        modifier = Modifier.weight(1f),
        value = state.expiry,
        onValueChange = {
          val digits = it.filter(Char::isDigit)
          val formatted = when {
            digits.length <= 2 -> digits
            digits.length <= 4 -> digits.substring(0, 2) + "/" + digits.substring(2)
            else -> digits.substring(0, 2) + "/" + digits.substring(2, 4)
          }
          state = state.copy(expiry = formatted)
        },
        hint = "EXPIRATION MM/YY"
      )
    }

    Spacer(Modifier.height(8.dp))

    CustomTextField(
      value = state.zip,
      onValueChange = {
        if (it.length <= 6 && it.all(Char::isDigit)) {
          state = state.copy(zip = it)
        }
      },
      hint = "ZIP"
    )


  }
}


@Composable
fun DebitCardPreview(cardInfo: CardFormState) {
  // Format the card number with spaces every 4 digits
//  val formattedNumber = remember(cardInfo.number) {
//    cardInfo.number
//      .filter { it.isDigit() } // Remove any existing spaces
//      .chunked(4) // Split into chunks of 4
//      .joinToString("   ") // Join with spaces
//  }
  val numberGroups = remember(cardInfo.number) {
    cardInfo.number
      .filter { it.isDigit() }
      .chunked(4)
  }

  Box(
    modifier = Modifier
      .fillMaxWidth() // Make card take full width
      .height(200.dp)
      .shadow(
        elevation = 45.dp,
        shape = RoundedCornerShape(23.dp),
        spotColor = Color(0x1A000000),
        ambientColor = Color(0x1A000000),
      )
      .background(
        brush = Brush.linearGradient(
          colors = listOf(
            Color(0xFF2A7777),
            Color(0xFF06B6B6)
          )
        )
      )
  ) {
    Image(
      painter = painterResource(id = R.drawable.debit_card_background),
      contentDescription = null,
      modifier = Modifier.fillMaxSize()
    )

    Column(
      modifier = Modifier
        .fillMaxSize()
        .padding(16.dp),
      verticalArrangement = Arrangement.SpaceBetween
    ) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
      ) {
        Text("Debit\n Card", color = Color.White)
        Text("Mono", color = Color.White, style = MaterialTheme.typography.titleMedium)
      }

      Image(
        painter = painterResource(id = R.drawable.chip),
        contentDescription = null,
      )

      // Formatted card number
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .padding(top = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
      ) {
        // Display each group in a separate Text component
        numberGroups.forEach { group ->
          Text(
            text = group,
            color = Color.White,
            style = MaterialTheme.typography.titleMedium.copy(
              letterSpacing = 2.sp,
              fontFamily = FontFamily.Monospace
            )
          )
        }

        // Add empty groups for remaining slots (up to 4 groups)
        repeat(4 - numberGroups.size) {
          Text(
            text = "••••",
            color = Color.White.copy(alpha = 0.5f),
            style = MaterialTheme.typography.titleMedium.copy(
              letterSpacing = 2.sp,
              fontFamily = FontFamily.Monospace
            )
          )
        }
      }

      Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
      ) {
        Text(cardInfo.name, color = Color.White)
        Text(cardInfo.expiry, color = Color.White)
      }
    }
  }
}

data class CardFormState(
  val name: String = "IRVAN MOSES",
  val number: String = "6219 8610 2888 8075",
  val expiry: String = "22/01",
  val cvc: String = "",
  val zip: String = ""
)
