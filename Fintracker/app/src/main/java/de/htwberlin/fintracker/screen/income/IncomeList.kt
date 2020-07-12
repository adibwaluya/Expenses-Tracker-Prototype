package de.htwberlin.fintracker.screen.income

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import de.htwberlin.fintracker.R
import de.htwberlin.fintracker.databinding.FragmentIncomeListBinding

/**
 * A simple [Fragment] subclass.
 */
class IncomeList : Fragment() {

    private lateinit var binding: FragmentIncomeListBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_income_list,
            container,
            false
        )

        return binding.root
    }

}
