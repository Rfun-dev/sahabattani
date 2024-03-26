package com.motionhack.paktani.fragment

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.Intent.ACTION_GET_CONTENT
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.contentValuesOf
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.firebase.Firebase
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.firestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.storage
import com.motionhack.paktani.R
import com.motionhack.paktani.databinding.FragmentAddProductBinding
import com.motionhack.paktani.model.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.io.ByteArrayOutputStream
import java.util.UUID

class AddProductFragment : Fragment() {
    private var _binding : FragmentAddProductBinding?= null
    private lateinit var databaseReference: DatabaseReference
    private lateinit var storageReference: StorageReference
    private val binding get() = _binding!!
    private var uri: Uri? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       _binding = FragmentAddProductBinding.inflate(layoutInflater)
        databaseReference = FirebaseDatabase.getInstance().getReference("products")
        binding.apply {
            btnTambahGambar.setOnClickListener {
                ActivityResultLauncher.launch("image/*")
            }

            btnSimpan.setOnClickListener {
                val namaBarang = binding.tietNamaBarang.text.toString()
                val deskripsiBarang = binding.tietDeskripsiBarang.text.toString()
                val hargaBarang = binding.tietHargaBarang.text.toString()
                val ratingBarang = binding.tietRatingBarang.text.toString()
                val penjualBarang = binding.tietPenjualBarang.text.toString()
                val lokasiBarang = binding.tietLokasiBarang.text.toString()
                var item : Product
                storageReference = FirebaseStorage.getInstance().getReference("products/images")
                val productId = databaseReference.push().key

                uri?.let {
                    storageReference.child(productId!!).putFile(it).addOnSuccessListener { task ->
                        task.metadata!!.reference!!.downloadUrl.addOnSuccessListener { url ->
                            val productImage = url.toString()
                            item = Product(
                                productId,
                                namaBarang,
                                deskripsiBarang,
                                hargaBarang,
                                ratingBarang,
                                penjualBarang,
                                lokasiBarang,
                                productImage
                            )
                            databaseReference.child(productId).setValue(item).addOnCompleteListener {
                                Toast.makeText(
                                    activity,
                                    "Barang berhasil ditambahkan!",
                                    Toast.LENGTH_SHORT
                                ).show()
                                findNavController().navigate(R.id.action_addProductFragment_to_mainFragment)
                            }.addOnFailureListener {
                                Toast.makeText(
                                    activity,
                                    "Barang gagal ditambahkan",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }
                }
            }

        }
        return binding.root
    }


    private val ActivityResultLauncher = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ){
        binding.ivBarang.setImageURI(it)
        if(it != null) {
            uri = it
        }
    }

}