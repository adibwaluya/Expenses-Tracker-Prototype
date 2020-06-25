package de.htwberlin.fintracker.screen.expense

import ExpenseListDao
import androidx.lifecycle.LiveData

// Declaring DAO as a private property to pass it instead of the whole database
class ExpenseListRepository(private val expenseListDao: ExpenseListDao) {

    // Observed LifeData will notify observer when the data has changed
    val allExpenses: LiveData<List<ExpenseData>> = expenseListDao.getAllExpenses()

    suspend fun insert(expenseData: ExpenseData) {
        expenseListDao.insert(expenseData)
    }
}