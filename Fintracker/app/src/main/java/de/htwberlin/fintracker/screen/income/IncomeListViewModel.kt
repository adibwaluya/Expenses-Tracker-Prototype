package de.htwberlin.fintracker.screen.income

import android.util.Log
import androidx.lifecycle.ViewModel

class IncomeListViewModel : ViewModel() {
    // Added logs to track when VM is initialised and destroyed
    init {
        Log.i("IncomeListViewModel", "IncomeListVM created!")
    }
    override fun onCleared() {
        super.onCleared()
        Log.i("IncomeListViewModel", "IncomeListVM destroyed!")
    }
}