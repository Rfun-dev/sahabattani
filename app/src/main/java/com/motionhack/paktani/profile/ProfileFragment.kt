package com.motionhack.paktani.profile

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.motionhack.paktani.R
import com.motionhack.paktani.databinding.FragmentProfileBinding
import com.motionhack.paktani.profile.adapter.ReviewAdapter
import com.motionhack.paktani.util.Dummy

class ProfileFragment : Fragment() {
    private var binding : FragmentProfileBinding? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(layoutInflater,container,false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.btnProfileBack?.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_profileFragment_to_mainFragment)
        }

    }
}