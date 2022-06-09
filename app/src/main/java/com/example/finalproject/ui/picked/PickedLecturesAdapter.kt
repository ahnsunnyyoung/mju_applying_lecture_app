package com.example.finalproject.ui.picked

import android.annotation.SuppressLint
import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.LoginActivity.Companion.currentUser
import com.example.finalproject.MainActivity
import com.example.finalproject.R
import com.example.finalproject.data.Lecture
import com.example.finalproject.databinding.PickedLecturesViewBinding
import kotlin.math.round


class PickedLecturesAdapter(
    private val dataSet: ArrayList<Lecture>,
    pickedFragment: PickedFragment,
    appliedLecturesAdapter: AppliedLecturesAdapter
): RecyclerView.Adapter<PickedLecturesAdapter.MyViewHolder>() {
    var count:Int = dataSet.size
    var pickedFragment = pickedFragment
    var appliedLecturesAdapter = appliedLecturesAdapter
    class MyViewHolder(val binding: PickedLecturesViewBinding) : RecyclerView.ViewHolder(binding.root)
    override fun getItemCount() = dataSet.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(PickedLecturesViewBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    @SuppressLint("SetTextI18n", "NotifyDataSetChanged")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val binding = (holder as MyViewHolder).binding
        val lecture = dataSet[position]
        binding.lecId.text = lecture.id.toString()
        binding.lecName.text = lecture.name
        val competRate: Double = lecture.pickedStuNum.toDouble() / lecture.limitStuNum.toDouble()
        binding.lecContentsFirstLine.text =
            "${lecture.stuGradeArray[lecture.stuGrade]} ${lecture.majorTypeArray[lecture.uniType][lecture.majorType]} ${lecture.credits}학점 ${lecture.hours}시간 ${lecture.profName}"
        binding.lecContentsSecondLine.text =
            "신청 : ${lecture.appliedStuNum}  제한 : ${lecture.limitStuNum}  담기 : ${lecture.pickedStuNum}  경쟁률 : ${
                round(competRate * 100) / 100
            }"
        binding.lecContentsThirdLine.text = lecture.timeInfo

        binding.removePickedLecture.setOnClickListener {
            dataSet.removeAt(position)
            this.notifyDataSetChanged()
            pickedFragment.refreshPickedLecturesData()
            pickedFragment.toastCanceledLectures(lecture.name)
        }

        binding.applyPickedLecture.setOnClickListener {
            currentUser.appliedList.add(dataSet[position])
            appliedLecturesAdapter.notifyDataSetChanged()
            pickedFragment.refreshAppliedLecturesData()
            pickedFragment.toastAppliedLectures(lecture.name)
        }
    }
}