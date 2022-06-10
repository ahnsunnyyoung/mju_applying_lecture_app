package com.example.finalproject.ui.lectures

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.finalproject.LoginActivity.Companion.currentUser
import com.example.finalproject.MainActivity
import com.example.finalproject.MainActivity.Companion.currentFragmentId
import com.example.finalproject.R
import com.example.finalproject.data.Lecture
import com.example.finalproject.databinding.FragmentLecturesBinding
import com.example.finalproject.ui.picked.PickedFragment
import com.github.angads25.toggle.interfaces.OnToggledListener


class LecturesFragment : Fragment() {

    private var _binding: FragmentLecturesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentLecturesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        if(currentUser.pickedList.size===0) {
            binding.pickedLecturesNumNotify.visibility = View.GONE
        }else{
            binding.cartBtn.visibility = View.VISIBLE
            binding.pickedLecturesNumNotify.visibility = View.VISIBLE
            binding.pickedLecturesNumNotify.text =  currentUser.pickedList.size.toString()
        }

        var campusType = 0

        var lecturesUniTypeAdapter = LecturesUniTypeAdapter(Lecture().uniTypeArray[campusType],context,campusType)
        binding.lecturesUniTypeRecyclerview.layoutManager = LinearLayoutManager(context)
        binding.lecturesUniTypeRecyclerview.adapter = lecturesUniTypeAdapter

        binding.campusLabel.setOnToggledListener(OnToggledListener { labeledSwitch, isOn ->
            if (isOn){
                if (binding.campusTimeLabel.isOn){
                    campusType = 0
                }else{
                    campusType = 1
                }
            }else{
                if (binding.campusTimeLabel.isOn){
                    campusType = 2
                }else{
                    campusType = 3
                }
            }
            lecturesUniTypeAdapter = LecturesUniTypeAdapter(Lecture().uniTypeArray[campusType],context,campusType)
            binding.lecturesUniTypeRecyclerview.layoutManager = LinearLayoutManager(context)
            binding.lecturesUniTypeRecyclerview.adapter = lecturesUniTypeAdapter
        })

        binding.campusTimeLabel.setOnToggledListener(OnToggledListener { labeledSwitch, isOn ->
            if (isOn){
                if (binding.campusLabel.isOn){
                    campusType = 0
                }else{
                    campusType = 2
                }
            }else{
                if (binding.campusLabel.isOn){
                    campusType = 1
                }else{
                    campusType = 3
                }
            }
            lecturesUniTypeAdapter = LecturesUniTypeAdapter(Lecture().uniTypeArray[campusType],context,campusType)
            binding.lecturesUniTypeRecyclerview.layoutManager = LinearLayoutManager(context)
            binding.lecturesUniTypeRecyclerview.adapter = lecturesUniTypeAdapter
        })


        val activity = (activity as MainActivity)

        binding.cartBtn.setOnClickListener {
            currentFragmentId = R.id.navigation_picked
            activity.changeFragment(PickedFragment())
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}