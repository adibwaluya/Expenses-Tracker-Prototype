package de.htwberlin.fintracker.screen.login

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.ViewUtils
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import de.htwberlin.fintracker.R
import de.htwberlin.fintracker.data.db.entities.AppDatabase
import de.htwberlin.fintracker.data.db.entities.User
import de.htwberlin.fintracker.data.network.MyApi
import de.htwberlin.fintracker.data.repositories.UserRepository
import de.htwberlin.fintracker.databinding.FragmentLoginBinding
import de.htwberlin.fintracker.screen.auth.AuthListener
import de.htwberlin.fintracker.screen.auth.AuthViewModel
import de.htwberlin.fintracker.screen.auth.AuthViewModelFactory
import de.htwberlin.fintracker.util.toast

/**
 * A simple [Fragment] subclass.
 */
class UserLoginFragment : Fragment(), AuthListener {

    private lateinit var binding: FragmentLoginBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_login,
            container,
            false
        )

        binding.loginButton.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_userLoginFragment_to_mainPageFragment)
        }

        binding.signupButton.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_userLoginFragment_to_userRegistrationFragment)
        }

        val api = MyApi()
        val db = AppDatabase(requireContext())

        // this will be need to instantiate AuthViewModel
        val repository = UserRepository(api, db)
        val factory = AuthViewModelFactory(repository)

        val viewModel = ViewModelProvider(this, factory).get(AuthViewModel::class.java)
        binding.viewmodel = viewModel
        viewModel.authListener = this
        viewModel.getLoggedInUser().observe(viewLifecycleOwner, Observer { user ->
            if(user != null){

                // User logged in
                view?.findNavController()?.navigate(R.id.action_userLoginFragment_to_mainPageFragment)

            }
        })

        return binding.root
    }

    override fun onStarted() {
        binding.progresBar.visibility = View.VISIBLE

    }

    override fun onSuccess(user: User) {
        binding.progresBar.visibility = View.INVISIBLE

    }

    override fun onFailure(message: String) {
        binding.progresBar.visibility = View.INVISIBLE
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show()
    }


}
