package com.najeeb.movies.screens.statistic

import androidx.compose.animation.core.EaseInOutCubic
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
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
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.najeeb.movies.R
import com.najeeb.movies.components.CustomAppTopBar
import com.najeeb.movies.components.ToggleButton
import com.najeeb.movies.core.HelperSize.defaultPadding
import com.najeeb.movies.screens.home.HomeListItems
import com.najeeb.movies.screens.home.TransactionItem
import com.najeeb.movies.screens.statistic.components.ButtonDropdownMenu
import com.najeeb.movies.screens.statistic.components.DateFilterChips
import com.najeeb.movies.ui.theme.IconColor
import com.najeeb.movies.ui.theme.toColor
import ir.ehsannarmani.compose_charts.LineChart
import ir.ehsannarmani.compose_charts.models.AnimationMode
import ir.ehsannarmani.compose_charts.models.DotProperties
import ir.ehsannarmani.compose_charts.models.DrawStyle
import ir.ehsannarmani.compose_charts.models.Line

@ExperimentalMaterial3Api
@Composable
fun StatisticScreen() {
  val isDarkMode = isSystemInDarkTheme()
  val systemUiController = rememberSystemUiController()
  SideEffect {
    systemUiController.setStatusBarColor(
      color = Color.Transparent,
      darkIcons = !isDarkMode
    )
  }

  Scaffold(
    topBar = {
      CustomAppTopBar(
        title = "Statistic Screen",
        showBackButton = false,
        actions = {
          Image(
            painter = painterResource(R.drawable.download_icon),
            contentDescription = "avatar",
            contentScale = ContentScale.Crop,
            colorFilter = ColorFilter.tint(IconColor),
            modifier = Modifier
              .padding(horizontal = 20.dp)
              .size(20.dp)
          )
        }
      )
    }
  ) { padding ->
    Column(
      modifier = Modifier
        .padding(horizontal = defaultPadding.dp)
        .fillMaxWidth()
        .verticalScroll(rememberScrollState())
        .padding(padding),
      horizontalAlignment = Alignment.End
    ) {
      DateFilterChips { selectedDate ->
        println("Selected date filter: $selectedDate")
      }
      Spacer(modifier = Modifier.height(20.dp))
      ButtonDropdownMenu()
      Spacer(modifier = Modifier.height(20.dp))
      StatisticsFlitterRow()
      Spacer(modifier = Modifier.height(20.dp))

      Box(modifier = Modifier
        .height(200.dp)
        .fillMaxWidth()) {
        LineChart(
          modifier = Modifier
            .fillMaxSize(),
          data = remember {
            listOf(
              Line(
                label = "Windows",
                values = listOf(10.0, 15.0, 10.0, 19.0, 15.0, 20.0, 15.0, 25.0),
                color = SolidColor(Color(0xFF23af92)),
                firstGradientFillColor = Color(0xFF2BC0A1).copy(alpha = .5f),
                secondGradientFillColor = Color.Transparent,
                strokeAnimationSpec = tween(2000, easing = EaseInOutCubic),
                gradientAnimationDelay = 1000,
                drawStyle = DrawStyle.Stroke(width = 2.dp),
              )
            )
          },
          animationMode = AnimationMode.Together(delayBuilder = {
            it * 500L
          }),
        )
      }

      Spacer(modifier = Modifier.height(20.dp))

      defaultTransactions.forEach { transaction ->
        HomeListItems(
          imageUri = transaction.imageUri,
          paddingImage = transaction.paddingImage,
          name = transaction.name,
          date = transaction.date,
          amount = transaction.amount,
          amountColor = transaction.amountColor,
        )
      }
    }
  }

}

@Composable
fun StatisticsFlitterRow(onSort: () -> Unit = {}) {
  Row(
    horizontalArrangement = Arrangement.SpaceBetween,
    modifier = Modifier
      .fillMaxWidth()
  ) {
    Text(
      text = stringResource(id = R.string.top_spending),
      style = MaterialTheme.typography.titleLarge,
    )
    Image(
      painter = painterResource(R.drawable.sort_icon),
      contentDescription = "sort icon",
      contentScale = ContentScale.Crop,
      modifier = Modifier
        .padding(horizontal = 5.dp)
        .size(22.dp)
        .clickable { onSort }
    )
  }
}

private val defaultTransactions = listOf(
  TransactionItem(
    imageUri = R.drawable.starbucks_logo,
    paddingImage = 1.dp,
    name = "Starbucks",
    date = "Jan 12, 2022",
    amount = "- \$ 150.00",
    amountColor = "#F95B51".toColor()
  ),
  TransactionItem(
    imageUri = R.drawable.girl_image,
    paddingImage = 10.dp,
    name = "Transfer",
    date = "Yesterday",
    amount = "- \$ 85.00",
    amountColor = "#25A969".toColor()
  ),
  TransactionItem(
    imageUri = R.drawable.youtube,
    paddingImage = 10.dp,
    name = "Youtube",
    date = "Jan 16, 2022",
    amount = "- \$ 11.99",
    amountColor = "#F95B51".toColor()
  )
)
