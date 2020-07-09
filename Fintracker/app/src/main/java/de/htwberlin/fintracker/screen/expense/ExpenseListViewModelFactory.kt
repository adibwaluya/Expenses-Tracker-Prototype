package de.htwberlin.fintracker.screen.expense

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import de.htwberlin.fintracker.data.db.ExpenseDAO

class ExpenseListViewModelFactory(
    private val dao: ExpenseDAO
):ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ExpenseListViewModel(dao) as T
    }
}