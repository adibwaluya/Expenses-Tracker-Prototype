package de.htwberlin.fintracker.screen.expense

import android.util.Log
import androidx.lifecycle.ViewModel
import de.htwberlin.fintracker.data.repositories.ExpenseRepository

class ExpenseListViewModel(
    private val repository: ExpenseRepository
) : ViewModel() {
    // Added logs to track when VM is initialised and destroyed
    init {
        Log.i("ExpenseListViewModel", "ExpenseListVM created!")
    }
    override fun onCleared() {
        super.onCleared()
        Log.i("ExpenseListViewModel", "ExpenseListVM destroyed!")
    }

    // Observe user changes in the local db
    fun getAllExpenses() = repository.getAllExpenses()


}