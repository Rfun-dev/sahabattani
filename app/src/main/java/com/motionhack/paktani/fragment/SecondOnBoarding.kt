package com.motionhack.paktani.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.motionhack.paktani.R


class SecondOnBoarding : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_second_on_boarding, container, false)
        val next = view.findViewById<TextView>(R.id.btnNext2)
        val viewPager = activity?.findViewById<ViewPager2>(R.id.view_pager)
        next.setOnClickListener {
            viewPager?.currentItem = 2
        }
        return view
    }


}