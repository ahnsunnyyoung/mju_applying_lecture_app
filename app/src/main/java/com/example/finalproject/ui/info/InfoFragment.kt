package com.example.finalproject.ui.info

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.finalproject.CalendarActivity
import com.example.finalproject.LoginActivity.Companion.currentUser
import com.example.finalproject.databinding.FragmentInfoBinding

class InfoFragment : Fragment() {
    val TAG = "InfoFragment"

    private var _binding: FragmentInfoBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInfoBinding.inflate(inflater, container, false)

        binding.infoStatement.text = "명지대학교는\n${currentUser.name} 학생의\n올클을 기원합니다!"

        binding.userInfo.stuName.text = "${currentUser.name}"
        binding.userInfo.stuCampus.text = "${currentUser.campusTypeArray[currentUser.campusType]}"
        binding.userInfo.stuTime.text = "${currentUser.timeTypeArray[currentUser.timeType]}"
        binding.userInfo.stuMajor.text = "${currentUser.major}"
        binding.userInfo.stuGrade.text = "${currentUser.grade}학년"
        binding.userInfo.stuEnglishLvl.text = "영어 ${currentUser.englishLvlArray[currentUser.englishLvl]}"
        binding.userInfo.stuEnglishConLvl.text = "영어회화 ${currentUser.englishConLvlArray[currentUser.englishConLvl]}"
        binding.userInfo.stuMathLvl.text = "미적분학 ${currentUser.mathLvlArray[currentUser.mathLvl]}"

        binding.infoScheduleBtn.setOnClickListener {
            val intent: Intent = Intent(activity, CalendarActivity::class.java)
            startActivity(intent)
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}