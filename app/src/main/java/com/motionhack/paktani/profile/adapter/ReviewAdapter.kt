package com.motionhack.paktani.profile.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.motionhack.paktani.databinding.ItemReviewBinding
import com.motionhack.paktani.profile.Review

class ReviewAdapter(val context : Context) : RecyclerView.Adapter<ReviewAdapter.ViewHolder>() {
    var profileList = arrayListOf<Review>()
    class ViewHolder(val binding: ItemReviewBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemReviewBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = profileList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.binding){
            val profileItem = profileList[position]
            tvRatingReview.text = context.getString(profileItem.star)
            ivProfileReview.setImageResource(profileItem.image)
            tvTextReview.text = context.getString(profileItem.text)
        }
    }
}