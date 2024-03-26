package com.motionhack.paktani.chat.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.motionhack.paktani.chat.model.Message
import com.motionhack.paktani.databinding.ItemChatBinding
import com.motionhack.paktani.util.Constans

class MessageAdapter  : RecyclerView.Adapter<MessageAdapter.ViewHolder>() {
    val listMessage = arrayListOf<Message>()
    inner class ViewHolder(val binding: ItemChatBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemChatBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = listMessage.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val message = listMessage[position]

        when(message.id){
            Constans.SEND_ID -> {
                holder.binding.tvRightChat.apply {
                    text = message.message
                    visibility = View.VISIBLE
                }
                holder.binding.tvLeftChat.visibility = View.GONE
            }
            Constans.RECEIVER_ID -> {
                holder.binding.tvLeftChat.apply {
                    text = message.message
                    visibility = View.VISIBLE
                }
                holder.binding.tvRightChat.visibility = View.GONE
            }
        }
    }

    fun insertMessage(message: Message){
        this.listMessage.add(message)
        notifyItemInserted(listMessage.size)
        notifyDataSetChanged()
    }
}