package de.htwberlin.fintracker.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import de.htwberlin.fintracker.R
import de.htwberlin.fintracker.databinding.FragmentInputDataBinding

class InputData : Fragment() {
    // Create binding and viewmodel variables
    private lateinit var binding: FragmentInputDataBinding
    private lateinit var viewModel: InputDataViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate view and obtain an instance of the binding class
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_input_data,
            container,
            false
        )



        return super.onCreateView(inflater, container, savedInstanceState)
    }

}