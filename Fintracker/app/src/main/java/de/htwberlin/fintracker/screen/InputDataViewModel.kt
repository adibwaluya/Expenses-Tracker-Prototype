package de.htwberlin.fintracker.screen

import androidx.lifecycle.ViewModel
import de.htwberlin.fintracker.data.db.ExpenseDAO
import de.htwberlin.fintracker.data.db.IncomeDAO
import de.htwberlin.fintracker.data.db.entities.Expense
import de.htwberlin.fintracker.data.db.entities.Income
import kotlinx.coroutines.*

class InputDataViewModel(
    private val expenseDAO: ExpenseDAO,
    private val incomeDAO: IncomeDAO
) : ViewModel() {
    // Declaring variables
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private var expense = Expense()
    private var income = Income()
    private val inputData = InputData()
    private val switchStatus = inputData.switchStatus


    // New click handler for the button - will be called with lambda function from XML code
    /**
     * Method to input the data based on what the user typed in
     */
    fun onInputData() {
        uiScope.launch {
            // check whether the input data an income or expense and call the right function
            if (switchStatus){
                insertIncome(income)
            } else if (!switchStatus){
                insertExpense(expense)
            }
        }
    }


    private suspend fun insertIncome(income: Income) {
        withContext(Dispatchers.IO){
            // set the value from user to the income entity
            income.incValue = inputData.value.toString().toDouble()
            income.incInfo = inputData.message.toString()
            incomeDAO.addIncome(income)
        }
    }


    private suspend fun insertExpense(expense: Expense) {
        // set the value from user to the expense entity
        expense.expValue = inputData.value.toString().toDouble()
        expense.expInfo = inputData.message.toString()
        expenseDAO.addExpenses(expense)
    }


    // Override onCleared function
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}