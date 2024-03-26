package com.motionhack.paktani.chat

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.motionhack.paktani.chat.adapter.MessageAdapter
import com.motionhack.paktani.chat.model.Message
import com.motionhack.paktani.databinding.FragmentChatBinding
import com.motionhack.paktani.util.BotResponse
import com.motionhack.paktani.util.Constans
import com.motionhack.paktani.util.Time
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ChatFragment : Fragment() {
    private var binding : FragmentChatBinding? = null
    private lateinit var messageAdapter : MessageAdapter
    private var messagesList = mutableListOf<Message>()

    private val bothList = listOf("Budi","Heru","Cika","Lala")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentChatBinding.inflate(layoutInflater,container,false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView()
        clickEvents()
        val random = (0..3).random()
        customMessage("Hallo, Today you're speaking with ${bothList[random]}, what can i help for you ?")
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun customMessage(s: String) {
        GlobalScope.launch {
            delay(1000)
            withContext(Dispatchers.Main) {
                val timeStamp = Time.timeStamp()
                messagesList.add(Message(s, Constans.RECEIVER_ID, timeStamp))
                messageAdapter.insertMessage(Message(s, Constans.RECEIVER_ID, timeStamp))

                binding?.rvChat?.scrollToPosition(messageAdapter.itemCount - 1)
            }
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun clickEvents() {
        binding?.imbSendChat?.setOnClickListener {
            sendMessage()
        }
        GlobalScope.launch {
            delay(1000)
        }
    }

    private fun sendMessage() {
        val message =  binding?.tietChat?.text?.toString()
        val timestamp = Time.timeStamp()

        if(message?.isNotEmpty() as Boolean){
            binding?.tietChat?.setText("")

            messageAdapter.insertMessage(Message(message, Constans.SEND_ID,timestamp))
            binding?.rvChat?.scrollToPosition(messageAdapter.itemCount - 1)

            botResponse(message)
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun botResponse(message : String) {
        val timestamp = Time.timeStamp()

        GlobalScope.launch {
            delay(1000)

            withContext(Dispatchers.Main){
                val response = BotResponse.basicResponses(message)
                messageAdapter.insertMessage(Message(response,Constans.RECEIVER_ID,timestamp))

                when (response){
                    Constans.OPEN_GOOGLE -> {
                        val site = Intent(Intent.ACTION_VIEW)
                        site.data = Uri.parse("https://www.google.com/")
                        startActivity(site)
                    }

                    Constans.OPEN_SEARCH -> {
                        val site = Intent(Intent.ACTION_VIEW)
                        val searchTerm : String = message.substringAfter("search")
                        site.data = Uri.parse("https://www.google.com/search?&q=$searchTerm")
                        startActivity(site)
                    }
                }
            }
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun onStart() {
        super.onStart()

        GlobalScope.launch {
            delay(1000)
            withContext(Dispatchers.Main){
                binding?.rvChat?.scrollToPosition(messageAdapter.itemCount - 1)
            }
        }
    }

    private fun recyclerView() {
        messageAdapter = MessageAdapter()
        binding?.rvChat?.adapter = messageAdapter
        binding?.rvChat?.layoutManager = LinearLayoutManager(context)
    }
}