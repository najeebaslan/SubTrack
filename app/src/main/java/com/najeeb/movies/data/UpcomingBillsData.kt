package com.najeeb.movies.data

import android.os.Parcelable
import com.najeeb.movies.R
import kotlinx.parcelize.Parcelize

@Parcelize
data class UpcomingBillsItem(
  val imageUri: Int,
  val name: String,
  val date: String,
  val time: String, // ⬅️ Added field
  val price: String,
  val fee: String,
  val total: String,
  val debitCardIcon: Int,
  val paypalIcon: Int
) : Parcelable

val UpcomingBillsData = listOf(
  UpcomingBillsItem(
    imageUri = R.drawable.youtube,
    name = "Youtube",
    date = "Jan 16, 2022",
    time = "10:00 AM",
    price = "$ 11.99",
    fee = "$ 1.99",
    total = "$13.98",
    debitCardIcon = R.drawable.credit_card,
    paypalIcon = R.drawable.paypal
  ),
  UpcomingBillsItem(
    imageUri = R.drawable.electricity_icon,
    name = "Electricity",
    date = "Mar 28, 2022",
    time = "08:45 AM",
    price = "$ 45.00",
    fee = "$ 2.50",
    total = "$47.50",
    debitCardIcon = R.drawable.credit_card,
    paypalIcon = R.drawable.paypal
  ),
  UpcomingBillsItem(
    imageUri = R.drawable.home_icon_fill,
    name = "House Rent",
    date = "Mar 31, 2022",
    time = "09:00 AM",
    price = "$ 1200.00",
    fee = "$ 0.00",
    total = "$1200.00",
    debitCardIcon = R.drawable.credit_card,
    paypalIcon = R.drawable.paypal
  ),
  UpcomingBillsItem(
    imageUri = R.drawable.spotify_icon,
    name = "Spotify",
    date = "Feb 28, 2022",
    time = "07:30 AM",
    price = "$ 9.99",
    fee = "$ 1.00",
    total = "$10.99",
    debitCardIcon = R.drawable.credit_card,
    paypalIcon = R.drawable.paypal
  )
)

@Parcelize
data class BillPaymentModel(
  val serviceName: String,
  val serviceIcon: Int,
  val paymentMethod: String,
  val price: String,
  val fee: String,
  val date: String,
  val transactionId: String = "TXN-2024-001234",
  val time: String,
  val total: String
) : Parcelable

