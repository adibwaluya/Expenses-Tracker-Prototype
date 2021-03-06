package de.htwberlin.fintracker.screen.expense

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
    private var expenses = MutableLiveData<List<Expense>>()
    private val expensesToShow = dao.getAllExpenses()

    init {
        initialiseExpense()
    }

    // Functions from Dao

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

    // New click handler for the FAB - will be called with lambda function from XML code
    fun onAddExpense() {
        uiScope.launch {
            val newExpense = Expense()
            insert(newExpense)
            expense.value = getExpenseFromDatabase()
        }
    }

    // Suspend function from the click handler
    private suspend fun insert(newExpense: Expense) {
        withContext(Dispatchers.IO) {
            dao.addExpenses(newExpense)
        }
    }

    // Functions to get all expenses from database
    fun getAllExpenses() = dao.getAllExpenses()

    // Override function
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}