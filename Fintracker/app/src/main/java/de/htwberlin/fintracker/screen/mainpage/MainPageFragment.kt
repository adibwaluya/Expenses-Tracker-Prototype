package de.htwberlin.fintracker.screen.mainpage

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import de.htwberlin.fintracker.R
import de.htwberlin.fintracker.databinding.FragmentMainPageBinding

/**
 * A simple [Fragment] subclass.
 */
class MainPageFragment : Fragment() {

    private lateinit var binding: FragmentMainPageBinding
    private lateinit var drawerLayout: DrawerLayout
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

        /**
         * Use data binding to navigate to input data layout
         */
        binding.addFromMainPage.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_mainPageFragment_to_inputData)
        }

        setHasOptionsMenu(true)
        drawerLayout = binding.drawerLayout
        NavigationUI.setupWithNavController(binding.navView, findNavController())
        return binding.root
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.options_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item!!,
            view!!.findNavController())
                || super.onOptionsItemSelected(item)
    }

}
