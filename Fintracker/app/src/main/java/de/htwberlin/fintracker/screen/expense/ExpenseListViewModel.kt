package de.htwberlin.fintracker.screen.expense

import android.util.Log
import androidx.lifecycle.ViewModel

class ExpenseListViewModel : ViewModel() {
    // Added logs to track when VM is initialised and destroyed
    init {
        Log.i("ExpenseListViewModel", "ExpenseListVM created!")
        // TODO: create reference to ExpenseListDao through FinTrackerDatabase


        // TODO: do we need repository?


        // TODO: create a list of Expenses using LiveData
    }
    override fun onCleared() {
        super.onCleared()
        Log.i("ExpenseListViewModel", "ExpenseListVM destroyed!")
    }

    // TODO: Launch a coroutine to insert the data in a non-blocking way with a function
}