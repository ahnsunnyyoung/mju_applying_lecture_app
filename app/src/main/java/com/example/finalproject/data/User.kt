package com.example.finalproject.data

data class User(val id:String,
           val pwd:String,
           val stuNum:Int,
           val name:String,
           val campusType: Int = 0,
           val timeType: Int = 0,
           val major:String,
           val grade:Int,
           val englishLvl:Int,
           val englishConLvl:Int,
           val mathLvl:Int,
           val pickedList:ArrayList<Lecture>,
           val appliedList:ArrayList<Lecture>) {
    val campusTypeArray: Array<String> = arrayOf("인문캠퍼스","자연캠퍼스")
    val timeTypeArray: Array<String> = arrayOf("주간","야간")

    val englishLvlArray: Array<String> = arrayOf("R1","R2","R3","이수")
    val englishConLvlArray: Array<String> = arrayOf("L1","L2","L3","이수")
    val mathLvlArray: Array<String> = arrayOf("A1","A2","A3","이수")
}