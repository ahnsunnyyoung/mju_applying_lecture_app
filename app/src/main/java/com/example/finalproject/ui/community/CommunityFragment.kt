package com.example.finalproject.ui.community

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.finalproject.CommunityLivePostActivity
import com.example.finalproject.LoginActivity
import com.example.finalproject.databinding.FragmentCommunityBinding

class CommunityFragment : Fragment() {

    private var _binding: FragmentCommunityBinding? = null
    companion object {
        var liveCommunityPostAdapter: LiveCommunityPostAdapter? = null
    }

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


        liveCommunityPostAdapter = LiveCommunityPostAdapter(LoginActivity.liveCommunityPostDataArray)
        binding.communityLiveRecyclerview.layoutManager = LinearLayoutManager(context)
        binding.communityLiveRecyclerview.adapter = liveCommunityPostAdapter

        binding.communityLivePostBtn.setOnClickListener {
            val intent: Intent = Intent(context, CommunityLivePostActivity::class.java)
            startActivity(intent)
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}