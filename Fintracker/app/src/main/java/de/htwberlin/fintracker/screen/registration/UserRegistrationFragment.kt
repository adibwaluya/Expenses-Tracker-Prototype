package de.htwberlin.fintracker.screen.registration

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
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

        binding.alreadyAccount.setOnClickListener { view: View ->
            this.findNavController().navigate(R.id.action_userRegistrationFragment_to_userLoginFragment)
        }

        return binding.root
        }

    }


