package de.htwberlin.fintracker.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import de.htwberlin.fintracker.R
import de.htwberlin.fintracker.data.db.entities.AppDatabase
import de.htwberlin.fintracker.databinding.FragmentInputDataBinding
import kotlinx.android.synthetic.main.fragment_input_data.*

class InputData : Fragment() {
    // Declaring variables
    private lateinit var binding: FragmentInputDataBinding
    private lateinit var viewModel: InputDataViewModel
    val switch : Switch = switch_income_or_expense
    val switchStatus : Boolean = switch.isChecked()
    val value = binding.value.text
    val message = binding.message.text


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate view and obtain an instance of the binding class
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_input_data,
            container,
            false
        )

        // Initialise ViewModel with ViewModelProvider and databinding
        //val db = AppDatabase(requireContext())
        //val dao = db.getExpenseDao()
        viewModel = ViewModelProvider(this).get(InputDataViewModel::class.java)
        binding.inputDataViewModel = viewModel

        return binding.root
    }

}