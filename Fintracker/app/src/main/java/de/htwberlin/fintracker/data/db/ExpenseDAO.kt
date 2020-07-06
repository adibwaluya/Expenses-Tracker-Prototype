package de.htwberlin.fintracker.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.OnConflictStrategy
import de.htwberlin.fintracker.data.db.entities.Expense

@Dao
interface ExpenseDAO {
    // Show all expenses as a list of Expense data in a chronological order by descending ID
    @Query("SELECT * FROM `List of Expenses` ORDER BY expId DESC")
    fun getAllExpenses(): LiveData<List<Expense>>  // The data will be tracked with LiveData


    // Function to insert more expenses or update the expenses to the database
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addExpenses(expense: Expense)


    // Function to delete expense data
    @Delete
    fun delExpenses(expense: Expense)
}