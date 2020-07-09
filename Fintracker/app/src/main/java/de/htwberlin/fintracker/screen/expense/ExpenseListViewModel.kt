package de.htwberlin.fintracker.screen.expense

import androidx.lifecycle.ViewModel
import de.htwberlin.fintracker.data.db.ExpenseDAO

class ExpenseListViewModel(
    private val dao: ExpenseDAO
): ViewModel() {

    fun getAllExpenses() = dao.getAllExpenses()

}