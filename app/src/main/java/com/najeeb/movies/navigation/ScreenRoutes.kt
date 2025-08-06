package com.najeeb.movies.navigation

/**
 * Centralized class containing all screen routes for the app.
 * This helps maintain consistency and makes navigation easier to manage.
 */
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