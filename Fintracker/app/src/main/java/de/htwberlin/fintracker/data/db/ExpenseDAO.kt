package de.htwberlin.fintracker.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import de.htwberlin.fintracker.data.db.entities.Expense

@Dao
interface ExpenseDAO {
    // Show all expenses as a list of Expense data in a chronological order by descending ID
    @Query("SELECT * FROM `List of Expenses` ORDER BY expId DESC")
    fun getAllExpenses(): LiveData<List<Expense>>  // The data will be tracked with LiveData

    // Show one expense at a time
    @Query("SELECT * FROM `List of Expenses` ORDER BY expId DESC LIMIT 1")
    fun getExpense(): Expense


    // Function to insert more expenses to the database
    @Insert
    fun addExpenses(expense: Expense)


    // Function to delete expense data
    @Delete
    fun delExpenses(expense: Expense)
}