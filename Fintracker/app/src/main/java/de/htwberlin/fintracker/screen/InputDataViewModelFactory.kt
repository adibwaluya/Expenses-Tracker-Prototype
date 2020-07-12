package de.htwberlin.fintracker.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import de.htwberlin.fintracker.data.db.ExpenseDAO
import de.htwberlin.fintracker.data.db.IncomeDAO

class InputDataViewModelFactory(
    private val expenseDAO: ExpenseDAO,
    private val incomeDAO: IncomeDAO
): ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return InputDataViewModel(expenseDAO, incomeDAO) as T
    }
}