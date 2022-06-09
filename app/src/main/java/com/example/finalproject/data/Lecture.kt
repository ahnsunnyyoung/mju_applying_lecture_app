package com.example.finalproject.data

class Lecture(
    val name:String = "",
    val id:Int = 0,
    val uniType:Int = 0,
    val majorType:Int = 0,
    val stuGrade:Int = 0,
    val credits:Int = 0,
    val hours:Int = 0,
    val profName:String = "",
    val appliedStuNum:Int = 0,
    val limitStuNum:Int = 0,
    val pickedStuNum:Int = 0,
    val timeInfo:String = "화 12:00 - 13:00") {

    val stuGradeArray: Array<String> = arrayOf("전학년","1학년","2학년","3학년","4학년")
    val uniTypeArray: Array<String> = arrayOf("교양필수","선택교양","기초교양","균형교양/교직/자유선택","인문대학","사회과학대학","법과대학","경영대학")
    val majorTypeArray = arrayOf(
                            arrayOf("성경개론","채플","영어","영어회화","기타교양필수"), //교양필수
                            arrayOf("선택교양과목"), //선택교양
                            arrayOf("인문과학","문화와예술","사회과학","자연과학","공학","건강과생활","외국어","컴퓨터"), //기초교양
                            arrayOf("기독교의이해와삷","인문학과의만남","문화예술의초대"), //균형교양/교직/자유선택
                            arrayOf("국어국문학과","영어영문학과","중어중문학과"), //인문대학
                            arrayOf("행정학과","정치외교학과"), //사회과학대학
                            arrayOf("법학과"), //법과대학
                            arrayOf("경영학과","경영정보학과") //경영대학
                        )
}