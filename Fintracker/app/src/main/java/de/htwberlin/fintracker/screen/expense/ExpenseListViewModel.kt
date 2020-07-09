package de.htwberlin.fintracker.screen.expense

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import de.htwberlin.fintracker.data.db.ExpenseDAO
import de.htwberlin.fintracker.data.db.entities.Expense
import kotlinx.coroutines.*

class ExpenseListViewModel(
    private val dao: ExpenseDAO
): ViewModel() {

    // Declaring variables
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private var expense = MutableLiveData<Expense>()

    init {
        initialiseExpense()
    }

    private fun initialiseExpense() {
        uiScope.launch {
            expense.value = getExpenseFromDatabase()
        }
    }

    private suspend fun getExpenseFromDatabase(): Expense? {
        return withContext(Dispatchers.IO) {
            var expense = dao.getExpense()
            expense
        }
    }


    // Functions from Dao
    fun getAllExpenses() = dao.getAllExpenses()

    // Override function
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}