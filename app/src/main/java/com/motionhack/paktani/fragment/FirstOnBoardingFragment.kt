package com.motionhack.paktani.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.motionhack.paktani.R


class FirstOnBoardingFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_first_on_boarding, container, false)
        val next = view.findViewById<TextView>(R.id.btnNext1)
        val viewPager = activity?.findViewById<ViewPager2>(R.id.view_pager)
        next.setOnClickListener {
            viewPager?.currentItem = 1
        }
        return view
    }



}