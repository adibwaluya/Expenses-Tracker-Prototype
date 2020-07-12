package de.htwberlin.fintracker.screen.income

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import de.htwberlin.fintracker.data.db.IncomeDAO
import de.htwberlin.fintracker.data.db.entities.Income
import kotlinx.coroutines.*

class IncomeListViewModel(
    private val dao: IncomeDAO
) : ViewModel() {

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private var income = MutableLiveData<Income>()
    private var incomes = MutableLiveData<List<Income>>()
    private val incomeToShow = dao.getAllIncomes()

    // Added logs to track when VM is initialised and destroyed
    init {
        initialiseIncome()
    }

    private fun initialiseIncome() {
        uiScope.launch {
            income.value = getIncomeFromDatabase()
        }
    }

    private suspend fun getIncomeFromDatabase(): Income? {
        return withContext(Dispatchers.IO){
            var income = dao.getIncome()
            income
        }
    }

    fun onAddIncome(){
        uiScope.launch {
            val newIncome = Income()
            insert(newIncome)
            income.value = getIncomeFromDatabase()
        }
    }

    private suspend fun insert(newIncome: Income){
        withContext(Dispatchers.IO){
            dao.addIncome(newIncome)
        }
    }

    fun getAllIncomes() = dao.getAllIncomes()

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}