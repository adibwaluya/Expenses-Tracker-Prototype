package de.htwberlin.fintracker.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

const val INCOME_ID = 0
@Entity(tableName = "List of Incomes")
data class Income (

    var incInfo: String? = null,
    var incValue: Double? = null

){
    @PrimaryKey(autoGenerate = true)
    var incId: Int = INCOME_ID
}