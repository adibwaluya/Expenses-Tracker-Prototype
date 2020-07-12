package de.htwberlin.fintracker.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import de.htwberlin.fintracker.data.db.entities.Income

@Dao
interface IncomeDAO {

    @Query("SELECT * FROM `List of Incomes` ORDER BY incId DESC")
    fun getAllIncomes(): LiveData<List<Income>>

    @Query("SELECT * FROM `List of Incomes` ORDER BY incId DESC LIMIT 1")
    fun getIncome(): Income

    @Insert
    fun addIncome(income: Income)

    @Delete
    fun delIncome(income: Income)
}