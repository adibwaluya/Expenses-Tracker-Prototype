package de.htwberlin.fintracker.screen.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import de.htwberlin.fintracker.R
import de.htwberlin.fintracker.databinding.FragmentLoginBinding

/**
 * A simple [Fragment] subclass.
 */
class UserLoginFragment : Fragment() {

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

        return binding.root
    }

}
