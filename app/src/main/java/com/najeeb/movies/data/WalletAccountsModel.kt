package com.najeeb.movies.data

import com.najeeb.movies.R


data class WalletAccountsModel(
  val imageUri: Int,
  val title: String,
  val subtitle: String,
)

val listAccounts = listOf(
  WalletAccountsModel(
    imageUri = R.drawable.bank_fill,
    title = "Bank Link",
    subtitle = "Connect your bank account to deposit & fund"
  ),
  WalletAccountsModel(
    imageUri = R.drawable.microdeposits,
    title = "Microdeposits",
    subtitle = "Connect bank in 5-7 days"
  ),
  WalletAccountsModel(
    imageUri = R.drawable.paypal,
    title = "Paypal",
    subtitle = "Connect you paypal account"
  ),

  )