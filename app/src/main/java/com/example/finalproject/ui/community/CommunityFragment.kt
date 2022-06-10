package com.example.finalproject.ui.community

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.finalproject.CommunityLiveActivity
import com.example.finalproject.LoginActivity.Companion.liveCommunityPostDataArray
import com.example.finalproject.data.Post
import com.example.finalproject.databinding.FragmentCommunityBinding

class CommunityFragment : Fragment() {

    private var _binding: FragmentCommunityBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCommunityBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.communityLiveBtn.setOnClickListener {
            val intent: Intent = Intent(activity, CommunityLiveActivity::class.java)
            startActivity(intent)
        }

        val liveCommunityPostAdapter = LiveCommunityPostAdapter(liveCommunityPostDataArray.slice(0..2) as ArrayList<Post>)
        binding.communityLiveRecyclerview.layoutManager = LinearLayoutManager(context)
        binding.communityLiveRecyclerview.adapter = liveCommunityPostAdapter

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}