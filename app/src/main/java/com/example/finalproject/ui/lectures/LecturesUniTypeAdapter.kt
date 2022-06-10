package com.example.finalproject.ui.lectures

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.R
import com.example.finalproject.data.Lecture
import com.example.finalproject.databinding.LecturesUniTypeViewBinding

class LecturesUniTypeAdapter(
    private val dataSet: Array<String>,
    val mContext: Context?,
    val campusType: Int
): RecyclerView.Adapter<LecturesUniTypeAdapter.MyViewHolder>() {
    var count:Int = 0

    class MyViewHolder(val binding: LecturesUniTypeViewBinding) : RecyclerView.ViewHolder(binding.root)
    override fun getItemCount() = dataSet.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        count++
        return MyViewHolder(LecturesUniTypeViewBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val binding = (holder as MyViewHolder).binding
        binding.expandableUniType.parentLayout.findViewById<TextView>(R.id.lectures_category_text).text = dataSet[position]
        val secondBinding = binding.expandableUniType.secondLayout
        val adapter = LecturesMajorTypeAdapter(Lecture().majorTypeArray[campusType][position],mContext,position)
        secondBinding.findViewById<RecyclerView>(R.id.lectures_major_type_recyclerview).adapter = adapter

        binding.expandableUniType.parentLayout.setOnClickListener {
            if (binding.expandableUniType.isExpanded){
                binding.expandableUniType.collapse()
            } else{
                binding.expandableUniType.expand()
                }
        }

    }
}