package com.najeeb.movies.navigation

import androidx.navigation.NavHostController
import com.najeeb.movies.data.TransactionDetailsModels
import com.najeeb.movies.data.UpcomingBillsItem
import com.najeeb.movies.data.BillPaymentModel

/**
 * Helper class for navigation operations.
 * Provides convenient methods for common navigation patterns.
 */
object NavigationHelper {
    
    /**
     * Navigate to transaction details screen
     */
    fun navigateToTransactionDetails(
        navController: NavHostController,
        transaction: TransactionDetailsModels
    ) {
        navController.currentBackStackEntry?.savedStateHandle?.set(
            ScreenRoutes.Keys.TRANSACTION_MODEL, 
            transaction
        )
        navController.navigate(ScreenRoutes.TRANSACTION_DETAILS)
    }
    
    /**
     * Navigate to bill details screen
     */
    fun navigateToBillDetails(
        navController: NavHostController,
        bill: UpcomingBillsItem
    ) {
        navController.currentBackStackEntry?.savedStateHandle?.set(
            ScreenRoutes.Keys.BILL_MODEL, 
            bill
        )
        navController.navigate(ScreenRoutes.BILL_DETAILS)
    }
    
    /**
     * Navigate to bill payment screen
     */
    fun navigateToBillPayment(
        navController: NavHostController,
        billPayment: BillPaymentModel
    ) {
        navController.currentBackStackEntry?.savedStateHandle?.set(
            ScreenRoutes.Keys.BILL_PAYMENT_MODEL, 
            billPayment
        )
        navController.navigate(ScreenRoutes.BILL_PAYMENT)
    }
    
    /**
     * Navigate to payment successfully screen
     */
    fun navigateToPaymentSuccessfully(
        navController: NavHostController,
        paymentSuccess: BillPaymentModel
    ) {
        navController.currentBackStackEntry?.savedStateHandle?.set(
            ScreenRoutes.Keys.PAYMENT_SUCCESSFULLY_MODEL, 
            paymentSuccess
        )
        navController.navigate(ScreenRoutes.PAYMENT_SUCCESSFULLY)
    }
    
    /**
     * Navigate to home screen and clear back stack
     */
    fun navigateToHome(navController: NavHostController) {
        navController.navigate(ScreenRoutes.HOME) {
            popUpTo(ScreenRoutes.HOME) { inclusive = true }
        }
    }
    
    /**
     * Navigate to connect wallet screen
     */
    fun navigateToConnectWallet(navController: NavHostController) {
        navController.navigate(ScreenRoutes.CONNECT_WALLET)
    }
    
    /**
     * Navigate to add expense screen
     */
    fun navigateToAddExpense(navController: NavHostController) {
        navController.navigate(ScreenRoutes.ADD_EXPENSE)
    }
    
    /**
     * Pop back stack
     */
    fun popBackStack(navController: NavHostController) {
        navController.popBackStack()
    }
} 