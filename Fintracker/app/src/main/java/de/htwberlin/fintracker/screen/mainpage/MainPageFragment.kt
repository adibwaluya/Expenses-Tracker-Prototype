package de.htwberlin.fintracker.screen.mainpage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import de.htwberlin.fintracker.R
import de.htwberlin.fintracker.databinding.FragmentMainPageBinding
import kotlinx.android.synthetic.main.activity_main.*

/**
 * A simple [Fragment] subclass.
 */
class MainPageFragment : Fragment() {

    private lateinit var binding: FragmentMainPageBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_main_page,
            container,
            false
        )

        binding.incomeButton.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_mainPageFragment_to_incomeList)
        }

        binding.expenseButton.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_mainPageFragment_to_expenseList)
        }

        NavigationUI.setupWithNavController(binding.navView, findNavController())
        return binding.root
    }

}
