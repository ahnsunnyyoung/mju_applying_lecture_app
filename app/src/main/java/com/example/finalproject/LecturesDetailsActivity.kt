package com.example.finalproject

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.LoginActivity.Companion.currentUser
import com.example.finalproject.LoginActivity.Companion.lectureDataArray
import com.example.finalproject.databinding.ActivityLecturesDetailBinding
import com.example.finalproject.ui.picked.PickedFragment


class LecturesDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLecturesDetailBinding

    var available_apply_credits = 17
    var applied_total_lec_num = 0
    var applied_total_lec_credits = 0
    var textCartItemCount: TextView? = null
    var mCartItemCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLecturesDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var uniType = intent.getIntExtra("UniType",0)
        var majorType = intent.getIntExtra("MajorType",0)

        var lectureArray = lectureDataArray[uniType][majorType]


        //         수강신청 카드뷰 내용 초기화
        refreshAppliedLecturesData()

        var appliedExpandableView = binding.expandableAppliedLecture

        if (LoginActivity.currentUser.appliedList.size==0){
            appliedExpandableView.secondLayout.findViewById<TextView>(R.id.no_lec_text).visibility = View.VISIBLE
        }

        val adapter = LecturesDetailsAppliedLecturesAdapter(LoginActivity.currentUser.appliedList,this)
        appliedExpandableView.secondLayout.findViewById<RecyclerView>(R.id.applied_lectures_recyclerview).adapter = adapter

        appliedExpandableView.parentLayout.setOnClickListener {
            if (appliedExpandableView.isExpanded){
                appliedExpandableView.collapse()
            } else{
                appliedExpandableView.expand()
            }
        }

        if (lectureArray.isEmpty()){
            binding.noLecText.visibility = View.VISIBLE
        }else{
            val lecturesUniTypeAdapter = LecturesDetailsAdapter(lectureArray,adapter,this)
            binding.lecturesDetailsRecyclerview.layoutManager = LinearLayoutManager(this)
            binding.lecturesDetailsRecyclerview.adapter = lecturesUniTypeAdapter
        }

    }

    fun refreshAppliedLecturesData(){
        if (LoginActivity.currentUser.appliedList.size==0){
            binding.expandableAppliedLecture.secondLayout.findViewById<TextView>(R.id.no_lec_text).visibility = View.VISIBLE
        }
        binding.expandableAppliedLecture.findViewById<TextView>(R.id.available_apply_credits).text = "${available_apply_credits}학점"
        applied_total_lec_num = 0
        applied_total_lec_credits = 0
        for (lec in LoginActivity.currentUser.appliedList) {
            applied_total_lec_num ++
            applied_total_lec_credits += lec.credits
        }

        binding.expandableAppliedLecture.findViewById<TextView>(R.id.applied_total_lec_num).text = "${applied_total_lec_num}과목"
        binding.expandableAppliedLecture.findViewById<TextView>(R.id.applied_total_lec_credits).text = "${applied_total_lec_credits}학점"

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.lectures_details_menu, menu)
        val menuItem: MenuItem = menu.findItem(R.id.action_cart)
        val actionView: View = menuItem.actionView
        textCartItemCount = actionView.findViewById<View>(R.id.cart_badge) as TextView
        setupBadge()
        actionView.setOnClickListener { onOptionsItemSelected(menuItem) }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_cart -> {
                MainActivity.currentFragmentId = R.id.navigation_picked
                val intent: Intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
    fun setupBadge() {
        mCartItemCount = currentUser.pickedList.size
        if (textCartItemCount != null) {
            if (mCartItemCount === 0) {
                if (textCartItemCount!!.visibility !== View.GONE) {
                    textCartItemCount!!.visibility = View.GONE
                }
            } else {
                textCartItemCount!!.text = mCartItemCount.toString()
                if (textCartItemCount!!.visibility !== View.VISIBLE) {
                    textCartItemCount!!.visibility = View.VISIBLE
                }
            }
        }
    }
}