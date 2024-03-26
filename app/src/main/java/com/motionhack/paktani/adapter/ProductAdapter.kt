package com.motionhack.paktani.adapter

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.cardview.widget.CardView
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.motionhack.paktani.R
import com.motionhack.paktani.fragment.MainFragmentDirections
import com.motionhack.paktani.model.Product
import com.squareup.picasso.Picasso

class ProductAdapter(private var items : List<Product>) :
    RecyclerView.Adapter<ProductAdapter.ViewHolder>() {
    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val namaBarang: TextView= itemView.findViewById(R.id.tv_nama_barang)
        val hargaBarang: TextView = itemView.findViewById(R.id.tv_harga_barang)
        val gambarBarang: ImageView = itemView.findViewById(R.id.iv_gambar)
        val btnBeli : Button = itemView.findViewById(R.id.btn_beli)
        val rvItem : CardView = itemView.findViewById(R.id.rv_item_container)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ProductAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false))
    }

    override fun onBindViewHolder(holder: ProductAdapter.ViewHolder, position: Int) {
        val currentItem = items[position]
        Picasso.get().load(currentItem.gambarBarang).into(holder.gambarBarang)
        holder.namaBarang.setText(currentItem.namaBarang)
        holder.hargaBarang.setText(currentItem.hargaBarang)
        holder.btnBeli.setOnClickListener {
            val action = MainFragmentDirections.actionMainFragmentToCheckOutFragment(
                currentItem.namaBarang!!,
                currentItem.penjualBarang!!,
                currentItem.hargaBarang!!,
                currentItem.gambarBarang!!
            )
            findNavController(holder.itemView).navigate(action)
        }
        holder.rvItem.setOnClickListener {
            val action = MainFragmentDirections.actionMainFragmentToDetailProductFragment(
                currentItem.namaBarang!!,
                currentItem.hargaBarang!!,
                currentItem.deskripsiBarang!!,
                currentItem.gambarBarang!!,
                currentItem.penjualBarang!!,
            )
            findNavController(holder.itemView).navigate(action)
        }

    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun searchDataList(searchList: List<Product>) {
        items = searchList
        notifyDataSetChanged()
    }
}