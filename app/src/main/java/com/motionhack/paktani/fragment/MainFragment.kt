package com.motionhack.paktani.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.motionhack.paktani.R
import com.motionhack.paktani.databinding.FragmentMainBinding

class MainFragment : Fragment() {
    private var binding : FragmentMainBinding? = null
    private var user : FirebaseUser? = null
    private var auth : FirebaseAuth? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(layoutInflater,container,false)
        return binding?.root as View
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = FirebaseAuth.getInstance()
        user = auth?.currentUser
        if (auth == null){
            Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_loginFragment)
        }
        binding?.btnMainLogout?.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_loginFragment)
            FirebaseAuth.getInstance().signOut()
        }
    }
}