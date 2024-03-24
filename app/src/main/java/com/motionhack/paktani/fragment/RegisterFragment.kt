package com.motionhack.paktani.fragment

import android.app.Activity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth
import com.motionhack.paktani.R
import com.motionhack.paktani.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {
    private lateinit var auth: FirebaseAuth
    private var binding : FragmentRegisterBinding? = null
    private val TAG = RegisterFragment::class.java.name
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(layoutInflater,container,false)
        return binding?.root as View
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = FirebaseAuth.getInstance()
        val email = binding?.tietRegisterEmail?.text
        val password = binding?.tietRegisterPassword?.text
        binding?.btnRegisterRegister?.setOnClickListener {
            Log.d(TAG, "onViewCreated: ${email}")
            binding?.pbLoadingRegister?.visibility = View.VISIBLE
            if(TextUtils.isEmpty(email)){
                binding?.tfRegisterEmail?.error = getString(R.string.enter_emai)
            }
            else if(TextUtils.isEmpty(password)){
                binding?.tfRegisterEmail?.error = getString(R.string.enter_password)
            }else {
                auth.createUserWithEmailAndPassword(email.toString(), password.toString())
                    .addOnCompleteListener(activity as Activity) { task ->
                        if (task.isSuccessful) {
                            Log.d(TAG, "createUserWithEmail:success")
                            Toast.makeText(
                                context,
                                "akun berhasil dibuat.",
                                Toast.LENGTH_SHORT,
                            ).show()
                            val user = auth.currentUser
                            Navigation.findNavController(view).navigate(R.id.action_registerFragment_to_loginFragment)
                        } else {
                            Toast.makeText(
                                context,
                                "membuat akun gagal.",
                                Toast.LENGTH_SHORT,
                            ).show()
                        }
                    }
                binding?.pbLoadingRegister?.visibility = View.GONE
            }
        }
        binding?.btnRegisterLogin?.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_registerFragment_to_loginFragment)
        }
    }
}