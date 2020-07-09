package de.htwberlin.fintracker.data.repositories

import de.htwberlin.fintracker.data.db.ExpenseDAO
import de.htwberlin.fintracker.data.db.entities.AppDatabase
import de.htwberlin.fintracker.data.db.entities.Expense

class ExpenseRepository(
    private val db: AppDatabase  //,
    // private val dao: ExpenseDAO
) {
    // perform Dao operations to insert or update expenses
    // fun addExpense(expense: Expense) = db.getExpenseDao().addExpenses(expense)


    // function to get all expenses
    // LiveData will be observed
    fun getAllExpenses() = db.getExpenseDao().getAllExpenses()
}