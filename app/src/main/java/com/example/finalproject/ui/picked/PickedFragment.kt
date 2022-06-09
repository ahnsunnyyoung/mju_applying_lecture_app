package com.example.finalproject.ui.picked

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.LoginActivity.Companion.currentUser
import com.example.finalproject.R
import com.example.finalproject.databinding.FragmentPickedBinding

class PickedFragment : Fragment() {

    private var _binding: FragmentPickedBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    var picked_total_lec_num = 0
    var picked_total_lec_credits = 0

    var available_apply_credits = 17
    var applied_total_lec_num = 0
    var applied_total_lec_credits = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPickedBinding.inflate(inflater, container, false)
        val root: View = binding.root

//         책가방 카드뷰 내용 초기화
        refreshPickedLecturesData()

//         수강신청 카드뷰 내용 초기화
        refreshAppliedLecturesData()


        var appliedExpandableView = binding.expandableAppliedLecture

        if (currentUser.appliedList.size==0){
            appliedExpandableView.secondLayout.findViewById<TextView>(R.id.no_lec_text).visibility = View.VISIBLE
        }

        val adapter = AppliedLecturesAdapter(currentUser.appliedList,this)
        appliedExpandableView.secondLayout.findViewById<RecyclerView>(R.id.applied_lectures_recyclerview).adapter = adapter

        appliedExpandableView.parentLayout.setOnClickListener {
            if (appliedExpandableView.isExpanded){
                appliedExpandableView.collapse()
            } else{
                appliedExpandableView.expand()
            }
        }

        val pickedLecturesAdapter = PickedLecturesAdapter(currentUser.pickedList,this, adapter)
        binding.pickedLecturesRecyclerview.layoutManager = LinearLayoutManager(context)
        binding.pickedLecturesRecyclerview.adapter = pickedLecturesAdapter

        return root
    }

    fun refreshPickedLecturesData(){
        picked_total_lec_num = 0
        picked_total_lec_credits = 0
        for (lec in currentUser.pickedList) {
            picked_total_lec_num ++
            picked_total_lec_credits += lec.credits
        }

        binding.pickedTotalLecturesNum.text = "${picked_total_lec_num}과목"
        binding.pickedTotalLecturesCredits.text = "${picked_total_lec_credits}학점"
    }

    fun refreshAppliedLecturesData(){
        if (currentUser.appliedList.size==0){
            binding.expandableAppliedLecture.secondLayout.findViewById<TextView>(R.id.no_lec_text).visibility = View.VISIBLE
        }
        binding.expandableAppliedLecture.findViewById<TextView>(R.id.available_apply_credits).text = "${available_apply_credits}학점"
        applied_total_lec_num = 0
        applied_total_lec_credits = 0
        for (lec in currentUser.appliedList) {
            applied_total_lec_num ++
            applied_total_lec_credits += lec.credits
        }

        binding.expandableAppliedLecture.findViewById<TextView>(R.id.applied_total_lec_num).text = "${applied_total_lec_num}과목"
        binding.expandableAppliedLecture.findViewById<TextView>(R.id.applied_total_lec_credits).text = "${applied_total_lec_credits}학점"

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}