package com.najeeb.movies.data

import androidx.compose.ui.unit.dp
import com.najeeb.movies.R
import com.najeeb.movies.screens.home.TransactionItem
import com.najeeb.movies.ui.theme.toColor

 val TransactionsData = listOf(
  TransactionItem(
    imageUri = R.drawable.upwk,
    paddingImage = 1.dp,
    name = "Upwork",
    date = "Today",
    amount = "+ \$ 850.00",
    amountColor = "#25A969".toColor()
  ),
  TransactionItem(
    imageUri = R.drawable.girl_image,
    paddingImage = 10.dp,
    name = "Transfer",
    date = "Yesterday",
    amount = "- \$ 85.00",
    amountColor = "#F95B51".toColor()
  ),
  TransactionItem(
    imageUri = R.drawable.paypal,
    paddingImage = 10.dp,
    name = "Paypal",
    date = "Jan 30, 2022",
    amount = "+ \$ 1,406.00",
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

 val defaultSendAgainImages = listOf(
  R.drawable.girl_image,
  R.drawable.person1,
  R.drawable.person2,
  R.drawable.person3,
  R.drawable.person4
)
