# Bill Details Screen

This document explains the new Bill Details screen implementation that follows the same design pattern as the TransactionDetailsScreen.

## Overview

The Bill Details screen displays subscription information with payment method selection, similar to the image provided. It includes:

- Service icon and name (e.g., YouTube Premium)
- Date information
- Price breakdown (Price, Fee, Total)
- Payment method selection (Debit Card, PayPal)
- Pay Now button

## Files Created/Modified

### New Files:
- `app/src/main/java/com/najeeb/movies/screens/bill_details/BillDetailsScreen.kt` - Main screen implementation
- `BILL_DETAILS_README.md` - This documentation

### Modified Files:
- `app/src/main/java/com/najeeb/movies/data/TransactionDetailsModels.kt` - Added BillDetailsModel data class
- `app/src/main/java/com/najeeb/movies/MoviesApp.kt` - Added navigation route for bill details
- `app/src/main/java/com/najeeb/movies/screens/wallet/WalletScreen.kt` - Added navigation callback
- `app/src/main/java/com/najeeb/movies/screens/wallet/components/WalletBodyContent.kt` - Updated to support bill details navigation
- `app/src/main/java/com/najeeb/movies/screens/wallet/components/UpcomingBillsListItems.kt` - Made items clickable for bill details

## Features

### Design Consistency
- Uses the same top bar style as TransactionDetailsScreen
- Same background header with teal-green color
- Same card layout with rounded corners
- Consistent typography and spacing

### Payment Method Selection
- Interactive radio button selection
- Visual feedback for selected payment method
- Smooth state management with Compose

### Navigation
- Integrated with existing navigation system
- Proper back stack management
- Data passing through NavController saved state

## How to Use

### Navigate to Bill Details Screen:
```kotlin
// From any screen with NavController
navController.currentBackStackEntry?.savedStateHandle?.set("billModel", billDetailsList.first())
navController.navigate("bill-details")
```

### Add New Bill Details:
```kotlin
// Add to billDetailsList in TransactionDetailsModels.kt
BillDetailsModel(
  serviceName = "Your Service",
  serviceIcon = R.drawable.your_icon,
  date = "Your Date",
  price = "Your Price",
  fee = "Your Fee",
  total = "Your Total",
  debitCardIcon = R.drawable.debit_card_background,
  paypalIcon = R.drawable.paypal
)
```

## Current Implementation

The screen is currently accessible from the Wallet screen's "Upcoming Bills" section. Clicking on any bill item will navigate to the Bill Details screen with the YouTube Premium example data.

## Design Elements

- **Top Bar**: Transparent background with white text and back button
- **Header Background**: Teal-green gradient with circular patterns
- **Main Card**: White background with 30dp rounded corners
- **Payment Options**: Interactive cards with radio button selection
- **Pay Button**: Full-width teal-green button with rounded corners

The implementation follows Material Design 3 principles and maintains consistency with the existing app design. 