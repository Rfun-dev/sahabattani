package com.motionhack.paktani.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.motionhack.paktani.R
import com.motionhack.paktani.databinding.FragmentDetailProductBinding
import com.motionhack.paktani.profile.adapter.ReviewAdapter
import com.motionhack.paktani.util.Dummy
import com.squareup.picasso.Picasso


class DetailProductFragment : Fragment() {
    private var _binding : FragmentDetailProductBinding? = null
    private val args: DetailProductFragmentArgs by navArgs()
    private lateinit var adapter : ReviewAdapter

    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDetailProductBinding.inflate(layoutInflater)
        binding.apply {
            Picasso.get().load(args.gambarBarang).into(ivBarangDetail)
            namaBarang.setText(args.namaBarang).toString()
            hargaBarang.setText("Rp. " + args.hargaBarang).toString()
            deskripsiBarang.setText(args.deskripsiBarang).toString()
            btnBackDetail.setOnClickListener {
                findNavController().navigate(R.id.action_detailProductFragment_to_mainFragment)
            }
            btnBeli.setOnClickListener {
                val action = DetailProductFragmentDirections.actionDetailProductFragmentToCheckOutFragment(
                    args.namaBarang!!,
                    args.penjual!!,
                    args.hargaBarang!!,
                    args.gambarBarang!!
                )
                findNavController().navigate(action)
            }
            btnChat.setOnClickListener {
                findNavController().navigate(R.id.action_detailProductFragment_to_chatFragment)
            }
        }
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()
    }

    private fun setRecyclerView() {
        binding.apply{
            adapter = ReviewAdapter(context as Context)
            adapter.profileList = Dummy.profileList
            rvReviewDetail.adapter = adapter
            rvReviewDetail.layoutManager = LinearLayoutManager(context)
        }
    }

}