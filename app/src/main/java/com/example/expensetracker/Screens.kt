package com.example.expensetracker


sealed class Screens(
    val title: String,
    val icon: Int,
    val route: String
) {
    data object HomeScreen : Screens(
        title = "Home",
        icon = R.drawable.ic_home,
        route = "home_screen"
    )

    data object TransactionHistoryScreen : Screens(
        title = "Transaction History",
        icon = R.drawable.ic_transaction,
        route = "transaction_history_screen"
    )

    data object ReportScreen : Screens(
        title = "Daily Report",
        icon = R.drawable.ic_report,
        route = "report_screen"
    )

    data object NewTransactionScreen : Screens(
        title = "New Transaction",
        icon = 0,
        route = "new_transaction_screen"
    )

}