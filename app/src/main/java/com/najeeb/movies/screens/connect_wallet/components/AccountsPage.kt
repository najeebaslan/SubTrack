package com.najeeb.movies.screens.connect_wallet.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.najeeb.movies.components.BaseOutlinedButton
import com.najeeb.movies.data.listAccounts
import com.najeeb.movies.ui.theme.BackgroundCardColor
import com.najeeb.movies.ui.theme.GrayColor

@Composable
@Preview(showBackground = true)
fun AccountsPage() {
  var selectedIndex by remember { mutableIntStateOf(-1) }


  LazyColumn(
    modifier = Modifier
      .fillMaxSize(),
    verticalArrangement = Arrangement.Top
  ) {
    itemsIndexed(listAccounts) { index, item ->
      AccountCard(
        imageUri = item.imageUri,
        title = item.title,
        subtitle = item.subtitle,
        isSelected = selectedIndex == index,
        onClick = { selectedIndex = index }
      )
    }

    item {
      Spacer(Modifier.height(80.dp))
      BaseOutlinedButton(title =  "Next", onClick = {})
    }
    item {
      Spacer(Modifier.height(150.dp))
    }
  }
}


@Composable
fun AccountCard(
  imageUri: Int,
  title: String,
  subtitle: String,
  isSelected: Boolean,
  onClick: () -> Unit
) {
  Card(
    modifier = Modifier
      .fillMaxWidth()
      .padding(bottom = 14.dp)
      .clip(RoundedCornerShape(20.dp))
      .clickable(onClick = onClick),
    colors = CardDefaults.cardColors(
      containerColor =
        if (isSelected) MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
        else BackgroundCardColor,
    ),
    shape = RoundedCornerShape(20.dp),

    ) {
    Row(
      modifier = Modifier
        .padding(20.dp)
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
          painter = painterResource(id = imageUri),
          contentDescription = "Income",
        )
      }
      Column(
        modifier = Modifier
          .padding(start = 10.dp)
          .weight(1f)
      ) {
        Text(
          title,
          overflow = TextOverflow.Ellipsis,
          maxLines = 1,
          style = MaterialTheme.typography.titleMedium,
          color = if (isSelected) MaterialTheme.colorScheme.primary
          else MaterialTheme.colorScheme.onSurface
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
          subtitle,
          overflow = TextOverflow.Clip,
          maxLines = 2,
          style = MaterialTheme.typography.bodySmall,
          color = if (isSelected) MaterialTheme.colorScheme.primary
          else MaterialTheme.colorScheme.onSurface
        )
      }
      if (isSelected) {
        Icon(
          imageVector = Icons.Filled.Done,
          tint = Color.White,
          modifier = Modifier
            .size(20.dp)
            .shadow(
              elevation = 2.dp,
              shape = RoundedCornerShape(10.dp)
            )
            .background(
              color = MaterialTheme.colorScheme.primary,
              shape = CircleShape
            )
            .padding(2.dp),
          contentDescription = "Selected",
        )
      } else {
        Spacer(
          modifier = Modifier
            .size(30.dp)
        )
      }
    }
  }
}
