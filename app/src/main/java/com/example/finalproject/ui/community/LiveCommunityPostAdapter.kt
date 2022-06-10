package com.example.finalproject.ui.community

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.data.Post
import com.example.finalproject.databinding.LiveCommunityPostViewBinding
import com.example.finalproject.ui.picked.PickedLecturesAdapter
import java.text.SimpleDateFormat

class LiveCommunityPostAdapter(
    private val dataSet: ArrayList<Post>,
    ): RecyclerView.Adapter<LiveCommunityPostAdapter.MyViewHolder>() {
        var count:Int = dataSet.size
        class MyViewHolder(val binding: LiveCommunityPostViewBinding) : RecyclerView.ViewHolder(binding.root)

        override fun getItemCount() = dataSet.size

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            return MyViewHolder(LiveCommunityPostViewBinding.inflate(LayoutInflater.from(parent.context),parent,false))
        }

        @SuppressLint("SetTextI18n", "NotifyDataSetChanged")
        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            val binding = (holder as MyViewHolder).binding
            val post = dataSet[position]
            val sdf = SimpleDateFormat("MM/dd HH:mm")
            binding.title.text = post.title
            binding.content.text = post.content
            binding.timestamp.text = sdf.format(post.timestamp)
            binding.like.text = post.likeNum.toString()
        }
}