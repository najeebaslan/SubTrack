# Navigation Optimization

This document explains the navigation optimization implemented with centralized route management and helper classes.

## Overview

The navigation system has been optimized with:
- **ScreenRoutes**: Centralized class containing all screen routes
- **NavigationHelper**: Helper class providing convenient navigation methods
- **Consistent Route Management**: All navigation now uses constants instead of hardcoded strings

## Files Created

### New Files:
- `app/src/main/java/com/najeeb/movies/navigation/ScreenRoutes.kt` - Centralized route constants
- `app/src/main/java/com/najeeb/movies/navigation/NavigationHelper.kt` - Navigation helper methods
- `NAVIGATION_OPTIMIZATION_README.md` - This documentation

### Modified Files:
- `app/src/main/java/com/najeeb/movies/MoviesApp.kt` - Updated to use ScreenRoutes
- `app/src/main/java/com/najeeb/movies/screens/wallet/WalletScreen.kt` - Updated to use NavigationHelper
- `app/src/main/java/com/najeeb/movies/screens/bill_details/BillDetailsScreen.kt` - Updated to use NavigationHelper
- `app/src/main/java/com/najeeb/movies/screens/bill_payment/BillPaymentScreen.kt` - Updated to use NavigationHelper
- `app/src/main/java/com/najeeb/movies/screens/payment_successfully/PaymentSuccessfullyScreen.kt` - Updated to use NavigationHelper

## ScreenRoutes Class

### Structure:
```kotlin
object ScreenRoutes {
    // Main navigation routes
    const val HOME = "home"
    const val STATISTIC = "statistic"
    const val WALLET = "wallet"
    const val PROFILE = "profile"
    
    // Feature routes
    const val ADD_EXPENSE = "addExpense"
    const val CONNECT_WALLET = "connect-wallet"
    
    // Bill and payment flow routes
    const val BILL_DETAILS = "bill-details"
    const val BILL_PAYMENT = "bill-payment"
    const val PAYMENT_SUCCESSFULLY = "payment-successfully"
    
    // Transaction routes
    const val TRANSACTION_DETAILS = "transaction-details"
    
    // Navigation keys for data passing
    object Keys {
        const val TRANSACTION_MODEL = "transactionModel"
        const val BILL_MODEL = "billModel"
        const val BILL_PAYMENT_MODEL = "billPaymentModel"
        const val PAYMENT_SUCCESSFULLY_MODEL = "paymentSuccessfullyModel"
    }
}
```

### Benefits:
- **Type Safety**: No more hardcoded strings
- **Centralized Management**: All routes in one place
- **Easy Refactoring**: Change route names in one location
- **IDE Support**: Auto-completion and refactoring support

## NavigationHelper Class

### Available Methods:

#### Data Navigation Methods:
```kotlin
// Navigate with data passing
NavigationHelper.navigateToTransactionDetails(navController, transaction)
NavigationHelper.navigateToBillDetails(navController, bill)
NavigationHelper.navigateToBillPayment(navController, billPayment)
NavigationHelper.navigateToPaymentSuccessfully(navController, paymentSuccess)
```

#### Simple Navigation Methods:
```kotlin
// Simple navigation without data
NavigationHelper.navigateToConnectWallet(navController)
NavigationHelper.navigateToAddExpense(navController)
NavigationHelper.navigateToHome(navController)
NavigationHelper.popBackStack(navController)
```

### Benefits:
- **Consistent Data Passing**: Standardized way to pass data between screens
- **Reduced Code Duplication**: No more repetitive navigation code
- **Error Prevention**: Centralized navigation logic prevents common mistakes
- **Easy Testing**: Navigation logic can be tested independently

## Usage Examples

### Before Optimization:
```kotlin
// Hardcoded strings and repetitive code
navController.currentBackStackEntry?.savedStateHandle?.set("billModel", bill)
navController.navigate("bill-details")
```

### After Optimization:
```kotlin
// Clean and consistent navigation
NavigationHelper.navigateToBillDetails(navController, bill)
```

### Before Optimization:
```kotlin
// In NavHost
composable("bill-details") {
  val model = remember {
    navController.previousBackStackEntry?.savedStateHandle?.get<UpcomingBillsItem>("billModel")
  }
  // ...
}
```

### After Optimization:
```kotlin
// In NavHost
composable(ScreenRoutes.BILL_DETAILS) {
  val model = remember {
    navController.previousBackStackEntry?.savedStateHandle?.get<UpcomingBillsItem>(ScreenRoutes.Keys.BILL_MODEL)
  }
  // ...
}
```

## Migration Benefits

### Code Quality:
- **Maintainability**: Easier to maintain and update navigation
- **Readability**: Clear intent with descriptive method names
- **Consistency**: Uniform navigation patterns across the app

### Development Experience:
- **IDE Support**: Better auto-completion and refactoring
- **Error Detection**: Compile-time checking for route names
- **Documentation**: Self-documenting navigation code

### Performance:
- **Reduced String Operations**: Constants instead of string literals
- **Optimized Data Passing**: Standardized approach to data transfer

## Best Practices

### Adding New Routes:
1. Add route constant to `ScreenRoutes`
2. Add navigation key if data passing is needed
3. Add helper method to `NavigationHelper`
4. Update NavHost in `MoviesApp.kt`

### Example:
```kotlin
// 1. Add to ScreenRoutes
const val NEW_SCREEN = "new-screen"

// 2. Add key if needed
object Keys {
    const val NEW_MODEL = "newModel"
}

// 3. Add helper method
fun navigateToNewScreen(navController: NavHostController, data: NewModel) {
    navController.currentBackStackEntry?.savedStateHandle?.set(ScreenRoutes.Keys.NEW_MODEL, data)
    navController.navigate(ScreenRoutes.NEW_SCREEN)
}

// 4. Update NavHost
composable(ScreenRoutes.NEW_SCREEN) {
    val model = remember {
        navController.previousBackStackEntry?.savedStateHandle?.get<NewModel>(ScreenRoutes.Keys.NEW_MODEL)
    }
    // ...
}
```

## Current Navigation Flow

The complete payment flow now uses optimized navigation:

1. **Wallet Screen** → `NavigationHelper.navigateToBillDetails()`
2. **Bill Details Screen** → `NavigationHelper.navigateToBillPayment()`
3. **Bill Payment Screen** → `NavigationHelper.navigateToPaymentSuccessfully()`
4. **Payment Successfully Screen** → `NavigationHelper.navigateToHome()`

Each step uses the optimized navigation system, providing a consistent and maintainable codebase.

The navigation optimization significantly improves code quality, maintainability, and developer experience while reducing the risk of navigation-related bugs. 