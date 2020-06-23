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
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import de.htwberlin.fintracker.R
import de.htwberlin.fintracker.databinding.FragmentSignUpBinding

/**
 * A simple [Fragment] subclass.
 */
class UserRegistrationFragment : Fragment() {

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

        binding.registerButton.setOnClickListener {
            performRegister()
        }

        binding.alreadyAccount.setOnClickListener { view: View ->
            this.findNavController().navigate(R.id.action_userRegistrationFragment_to_userLoginFragment)
        }

        return binding.root
        }

    private fun performRegister() {

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

    }


