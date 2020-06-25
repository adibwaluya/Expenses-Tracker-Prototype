package de.htwberlin.fintracker.screen.expense

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "ListOfExpenses")
data class ExpenseData (
    @PrimaryKey(autoGenerate = true) val id: Int,
    // Optional ColumnInfo
    @ColumnInfo(name = "Expenses")
    val info: String,
    val date: Date,
    val value: Double
)