package de.htwberlin.fintracker.screen.expense

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import de.htwberlin.fintracker.R
import de.htwberlin.fintracker.data.db.entities.AppDatabase
import de.htwberlin.fintracker.data.db.entities.Expense
import de.htwberlin.fintracker.data.repositories.ExpenseRepository
import de.htwberlin.fintracker.databinding.FragmentExpenseListBinding
import kotlinx.coroutines.InternalCoroutinesApi

/**
 * A simple [Fragment] subclass.
 */
class ExpenseList : Fragment() {
    // Create binding and viewmodel variables
    private lateinit var binding: FragmentExpenseListBinding
    // @InternalCoroutinesApi
    private lateinit var viewModel: ExpenseListViewModel

    // @InternalCoroutinesApi
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate view and obtain an instance of the binding class
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_expense_list,
            container,
            false
        )

        // Instantiate database and repository
        val db = AppDatabase(requireContext())
        val repo = ExpenseRepository(db)

        // Initialise ViewModel with ViewModelProvider
        viewModel = ViewModelProvider(this).get(ExpenseListViewModel::class.java)
        binding.expenseListViewModel = viewModel
        viewModel.getAllExpenses().observe(viewLifecycleOwner, Observer { expenses ->
            // Update the expenses in the fragment
            if (expenses.size != 1)
                binding.amountExpenses.text = expenses.toString()
        })

        // TODO: call methods to show expenses database

        // binding.setLifecycleOwner(this)
        return binding.root
    }

    // Methods

    // TODO: Methods for button pressed


    // TODO: Database related

}
