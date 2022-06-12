package com.example.finalproject

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.finalproject.databinding.ActivityCalendarBinding
import com.kizitonwose.calendarview.model.CalendarDay
import com.kizitonwose.calendarview.model.CalendarMonth
import com.kizitonwose.calendarview.model.DayOwner
import com.kizitonwose.calendarview.ui.DayBinder
import com.kizitonwose.calendarview.ui.MonthHeaderFooterBinder
import com.kizitonwose.calendarview.ui.ViewContainer
import com.kizitonwose.calendarview.utils.Size
import java.time.DayOfWeek
import java.time.YearMonth
import java.util.*


class CalendarActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCalendarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCalendarBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.calendarView.dayBinder = object : DayBinder<DayViewContainer> {
            // Called only when a new container is needed.
            override fun create(view: View) = DayViewContainer(view)

            // Called every time we need to reuse a container.
            override fun bind(container: DayViewContainer, day: CalendarDay) {
                container.textView.text = day.date.dayOfMonth.toString()
                if (day.owner == DayOwner.THIS_MONTH) {

                    if (day.date.dayOfMonth.toString() == "1" || day.date.dayOfMonth.toString() == "2" ||day.date.dayOfMonth.toString() == "3"||day.date.dayOfMonth.toString() == "4"){
                        container.dayView.setOnClickListener {
                            binding.calendarScheduleInfoView.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.teal_200))
                            binding.calendarScheduleInfo.text = "미리담기 기간입니다."
                            binding.calendarScheduleInfo.setTextColor(resources.getColor(R.color.white))
                        }
                        container.dayIcon.visibility = View.VISIBLE
                        container.dayIcon.imageTintList = ColorStateList.valueOf(resources.getColor(R.color.teal_200))
                    }

                    if (day.date.dayOfMonth.toString() == "7" || day.date.dayOfMonth.toString() == "8" ||day.date.dayOfMonth.toString() == "9"||day.date.dayOfMonth.toString() == "10"||day.date.dayOfMonth.toString() == "11"||day.date.dayOfMonth.toString() == "12"||day.date.dayOfMonth.toString() == "13"||day.date.dayOfMonth.toString() == "14"||day.date.dayOfMonth.toString() == "15"||day.date.dayOfMonth.toString() == "16"||day.date.dayOfMonth.toString() == "17"||day.date.dayOfMonth.toString() == "18"||day.date.dayOfMonth.toString() == "19"||day.date.dayOfMonth.toString() == "20"||day.date.dayOfMonth.toString() == "21"||day.date.dayOfMonth.toString() == "22"||day.date.dayOfMonth.toString() == "23"||day.date.dayOfMonth.toString() == "24"){
                        container.dayView.setOnClickListener {
                            binding.calendarScheduleInfoView.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.purple_200))
                            binding.calendarScheduleInfo.text = "수강신청 기간입니다."
                            binding.calendarScheduleInfo.setTextColor(resources.getColor(R.color.white))
                        }
                        container.dayIcon.visibility = View.VISIBLE
                        container.dayIcon.imageTintList = ColorStateList.valueOf(resources.getColor(R.color.purple_200))
                    }
                    container.textView.setTextColor(resources.getColor(R.color.black))
                } else {
                    if (day.date.dayOfMonth.toString() == "31"){
                        container.dayView.setOnClickListener {
                            binding.calendarScheduleInfoView.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.teal_200))
                            binding.calendarScheduleInfo.text = "미리담기 기간입니다."
                            binding.calendarScheduleInfo.setTextColor(resources.getColor(R.color.white))
                        }
                        container.dayIcon.visibility = View.VISIBLE
                        container.dayIcon.imageTintList = ColorStateList.valueOf(resources.getColor(R.color.teal_200))
                    }
                    if (day.date.dayOfMonth.toString() == "2"){
                        container.dayView.setOnClickListener {
                            binding.calendarScheduleInfoView.backgroundTintList = ColorStateList.valueOf(Color.RED)
                            binding.calendarScheduleInfo.text = "개강일입니다."
                            binding.calendarScheduleInfo.setTextColor(resources.getColor(R.color.white))
                        }
                        container.dayIcon.visibility = View.VISIBLE
                        container.dayIcon.imageTintList = ColorStateList.valueOf(Color.RED)
                    }

                    if (day.date.dayOfMonth.toString() == "3"||day.date.dayOfMonth.toString() == "4"||day.date.dayOfMonth.toString() == "5"){
                        container.dayView.setOnClickListener {
                            binding.calendarScheduleInfoView.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.teal_500))
                            binding.calendarScheduleInfo.text = "수강신청 변경 기간입니다."
                            binding.calendarScheduleInfo.setTextColor(resources.getColor(R.color.white))
                        }
                        container.dayIcon.visibility = View.VISIBLE
                        container.dayIcon.imageTintList = ColorStateList.valueOf(resources.getColor(R.color.teal_500))
                    }
                    container.textView.setTextColor(resources.getColor(R.color.gray_500))
                }

            }
        }

        binding.calendarView.daySize = Size(200,120)

        val daysOfWeek = arrayOf(
            DayOfWeek.SUNDAY,
            DayOfWeek.MONDAY,
            DayOfWeek.TUESDAY,
            DayOfWeek.WEDNESDAY,
            DayOfWeek.THURSDAY,
            DayOfWeek.FRIDAY,
            DayOfWeek.SATURDAY
        )
        val currentMonth = YearMonth.of(2022,2)
        binding.calendarView.setup(currentMonth, currentMonth, daysOfWeek.first())
        binding.calendarView.scrollToMonth(currentMonth)

        binding.calendarView.monthHeaderBinder = object :
            MonthHeaderFooterBinder<MonthViewContainer> {
            override fun create(view: View) = MonthViewContainer(view)
            override fun bind(container: MonthViewContainer, month: CalendarMonth) {
                container.textView.text = "${month.yearMonth.monthValue}월"
            }
        }

    }
}

class DayViewContainer(view: View) : ViewContainer(view) {
    val dayView = view.findViewById<LinearLayout>(R.id.dayView)
    val textView = view.findViewById<TextView>(R.id.calendarDayText)
    val dayIcon = view.findViewById<ImageView>(R.id.dayIcon)
}

class MonthViewContainer(view: View) : ViewContainer(view) {
    val textView = view.findViewById<TextView>(R.id.headerTextView)
}

