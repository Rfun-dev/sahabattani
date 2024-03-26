package com.motionhack.paktani.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.motionhack.paktani.R
import com.motionhack.paktani.adapter.ProductAdapter
import com.motionhack.paktani.databinding.FragmentMainBinding
import com.motionhack.paktani.model.Product
import java.util.Locale

class MainFragment : Fragment() {
    private var binding : FragmentMainBinding? = null
    private var user : FirebaseUser? = null
    private var auth : FirebaseAuth? = null
    private lateinit var  firebaseRef : DatabaseReference
    private lateinit var adapter : ProductAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var productList : ArrayList<Product>
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

        binding?.tvSearch?.queryHint = "Cari di Sahabatani"
        recyclerView = view.findViewById(R.id.rv_item)
        recyclerView.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.setHasFixedSize(true)
        productList = arrayListOf()
        firebaseRef = FirebaseDatabase.getInstance().getReference("products")
        firebaseRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                productList.clear()
                if(snapshot.exists()) {
                    for(productSnap in snapshot.children) {
                        val productData = productSnap.getValue(Product::class.java)
                        productList.add(productData!!)
                    }
                    val coursesAdapter = ProductAdapter(productList)
                    recyclerView.adapter = coursesAdapter
                }

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

    }


}