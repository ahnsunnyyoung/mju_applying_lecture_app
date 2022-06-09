package com.example.finalproject.ui.lectures

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.LecturesDetailsActivity
import com.example.finalproject.databinding.LecturesMajorTypeViewBinding


class LecturesMajorTypeAdapter(
    private val dataSet: Array<String>,
    mContext: Context?,
    uniType: Int
): RecyclerView.Adapter<LecturesMajorTypeAdapter.MyViewHolder>() {
    var count:Int = dataSet.size
    val mContext = mContext
    val uniType = uniType
    class MyViewHolder(val binding: LecturesMajorTypeViewBinding) : RecyclerView.ViewHolder(binding.root)

    override fun getItemCount() = count

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LecturesMajorTypeViewBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val binding = (holder as MyViewHolder).binding

        binding.itemText.text = dataSet[position]

        binding.itemText.setOnClickListener {
            val intent: Intent = Intent(mContext, LecturesDetailsActivity::class.java)
            intent.putExtra("UniType",uniType)
            intent.putExtra("MajorType",position)
            mContext?.startActivity(intent)
        }

//        binding.expandable.parentLayout.setOnClickListener {
//            if (binding.expandable.isExpanded){
//                binding.expandable.collapse()
//            } else{
//                binding.expandable.expand()
//            }
//        }

    }
}