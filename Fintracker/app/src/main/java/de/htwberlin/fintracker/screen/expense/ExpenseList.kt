package de.htwberlin.fintracker.screen.expense

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import de.htwberlin.fintracker.R
import de.htwberlin.fintracker.data.db.entities.AppDatabase
import de.htwberlin.fintracker.databinding.FragmentExpenseListBinding

/**
 * A simple [Fragment] subclass.
 */
class ExpenseList : Fragment() {
    // Create binding and viewmodel variables
    private lateinit var binding: FragmentExpenseListBinding
    private lateinit var viewModel: ExpenseListViewModel

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

        // Initialise ViewModel with ViewModelProvider and databinding
        val db = AppDatabase(requireContext())
        val dao = db.getExpenseDao()
        val factory = ExpenseListViewModelFactory(dao)

        viewModel = ViewModelProvider(this, factory).get(ExpenseListViewModel::class.java)
        binding.expenseListViewModel = viewModel
        binding.setLifecycleOwner(this)

        return binding.root
    }

}
