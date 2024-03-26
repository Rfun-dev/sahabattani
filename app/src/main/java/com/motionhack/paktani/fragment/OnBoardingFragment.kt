package com.motionhack.paktani.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.motionhack.paktani.R
import com.motionhack.paktani.adapter.ViewPagerAdapter
import com.motionhack.paktani.databinding.FragmentOnBoardingBinding
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator


class OnBoardingFragment : Fragment() {
    private var _binding : FragmentOnBoardingBinding?= null
    private val binding get() = _binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_on_boarding, container, false)

        if(onBoardingIsFinished() && !isLogin()) {
            findNavController().navigate(R.id.action_onBoardingFragment_to_loginFragment)
        } else if (isLogin()) {
            findNavController().navigate(R.id.action_onBoardingFragment_to_mainFragment)
        }

        val fragmentList = arrayListOf<Fragment>(
            FirstOnBoardingFragment(),
            SecondOnBoarding(),
            ThirdOnBoarding()
        )


        val adapter = ViewPagerAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle
        )
        val dotsIndicator = view.findViewById<DotsIndicator>(R.id.dots_indicator)
        val viewPager = view.findViewById<ViewPager2>(R.id.view_pager)
        viewPager.adapter = adapter
        dotsIndicator.attachTo(viewPager)



        return view
    }
    private fun isLogin(): Boolean {
        val sharedPreferences = requireActivity().getSharedPreferences("isLogin", Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean("finished",false)
    }
    private fun onBoardingIsFinished(): Boolean {
        val sharedPreferences = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean("finished",false)
    }


}