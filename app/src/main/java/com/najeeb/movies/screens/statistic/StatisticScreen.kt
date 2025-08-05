package com.najeeb.movies.screens.statistic

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.najeeb.movies.R
import com.najeeb.movies.components.CustomAppTopBar
import com.najeeb.movies.data.TransactionDetailsExpenseModels
import com.najeeb.movies.data.TransactionDetailsIncomeModels
import com.najeeb.movies.screens.home.HomeListItems
import com.najeeb.movies.screens.statistic.components.ButtonDropdownMenu
import com.najeeb.movies.screens.statistic.components.DateFilterChips
import com.najeeb.movies.ui.theme.ExpenseColor
import com.najeeb.movies.ui.theme.GreenColor
import com.najeeb.movies.ui.theme.IconColor
import ir.ehsannarmani.compose_charts.LineChart
import ir.ehsannarmani.compose_charts.models.AnimationMode


@ExperimentalMaterial3Api
@Composable
fun StatisticScreen(
  viewModel: StatisticsViewModel = viewModel()
) {
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
        .padding(horizontal = 16.dp)
        .fillMaxWidth()
        .verticalScroll(rememberScrollState())
        .padding(padding),
      horizontalAlignment = Alignment.End
    ) {
      DateFilterChips(
        viewModel = viewModel,
        onDateSelected = { selectedDate ->
          viewModel.onDateFilterSelected(selectedDate)
        }

      )

      Spacer(modifier = Modifier.height(20.dp))

      ButtonDropdownMenu(
        selectedItem = viewModel.selectedTransactionType,
        onItemSelected = { type ->
          viewModel.onTransactionTypeSelected(type)
        }
      )

      Spacer(modifier = Modifier.height(20.dp))

      StatisticsFlitterRow(
        onSort = { newSortOrder ->
          viewModel.onSortChanged(newSortOrder)
        },
        isAscending = viewModel.isAscendingSort
      )

      Spacer(modifier = Modifier.height(20.dp))

      Box(
        modifier = Modifier
          .height(200.dp)
          .fillMaxWidth()
      ) {
        LineChart(
          modifier = Modifier.fillMaxSize(),
          data = viewModel.lineChartData,
          animationMode = AnimationMode.Together(delayBuilder = {
            it * 500L
          }),
        )
      }

      Spacer(modifier = Modifier.height(20.dp))

      // Display filtered transactions
      viewModel.filteredTransactions.forEach { transaction ->
        when (transaction) {
          is TransactionDetailsIncomeModels -> HomeListItems(
            modifier = Modifier.padding(vertical = 8.dp),
            imageUri = transaction.imageUri,
            name = transaction.from,
            date = transaction.date,
            amount = transaction.total,
            amountColor = GreenColor,
          )

          is TransactionDetailsExpenseModels -> HomeListItems(
            modifier = Modifier.padding(vertical = 8.dp),
            imageUri = transaction.imageUri,
            name = transaction.total,
            date = transaction.date,
            amount = transaction.total,
            amountColor = ExpenseColor,
          )
        }
      }
    }
  }
}


@Composable
fun StatisticsFlitterRow(
  onSort: (Boolean) -> Unit,
  isAscending: Boolean
) {
  Row(
    horizontalArrangement = Arrangement.SpaceBetween,
    modifier = Modifier
      .fillMaxWidth()
  ) {
    Text(
      text = stringResource(id = R.string.top_spending),
      style = MaterialTheme.typography.titleLarge,
    )
    Row(
      verticalAlignment = Alignment.CenterVertically,
      modifier = Modifier
        .clickable { onSort(!isAscending) }
        .padding(4.dp)
    ) {
      Text(
        text = if (isAscending) "Low to High" else "High to Low",
        style = MaterialTheme.typography.bodySmall.copy(
          color = MaterialTheme.colorScheme.primary
        ),
        modifier = Modifier.padding(end = 4.dp)
      )
      Icon(
        painter = painterResource(
          R.drawable.sort_icon
        ),
        contentDescription = "sort direction",
        tint = MaterialTheme.colorScheme.primary,
        modifier = Modifier.size(16.dp)
      )
    }
  }
}

