package de.htwberlin.fintracker.screen.income

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import de.htwberlin.fintracker.data.db.IncomeDAO

class IncomeListViewModelFactory(
    private val dao: IncomeDAO
): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return IncomeListViewModel(dao) as T
    }

}