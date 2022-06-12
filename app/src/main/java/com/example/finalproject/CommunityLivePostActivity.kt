package com.example.finalproject

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.finalproject.LoginActivity.Companion.currentUser
import com.example.finalproject.LoginActivity.Companion.liveCommunityPostDataArray
import com.example.finalproject.data.Post
import com.example.finalproject.databinding.ActivityCommunityLivePostBinding
import com.example.finalproject.ui.community.CommunityFragment.Companion.liveCommunityPostAdapter
import java.sql.Timestamp

class CommunityLivePostActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCommunityLivePostBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCommunityLivePostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.communityLivePostBtn.setOnClickListener {
            val title = binding.title.text.toString()
            val content = binding.content.text.toString()
            val isUnknown = binding.isUnknown.isOn
            var writer = "익명"
            if (!isUnknown) {
                writer = currentUser.name
            }
            liveCommunityPostDataArray.add(0,Post(liveCommunityPostDataArray.size,title,content,Timestamp(System.currentTimeMillis()),writer,0))
            liveCommunityPostAdapter?.notifyDataSetChanged()
            onBackPressed()
        }
    }
}