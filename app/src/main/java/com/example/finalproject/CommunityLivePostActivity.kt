package com.example.finalproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.finalproject.databinding.ActivityCommunityLivePostBinding
import com.example.finalproject.ui.community.LiveCommunityPostAdapter

class CommunityLivePostActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCommunityLivePostBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCommunityLivePostBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}