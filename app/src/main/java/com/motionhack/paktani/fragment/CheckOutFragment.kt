package com.motionhack.paktani.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.StorageReference
import com.motionhack.paktani.R
import com.motionhack.paktani.databinding.FragmentCheckOutBinding
import com.motionhack.paktani.model.Product
import com.motionhack.paktani.model.Transaction
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import kotlin.time.times


class CheckOutFragment : Fragment() {
    private lateinit var databaseReference: DatabaseReference
    private val args: CheckOutFragmentArgs by navArgs()
    private var _binding: FragmentCheckOutBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCheckOutBinding.inflate(layoutInflater)

        val jenisPembayaran = arrayOf("Transfer - BNI", "Transfer - BCA", "Transfer - BRI", "Cash On Delivery")
        val jenisPengiriman = arrayOf("JNE - YES","JNE - REG", "JNE - OKE", "JNE - JTR", "TiKi - SDS", "TiKi - ONS", "TiKi - REG", "TiKi - TRC", "JNT - REG")
        val arrayPembayaranAdapter = ArrayAdapter<String>(requireContext(), android.R.layout.simple_spinner_dropdown_item,jenisPembayaran)
        val arrayPengirimanAdapter = ArrayAdapter<String>(requireContext(), android.R.layout.simple_spinner_dropdown_item,jenisPengiriman)
        var valueJenisPembayaran = ""
        var valueJenisPengiriman = ""
        databaseReference = FirebaseDatabase.getInstance().getReference("transactions")
        var number = 1
        var jumlah = 1
        var trans : Transaction
        binding.apply {
            Picasso.get().load(args.gambarBarang).into(gambarBarang)
            tvNamaBarang.setText(args.namaBarang)
            tvPenjual.setText(args.penjual)
            tvJumlahHarga.setText(args.harga)
            btnBack.setOnClickListener {
                findNavController().navigate(R.id.action_checkOutFragment_to_mainFragment)
            }
            btnPlus.setOnClickListener {
                number+= 1
                jumlahPembelian.setText(number.toString())
                jumlah = number * args.harga.toInt()
                tvJumlahHarga.setText(jumlah.toString())
            }
            btnMinus.setOnClickListener {
                number-= 1
                jumlahPembelian.setText(number.toString())
                jumlah = number * args.harga.toInt()
                tvJumlahHarga.setText(jumlah.toString())
            }
            val transId = databaseReference.push().key
            btnCheckOut.setOnClickListener {
                trans = Transaction(
                   transId,
                    tvNamaBarang.text.toString(),
                    etProvinsi.text.toString(),
                    etKabupaten.text.toString(),
                    etKecamatan.text.toString(),
                    etKelurahan.text.toString(),
                    etAlamatLengkap.text.toString(),
                    etKodePos.text.toString(),
                    valueJenisPembayaran,
                    valueJenisPengiriman,
                    jumlahPembelian.text.toString(),
                    tvJumlahHarga.text.toString(),
                    args.gambarBarang,
                    LocalDateTime.now().toString()
                )

                databaseReference.child(transId!!).setValue(trans).addOnCompleteListener {
                    Toast.makeText(
                        activity,
                        "Barang berhasil ditambahkan!",
                        Toast.LENGTH_SHORT
                    ).show()
                    findNavController().navigate(R.id.action_checkOutFragment_to_mainFragment)
                }.addOnFailureListener {
                    Toast.makeText(
                        activity,
                        "Barang gagal ditambahkan",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            spPengiriman.adapter = arrayPengirimanAdapter
            spPengiriman.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    valueJenisPengiriman = jenisPengiriman[position]
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }

            }

            spPembayaran.adapter = arrayPembayaranAdapter
            spPembayaran.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    valueJenisPembayaran = jenisPembayaran[position]
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }

            }



        }
        // Inflate the layout for this fragment
        return binding.root
    }


}