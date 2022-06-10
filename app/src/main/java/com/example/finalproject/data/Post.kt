package com.example.finalproject.data

import java.sql.Timestamp

class Post (val id:Int,
            val title:String,
            val content:String,
            val timestamp: Timestamp,
            val isUnkown:Boolean,
            val likeNum:Int) {
}