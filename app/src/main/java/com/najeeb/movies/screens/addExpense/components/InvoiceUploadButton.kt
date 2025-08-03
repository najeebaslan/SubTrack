package com.najeeb.movies.screens.addExpense.components

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Icon
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.najeeb.movies.core.dashedBorder


@Composable
fun InvoiceUploadButton() {
  var imageUri by remember { mutableStateOf<Uri?>(null) }

  // Image picker launcher
  val launcher = rememberLauncherForActivityResult(
    contract = ActivityResultContracts.GetContent()
  ) { uri: Uri? ->
    imageUri = uri
  }

  Column(
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Box(
      Modifier. clip(
        RoundedCornerShape(12.dp)
      ).clickable { launcher.launch("image/*") }
        .fillMaxWidth()
        .height(60.dp)
        .dashedBorder(
          width = 2.dp,
          radius = 10.dp,
          color = MaterialTheme.colorScheme.outline
        )
        .padding(8.dp),

      contentAlignment = Alignment.Center
    ) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Icon(
          imageVector = Icons.Default.AddCircle,
          contentDescription = "Add Invoice",
          tint = Color.Gray
        )
        Spacer(modifier = Modifier.width(5.dp))
        Text(
          textAlign = TextAlign.Center,
          text = "Add Invoice"
        )
      }
    }

    Spacer(modifier = Modifier.height(16.dp))

    // Show selected image
    imageUri?.let { uri ->
      Image(
        painter = rememberAsyncImagePainter(uri),
        contentDescription = null,
        modifier = Modifier
          .fillMaxWidth()
          .size(200.dp)
          .clip(RoundedCornerShape(12.dp)),
        contentScale = ContentScale.Crop
      )
    }
  }
}
