package de.htwberlin.fintracker.screen.expense

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import de.htwberlin.fintracker.R
import de.htwberlin.fintracker.databinding.FragmentExpenseListBinding

/**
 * A simple [Fragment] subclass.
 */
class ExpenseList : Fragment() {

    private lateinit var binding: FragmentExpenseListBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_expense_list,
            container,
            false
        )

        return binding.root
    }

}
