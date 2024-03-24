package com.motionhack.paktani.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.motionhack.paktani.R
import com.motionhack.paktani.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    private var binding : FragmentLoginBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(layoutInflater,container,false)

        binding?.btnRegisterLogin?.setOnClickListener {
            Navigation.findNavController(view as View).navigate(R.id.action_loginFragment_to_registerFragment)
        }
        return binding?.root
    }
}