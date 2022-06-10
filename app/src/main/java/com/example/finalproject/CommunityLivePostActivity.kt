package com.example.finalproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.finalproject.databinding.ActivityCommunityLiveBinding
import com.example.finalproject.ui.community.LiveCommunityPostAdapter

class CommunityLivePostActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCommunityLiveBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCommunityLiveBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val liveCommunityPostAdapter = LiveCommunityPostAdapter(LoginActivity.liveCommunityPostDataArray)
        binding.communityLiveRecyclerview.layoutManager = LinearLayoutManager(this)
        binding.communityLiveRecyclerview.adapter = liveCommunityPostAdapter
    }
}