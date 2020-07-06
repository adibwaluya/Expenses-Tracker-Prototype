package de.htwberlin.fintracker.screen.expense

import android.util.Log
import androidx.lifecycle.ViewModel

class ExpenseListViewModel : ViewModel() {
    // Added logs to track when VM is initialised and destroyed
    init {
        Log.i("ExpenseListViewModel", "ExpenseListVM created!")
    }
    override fun onCleared() {
        super.onCleared()
        Log.i("ExpenseListViewModel", "ExpenseListVM destroyed!")
    }
}