package com.example.finalproject

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.data.Lecture
import com.example.finalproject.databinding.AppliedLecturesViewBinding
import com.example.finalproject.ui.picked.AppliedLecturesAdapter
import com.example.finalproject.ui.picked.PickedFragment
import kotlin.math.round


class LecturesDetailsAppliedLecturesAdapter(
    private val dataSet: ArrayList<Lecture>,
    private val activity: LecturesDetailsActivity
): RecyclerView.Adapter<LecturesDetailsAppliedLecturesAdapter.MyViewHolder>() {
    class MyViewHolder(val binding: AppliedLecturesViewBinding) : RecyclerView.ViewHolder(binding.root)
    override fun getItemCount() = dataSet.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(AppliedLecturesViewBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

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
        binding.lecContentsThirdLine.text = "${lecture.timeInfo}"


        binding.cancelPickedLecture.setOnClickListener {
            dataSet.removeAt(position)
            this.notifyDataSetChanged()
            activity.refreshAppliedLecturesData()
            true
        }

    }
}