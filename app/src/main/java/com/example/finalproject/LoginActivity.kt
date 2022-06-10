package com.example.finalproject

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.finalproject.data.Lecture
import com.example.finalproject.data.Post
import com.example.finalproject.data.User
import com.example.finalproject.databinding.ActivityLoginBinding
import java.sql.Timestamp

class LoginActivity : AppCompatActivity() {

    val TAG = "LoginActivity"

// 임의 디비 생성
    companion object {
        val lectureDataArray:Array<Array<Array<Lecture>>> = arrayOf(
            arrayOf(
                arrayOf(),
                arrayOf(
                    Lecture("채플",5018,0,0,0,0,1,"",50,110,60,"화 11:00-11:50(S1101)"),
                    Lecture("채플",5019,0,0,0,0,1,"",100,110,300,"화 12:00-12:50(S1101)"),
                    Lecture("채플",5020,0,0,0,0,1,"",50,110,100,"수 11:00-11:50(S1101)")
                    ),
                arrayOf(),
                arrayOf(),
                arrayOf()
            ), //교양필수
            arrayOf(
                arrayOf()
            ), //선택교양
            arrayOf(arrayOf(),arrayOf(),arrayOf(),arrayOf(),arrayOf(),arrayOf(),arrayOf(),arrayOf()), //기초교양
            arrayOf(arrayOf(),arrayOf(),arrayOf()), //균형교양/교직/자유선택
            arrayOf(arrayOf(),arrayOf(),arrayOf()), //인문대학
            arrayOf(arrayOf(),arrayOf()), //사회과학대학
            arrayOf(arrayOf()), //법과대학
            arrayOf(arrayOf(),arrayOf()) //경영대학
        )

        val liveCommunityPostDataArray:ArrayList<Post> = arrayListOf<Post>(
            Post(0,"너무 떨린다","수강신청 다들 파이팅", Timestamp(System.currentTimeMillis()),true,0),
            Post(1,"모컴 경쟁률 빡셀까?","모컴 곡 잡아야 하는데...", Timestamp(System.currentTimeMillis()),true,0),
            Post(2,"곧이다 진짜 ;","ㅈㄱㄴ", Timestamp(System.currentTimeMillis()),true,1),
            Post(3,"꿀팁 ㄹㅇ ㅋㅋ","수강신청 전엔 명상이지!", Timestamp(System.currentTimeMillis()),true,6),
            Post(4,"배고프당","점심 뭐 먹지", Timestamp(System.currentTimeMillis()),true,5),
            Post(5,"종강 언제 해","개강부터 해야 할 텐데", Timestamp(System.currentTimeMillis()),true,16),
        )

        var userArrayList: ArrayList<User> = arrayListOf<User>(
            User("60181645","1234",60181645,"안선영",0,0,"융합소프트웨어학부",
                4,3,3,3,arrayListOf(
                    Lecture("고전으로읽는인문학",5224,1,0,0,3,3,"주민재",28,28,35,"월 15:00-16:15(S4519)"),
                    Lecture("인간심리의이해",5269,0,4,0,3,3,"진성조",28,28,134,"화 10:30-11:45(S4112)",)
                ), arrayListOf(
                )),
            User("60181620","1234",60181620,"김민지",0,0,"융합소프트웨어학부",
                4,3,3,3,arrayListOf(), arrayListOf()),
        )
        var currentUser: User = User("","",0,"",0,0,"",
            0,0,0,0,arrayListOf(), arrayListOf())
    }

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val mActionBar = supportActionBar
//        mActionBar!!.hide()

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        테스트용 바로 메인으로 전환
        currentUser = userArrayList[0]
        val intent:Intent = Intent(this,MainActivity::class.java).apply {
            putExtra("stuNum",userArrayList[0].stuNum)}
        startActivity(intent)

        // 로그인 버튼
        binding.loginBtn.setOnClickListener {

            //editText로부터 입력된 값을 받아온다
            var id = binding.loginId.text.toString()
            var pwd = binding.loginPassword.text.toString()

            // 쉐어드로부터 저장된 id, pw 가져오기

            val loginResult = checkLogin(userArrayList,id,pwd)
            if (loginResult[0] as Boolean){
                val user:User = loginResult[1] as User
                currentUser = user
                // 메인 화면으로 전환
                val intent:Intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
            }else{
                // 로그인 실패 다이얼로그 보여주기
                dialog("fail")
            }
        }
    }

    fun checkLogin(userList:ArrayList<User>,id:String,pwd:String): Array<Any> {
        for (i in userList.indices) {
            // 유저가 입력한 id, pw값과 쉐어드로 불러온 id, pw값 비교
            if(id == userList[i].id && pwd == userList[i].pwd){
                return arrayOf(true,userList[i])
            }
        }
        return arrayOf(false,"")
    }

    fun dialog(type: String){
        var dialog = AlertDialog.Builder(this)

        if(type.equals("success")){
            dialog.setTitle("로그인 성공")
            dialog.setMessage("로그인 성공!")
        }
        else if(type.equals("fail")){
            dialog.setTitle("로그인 실패")
            dialog.setMessage("아이디와 비밀번호를 확인해주세요")
        }

        var dialog_listener = object: DialogInterface.OnClickListener{
            override fun onClick(dialog: DialogInterface?, which: Int) {
                when(which){
                    DialogInterface.BUTTON_POSITIVE ->
                        Log.d(TAG, "")
                }
            }
        }

        dialog.setPositiveButton("확인",dialog_listener)
        dialog.show()
    }
}