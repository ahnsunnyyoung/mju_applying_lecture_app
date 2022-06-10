package com.example.finalproject

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.data.Lecture
import com.example.finalproject.databinding.LecturesDetailsViewBinding
import com.example.finalproject.ui.picked.AppliedLecturesAdapter
import kotlin.math.round

class LecturesDetailsAdapter(
    private val dataSet: Array<Lecture>,
    private val appliedLecturesAdapter: LecturesDetailsAppliedLecturesAdapter,
    private val lecturesDetailsActivity: LecturesDetailsActivity
): RecyclerView.Adapter<LecturesDetailsAdapter.MyViewHolder>() {
    var count: Int = dataSet.size

    class MyViewHolder(val binding: LecturesDetailsViewBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun getItemCount() = count

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LecturesDetailsViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    @SuppressLint("NotifyDataSetChanged", "SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val binding = (holder as MyViewHolder).binding
        val lecture = dataSet[position]
        binding.lecId.text = lecture.id.toString()
        binding.lecName.text = lecture.name
        val competRate:Double = lecture.pickedStuNum.toDouble()/lecture.limitStuNum.toDouble()
        binding.lecContentsFirstLine.text = "${lecture.stuGradeArray[lecture.stuGrade]} ${lecture.majorTypeArray[lecture.campusType][lecture.uniType][lecture.majorType]} ${lecture.credits}학점 ${lecture.hours}시간 ${lecture.profName}"
        binding.lecContentsSecondLine.text = "신청 : ${lecture.appliedStuNum}  제한 : ${lecture.limitStuNum}  담기 : ${lecture.pickedStuNum}  경쟁률 : ${round(competRate*100) / 100}"
        binding.lecContentsThirdLine.text = "${lecture.timeInfo}"

        binding.pickLecture.setOnClickListener {
            LoginActivity.currentUser.pickedList.add(dataSet[position])
            lecturesDetailsActivity.setupBadge()
            lecturesDetailsActivity.toastPickedLectures(dataSet[position].name)
        }

        binding.applyLecture.setOnClickListener {
            LoginActivity.currentUser.appliedList.add(dataSet[position])
            appliedLecturesAdapter.notifyDataSetChanged()
            lecturesDetailsActivity.refreshAppliedLecturesData()
            lecturesDetailsActivity.toastAppliedLectures(dataSet[position].name)
        }

    }
}