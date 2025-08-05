package com.najeeb.movies.screens.statistic

import androidx.compose.animation.core.EaseInOutCubic
import androidx.compose.animation.core.tween
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.najeeb.movies.data.TransactionDetailsExpenseModels
import com.najeeb.movies.data.TransactionDetailsIncomeModels
import com.najeeb.movies.data.transactionList
import com.najeeb.movies.ui.theme.ExpenseColor
import com.najeeb.movies.ui.theme.GreenColor
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.ehsannarmani.compose_charts.models.DrawStyle
import ir.ehsannarmani.compose_charts.models.Line
import javax.inject.Inject

@HiltViewModel
class StatisticsViewModel @Inject constructor() : ViewModel() {
  // UI State
  var selectedDateFilter by mutableStateOf("Day")
    private set

  var selectedTransactionType by mutableStateOf("Income")
    private set

  var isAscendingSort by mutableStateOf(false)
    private set

  // Processed data
  val filteredTransactions: List<Any>
    get() = transactionList
      .filter { transaction ->
        val matchesType = when (selectedTransactionType) {
          "Expense" -> transaction is TransactionDetailsExpenseModels
          "Income" -> transaction is TransactionDetailsIncomeModels
          else -> true
        }

        val matchesDate = true

        matchesType && matchesDate
      }
      .sortedBy { transaction ->
        val amount = when (transaction) {
          is TransactionDetailsIncomeModels -> transaction.total.parseCurrency()
          is TransactionDetailsExpenseModels -> transaction.total.parseCurrency()
        }
        if (isAscendingSort) amount else -amount
      }

  // Chart data
  val lineChartData: List<Line>
    get() = listOf(
      Line(
        label = selectedTransactionType,
        values = when (selectedDateFilter) {
          "Day" -> listOf(10.0, 15.0, 10.0, 19.0, 15.0, 20.0, 15.0, 25.0)
          "Week" -> listOf(50.0, 60.0, 55.0, 70.0, 65.0, 80.0)
          "Month" -> listOf(200.0, 250.0, 300.0, 350.0)
          "Year" -> listOf(1000.0, 1500.0, 2000.0, 2500.0, 3000.0)
          else -> listOf(10.0, 15.0, 10.0, 19.0, 15.0, 20.0, 15.0, 25.0)
        },
        color = SolidColor(
          if (selectedTransactionType == "Expense") ExpenseColor
          else GreenColor
        ),
        firstGradientFillColor = if (selectedTransactionType == "Expense")
          ExpenseColor.copy(alpha = .5f)
        else
          GreenColor.copy(alpha = .5f),
        secondGradientFillColor = Color.Transparent,
        strokeAnimationSpec = tween(2000, easing = EaseInOutCubic),
        gradientAnimationDelay = 1000,
        drawStyle = DrawStyle.Stroke(width = 2.dp),
      )
    )

  // Event handlers
  fun onDateFilterSelected(date: String) {
    selectedDateFilter = date
  }

  fun onTransactionTypeSelected(type: String) {
    selectedTransactionType = type
  }

  fun onSortChanged(newSortOrder: Boolean) {
    isAscendingSort = newSortOrder
  }
}

// Extension function for currency parsing
fun String.parseCurrency(): Double {
  return this.replace("[^0-9.]".toRegex(), "").toDouble()
}

