package de.htwberlin.fintracker.screen.expense

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import de.htwberlin.fintracker.FintrackerRoomDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch

@InternalCoroutinesApi
class ExpenseListViewModel(application: Application) : AndroidViewModel(application) {

    // Add repository to VM
    private val repository: ExpenseListRepository

    val allExpenses: LiveData<List<ExpenseData>>

    // Add logs to track when VM is initialised and destroyed
    init {
        Log.i("ExpenseListViewModel", "ExpenseListVM created!")

        // create reference to ExpenseListDao through FinTrackerDatabase
        val expenseListDao = FintrackerRoomDatabase.getDatabase(application).expenseListDao()

        // initialise repository
        repository = ExpenseListRepository(expenseListDao)

        // create a list of Expenses using LiveData
        allExpenses = repository.allExpenses
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("ExpenseListViewModel", "ExpenseListVM destroyed!")
    }

    // Launch a coroutine to insert the data in a non-blocking way with a function
    fun insert(expenseData: ExpenseData) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(expenseData)
    }
}