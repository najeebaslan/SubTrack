package com.najeeb.movies.screens.bill_details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.najeeb.movies.ui.theme.BackgroundCardColor
import com.najeeb.movies.ui.theme.GrayColor
import com.najeeb.movies.ui.theme.toColor


@Composable
fun PaymentMethodOption(
  icon: Int,
  title: String,
  isSelected: Boolean,
  onSelectionChanged: () -> Unit
) {
  Card(
    modifier = Modifier
      .fillMaxWidth().height(height = 90.dp)
      .padding(bottom = 14.dp)
      .clip(RoundedCornerShape(20.dp))

      .selectable(
        selected = isSelected,
        onClick = onSelectionChanged,
        role = Role.RadioButton
      ),


    colors = CardDefaults.cardColors(
      containerColor =
        if (isSelected) MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
        else BackgroundCardColor,
    ),
    shape = RoundedCornerShape(20.dp),

    border = if (isSelected) {
      androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.primary)
    } else null
  ) {
    Row(
      modifier = Modifier

        .padding(10.dp)
        .fillMaxWidth(),
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.SpaceBetween
    ) {
      Surface(
        modifier = Modifier.size(50.dp),
        shape = CircleShape,
        color = if (isSelected) Color.White else Color.Transparent
      ) {
        Image(
          modifier = Modifier.padding(10.dp),
          colorFilter = ColorFilter.tint(
            if (isSelected) MaterialTheme.colorScheme.primary
            else GrayColor.copy(alpha = 0.7f)
          ),
          painter = painterResource(id = icon),
          contentDescription = "Income",
        )
      }

      Text(
        title,
        modifier = Modifier
          .padding(start = 10.dp),
        style = MaterialTheme.typography.bodyLarge.copy(
          fontWeight = FontWeight.W500
        )
      )

      Spacer(modifier = Modifier.weight(0.1f))

      // Material3 RadioButton
      RadioButton(
        selected = isSelected,
        onClick = onSelectionChanged
      )
    }
  }
}
