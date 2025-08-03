package com.najeeb.movies.data

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.najeeb.movies.R

val UpcomingBillsData = listOf(
  UpcomingBillsItem(
    imageUri = R.drawable.youtube,
    paddingImage = 10.dp,
    name = "Youtube",
    date = "Jan 16, 2022",
  ),
  UpcomingBillsItem(
    imageUri = R.drawable.electricity_icon,
    paddingImage = 7.dp,
    name = "Electricity",
    date = "Mar 28, 2022",
  ),
  UpcomingBillsItem(
    imageUri = R.drawable.home_icon_fill,
    paddingImage = 10.dp,
    name = "House Rent",
    date = "Mar 31, 2022",
  ),
  UpcomingBillsItem(
    imageUri = R.drawable.spotify_icon,
    paddingImage = 10.dp,
    name = "Spotify",
    date = "Feb 28, 2022",
  ),

)

data class UpcomingBillsItem(
  val imageUri: Int,
  val paddingImage: Dp,
  val name: String,
  val date: String,
)