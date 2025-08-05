package com.najeeb.movies.screens.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.najeeb.movies.R
import com.najeeb.movies.core.HelperSize.paddingBalanceCard
import com.najeeb.movies.data.TransactionDetailsModels
import com.najeeb.movies.data.transactionList
import com.najeeb.movies.ui.theme.IconColor
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
  username: String = "Enjelin Morgeana",
  balanceInfo: BalanceInfo = BalanceInfo(
    totalBalance = "\$2,548.00",
    income = "\$1,500.00",
    expense = "\$800.00"
  ),
  transactions: List<TransactionDetailsModels> = transactionList,
  sendAgainImages: List<Int> = listOf(
    R.drawable.girl_image,
    R.drawable.person1,
    R.drawable.person2,
    R.drawable.person3,
    R.drawable.person4
  ),
  onNotificationClick: () -> Unit = {},
  onMenuClick: () -> Unit = {},
  onClickTransaction: (TransactionDetailsModels) -> Unit,
) {
  val systemUiController = rememberSystemUiController()
  SideEffect {
    systemUiController.setStatusBarColor(
      color = Color.Transparent,
      darkIcons = false
    )
  }

  val visibleIndices: SnapshotStateList<Int> = remember { mutableStateListOf() }
  val replayKey = remember { mutableStateOf(0) }

  val baseDelayMs = 80L
  val enterDurationMs = 500
  val transactionsCount = transactions.size
  val headerCount = 1
  val footerHeaderIndex = headerCount + transactionsCount
  val sendAgainIndex = footerHeaderIndex + 1
  val totalPositions = sendAgainIndex + 1

  LaunchedEffect(replayKey.value) {
    visibleIndices.clear()
    delay(30)
    for (pos in 0 until totalPositions) {
      visibleIndices.add(pos)
      delay(baseDelayMs)
    }
  }

  Scaffold(
    modifier = Modifier
      .fillMaxSize()
      .background(MaterialTheme.colorScheme.background),
    containerColor = MaterialTheme.colorScheme.background,

    topBar = {
      CenterAlignedTopAppBar(
        title = {},
        navigationIcon = {
          Box(modifier = Modifier.padding(horizontal = 16.dp)) {
            UserGreeting(username)
          }
        },
        actions = {
          Box(modifier = Modifier.padding(horizontal = 16.dp)) {
            NotificationIcon(onClick = onNotificationClick)
          }
        },
        colors = TopAppBarDefaults.topAppBarColors(
          containerColor = Color.Transparent,
          actionIconContentColor = IconColor
        )
      )
    }
  ) { padding ->
    Column(
      modifier = Modifier
        .fillMaxSize()
        .padding(),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      HomeHeader(
        balanceInfo = balanceInfo,
        onMenuClick = onMenuClick
      )

      Spacer(modifier = Modifier.height(paddingBalanceCard.dp))

      LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(vertical = 8.dp)
      ) {
        // Header before transactions: SeeAllRow for Transactions
        item {
          val pos = 0
          Spacer(modifier = Modifier.height(20.dp))
          AnimatedVisibility(
            visible = visibleIndices.contains(pos),
            enter = slideInVertically(
              initialOffsetY = { it },
              animationSpec = tween(enterDurationMs)
            ) + fadeIn(animationSpec = tween(enterDurationMs)),
            exit = fadeOut()
          ) {
            SeeAllRow(title = R.string.transactions_history, subtitle = R.string.see_all)
          }
          Spacer(modifier = Modifier.height(8.dp))
        }

        // Transactions list
        itemsIndexed(transactions) { index, transaction ->
          // compute logical position for this transaction
          val pos = headerCount + index // e.g., 1..N
          AnimatedVisibility(
            visible = visibleIndices.contains(pos),
            enter = slideInVertically(
              initialOffsetY = { it },
              animationSpec = tween(enterDurationMs)
            ) + fadeIn(animationSpec = tween(enterDurationMs)),
            exit = fadeOut()
          ) {
            TransactionItem(transaction = transaction, onClickTransaction = onClickTransaction)
            Spacer(modifier = Modifier.height(12.dp))
          }
        }

        // Footer header: Send again title
        item {
          Spacer(modifier = Modifier.height(20.dp))

          AnimatedVisibility(
            visible = visibleIndices.contains(footerHeaderIndex),
            enter = slideInVertically(
              initialOffsetY = { it },
              animationSpec = tween(enterDurationMs)
            ) + fadeIn(animationSpec = tween(enterDurationMs)),
            exit = fadeOut()
          ) {
            SeeAllRow(title = R.string.send_again, subtitle = R.string.see_all)
            Spacer(modifier = Modifier.height(8.dp))
          }
        }

        // Send again section
        item {
          AnimatedVisibility(
            visible = visibleIndices.contains(sendAgainIndex),
            enter = slideInVertically(
              initialOffsetY = { it },
              animationSpec = tween(enterDurationMs)
            ) + fadeIn(animationSpec = tween(enterDurationMs)),
            exit = fadeOut()
          ) {
            SendAgainSection(sendAgainImages)
            Spacer(modifier = Modifier.height(16.dp))
          }
        }
      }
    }
  }
}
