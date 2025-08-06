package com.najeeb.movies.data

import android.os.Parcelable
import com.najeeb.movies.R
import kotlinx.parcelize.Parcelize

@Parcelize
sealed class TransactionDetailsModels(
  open val imageUri: Int,
  open val status: String,
  open val time: String,
  open val date: String,
  open val total: String,
  open val fee: String
) : Parcelable

@Parcelize
data class TransactionDetailsIncomeModels(
  val from: String,
  val earnings: String,
  override val imageUri: Int,
  override val status: String,
  override val time: String,
  override val date: String,
  override val total: String,
  override val fee: String
) : TransactionDetailsModels(imageUri, status, time, date, total, fee), Parcelable

@Parcelize
data class TransactionDetailsExpenseModels(
  val to: String,
  val spending: String,
  override val imageUri: Int,
  override val status: String,
  override val time: String,
  override val date: String,
  override val total: String,
  override val fee: String
) : TransactionDetailsModels(imageUri, status, time, date, total, fee), Parcelable


val transactionList = listOf<TransactionDetailsModels>(
  TransactionDetailsIncomeModels(
    from = "Starbucks",
    earnings = "\$1,200.00",
    imageUri = R.drawable.starbucks_logo,
    status = "Income",
    time = "02:45 PM",
    date = "Mar 02, 2022",
    total = "\$1,160.00",
    fee = "- \$40.00"
  ),

  TransactionDetailsExpenseModels(
    to = "TransferWise",
    spending = "\$980.00",
    imageUri = R.drawable.girl_image,
    status = "Expense",
    time = "09:10 AM",
    date = "Mar 10, 2022",
    total = "\$960.00",
    fee = "- \$20.00"
  ),
  TransactionDetailsIncomeModels(
    from = "PayPal",
    earnings = "\$1,200.00",
    imageUri = R.drawable.paypal,
    status = "Income",
    time = "02:45 PM",
    date = "Mar 02, 2022",
    total = "\$1,160.00",
    fee = "- \$40.00"
  ),

  TransactionDetailsIncomeModels(
    from = "YouTube",
    earnings = "\$530.00",
    imageUri = R.drawable.youtube,
    status = "Income",
    time = "05:00 PM",
    date = "Mar 15, 2022",
    total = "\$510.00",
    fee = "- \$20.00"
  ),
  TransactionDetailsExpenseModels(
    to = "Fiverr Purchase",
    spending = "\$560.00",
    imageUri = R.drawable.fiverr,
    status = "Expense",
    time = "06:50 PM",
    date = "Mar 12, 2022",
    total = "\$560.00",
    fee = "- \$0.00"
  ),

  TransactionDetailsIncomeModels(
    from = "Upwork",
    earnings = "\$870.00",
    imageUri = R.drawable.upwk,
    status = "Income",
    time = "10:00 AM",
    date = "Feb 30, 2022",
    total = "\$850.00",
    fee = "- \$20.00"
  ),
  TransactionDetailsExpenseModels(
    to = "Dribbble Pro",
    spending = "\$120.00",
    imageUri = R.drawable.dribbble,
    status = "Expense",
    time = "08:30 AM",
    date = "Mar 05, 2022",
    total = "\$120.00",
    fee = "- \$0.00"
  ),

  TransactionDetailsIncomeModels(
    from = "Google Ads",
    earnings = "\$400.00",
    imageUri = R.drawable.google_ads_icon,
    status = "Income",
    time = "11:25 AM",
    date = "Mar 20, 2022",
    total = "\$380.00",
    fee = "- \$20.00"
  ),

  TransactionDetailsExpenseModels(
    to = "Toptal Subscription",
    spending = "\$820.00",
    imageUri = R.drawable.toptal,
    status = "Expense",
    time = "01:40 PM",
    date = "Mar 18, 2022",
    total = "\$820.00",
    fee = "- \$0.00"
  ),
  )


