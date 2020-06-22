package de.htwberlin.fintracker.screen.expense

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import de.htwberlin.fintracker.R
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

        // TODO: Initialise ViewModel with ViewModelProvider
        viewModel = ViewModelProviders.of(this).get(ExpenseListViewModel::class.java)

        // TODO: call methods to show expenses database

        return binding.root
    }

    // Methods

    // TODO: Methods for button pressed


    // TODO: Database related

}
