package de.htwberlin.fintracker.screen.registration

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import de.htwberlin.fintracker.R
import de.htwberlin.fintracker.data.db.entities.AppDatabase
import de.htwberlin.fintracker.data.db.entities.User
import de.htwberlin.fintracker.data.network.MyApi
import de.htwberlin.fintracker.data.network.NetworkConnectionInterceptor
import de.htwberlin.fintracker.data.repositories.UserRepository
import de.htwberlin.fintracker.databinding.FragmentSignUpBinding
import de.htwberlin.fintracker.screen.auth.AuthListener
import de.htwberlin.fintracker.screen.auth.AuthViewModel
import de.htwberlin.fintracker.screen.auth.AuthViewModelFactory

/**
 * A simple [Fragment] subclass.
 */
class UserRegistrationFragment : Fragment(), AuthListener {

    private lateinit var binding: FragmentSignUpBinding
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
        val factory = AuthViewModelFactory(repository)

        val viewModel = ViewModelProvider(this, factory).get(AuthViewModel::class.java)
        binding.viewmodel = viewModel
        viewModel.authListener = this
        viewModel.getLoggedInUser().observe(viewLifecycleOwner, Observer { user ->
            if(user != null){

                // User logged in
                view?.findNavController()?.navigate(R.id.action_userRegistrationFragment_to_mainPageFragment)

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

/**
 * private fun performRegister() {

val email = binding.emailRegistration.text.toString()
val password = binding.passwordRegistration.text.toString()

if (email.isEmpty() || password.isEmpty()) {
Toast.makeText(activity, "Please fill in the empty column", Toast.LENGTH_SHORT).show()
return
}

FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
.addOnCompleteListener {
if(!it.isSuccessful) return@addOnCompleteListener

else
Log.d("Main", "Successfully created user with uid: ${it.result?.user?.uid}")
}
.addOnFailureListener {
Toast.makeText(activity, "Failed to create user: ${it.message}", Toast.LENGTH_SHORT)
}
}
 * */


