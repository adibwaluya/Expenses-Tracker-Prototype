package de.htwberlin.fintracker.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

const val EXPENSE_ID = 0
@Entity(tableName = "List of Expenses")
data class Expense(
    // Entities for ROOM Database
    var expInfo: String? = null,
    //var expDate: LocalDate? = null,
    var expValue: Double? = null
){
    @PrimaryKey(autoGenerate = true)
    var expId: Int = CURRENT_USER_ID
}