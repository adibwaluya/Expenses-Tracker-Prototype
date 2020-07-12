package de.htwberlin.fintracker.screen.registration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import de.htwberlin.fintracker.R
import de.htwberlin.fintracker.data.db.entities.AppDatabase
import de.htwberlin.fintracker.data.db.entities.User
import de.htwberlin.fintracker.data.network.MyApi
import de.htwberlin.fintracker.data.network.NetworkConnectionInterceptor
import de.htwberlin.fintracker.data.repositories.UserRepository
import de.htwberlin.fintracker.databinding.FragmentSignUpBinding
import de.htwberlin.fintracker.screen.auth.AuthListener

/**
 * A simple [Fragment] subclass.
 */
class UserRegistrationFragment : Fragment(), AuthListener {

    private lateinit var binding: FragmentSignUpBinding
    private lateinit var viewModel: UserRegistrationViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_sign_up,
            container,
            false
        )

        binding.alreadyAccount.setOnClickListener { view: View ->
            this.findNavController().navigate(R.id.action_userRegistrationFragment_to_userLoginFragment)
        }

        // TODO: BAD PRACTICE! Replace with dependency injection
        val networkConnectionInterceptor = NetworkConnectionInterceptor(context!!.applicationContext)
        val api = MyApi(networkConnectionInterceptor)
        val db = AppDatabase(requireContext())

        // this will be need to instantiate AuthViewModel
        val repository = UserRepository(api, db)
        val factory = UserRegistrationViewModelFactory(repository)

        viewModel = ViewModelProvider(this, factory).get(UserRegistrationViewModel::class.java)
        binding.signupviewmodel = viewModel
        viewModel.authListener = this
        viewModel.SignedUpUser().observe(viewLifecycleOwner, Observer { user ->
            if(user != null){

                // User logged in
                view?.findNavController()?.navigate(R.id.action_userRegistrationFragment_to_mainPageFragment)

            }
        })

        binding.setLifecycleOwner(this)
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



