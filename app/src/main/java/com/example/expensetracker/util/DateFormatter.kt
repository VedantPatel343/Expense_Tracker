package com.example.expensetracker.util

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
fun LocalDate.toFormattedDate() : String {
    val pattern = "dd-MM-yyyy"
    val formatter = DateTimeFormatter.ofPattern(pattern)
    return this.format(formatter)
}