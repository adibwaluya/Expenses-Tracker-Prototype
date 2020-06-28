package de.htwberlin.fintracker.screen.expense

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "ListOfExpenses")
data class ExpenseData (
    // ExpId as Primary Key
    @PrimaryKey(autoGenerate = true)
    var ExpId: Int = 0,
    // Optional ColumnInfo
    @ColumnInfo(name = "ExpenseInfo")
    var expInfo: String = "",
    @ColumnInfo(name = "ExpenseDate")
    var expDate: Date,
    @ColumnInfo(name = "ExpenseValue")
    var expValue: Double = 0.0
)