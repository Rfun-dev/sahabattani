package com.motionhack.paktani.fragment

import android.app.Activity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth
import com.motionhack.paktani.R
import com.motionhack.paktani.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    private var binding : FragmentLoginBinding? = null
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(layoutInflater,container,false)
        return binding?.root
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if (currentUser != null) {
            Navigation.findNavController(view as View).navigate(R.id.action_loginFragment_to_mainFragment)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = FirebaseAuth.getInstance()
        val email = binding?.tietLoginEmail?.text
        val password = binding?.tietLoginPassword?.text
        val TAG = LoginFragment::class.java.name
        binding?.btnLoginLogin?.setOnClickListener {
            binding?.pbLoadingLogin?.visibility = View.VISIBLE
            if(TextUtils.isEmpty(email)){
                binding?.tfLoginEmail?.error = getString(R.string.enter_emai)
            }
            else if(TextUtils.isEmpty(password)){
                binding?.tfLoginPassword?.error = getString(R.string.enter_password)
            }else{
                auth.signInWithEmailAndPassword(email.toString(), password.toString())
                    .addOnCompleteListener(activity as Activity) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success")
                            Toast.makeText(
                                context,
                                "login berhasil.",
                                Toast.LENGTH_SHORT,
                            ).show()
                            Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_mainFragment)
                            val user = auth.currentUser
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.exception)
                            Toast.makeText(
                                context,
                                "akun tidak ditemukan.",
                                Toast.LENGTH_SHORT,
                            ).show()
                        }
                    }
                binding?.pbLoadingLogin?.visibility = View.GONE
            }
        }
        binding?.btnLoginRegister?.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }
}