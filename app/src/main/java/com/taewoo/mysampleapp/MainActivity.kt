package com.taewoo.mysampleapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.taewoo.mysampleapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {



    // 바인딩을 위해 변수 선언
    private lateinit var binding : ActivityMainBinding

    //객체를 나중에 초기화 하는 변수
    /**
     * nullable 타입을 사용하지 않고도 초기화를 지연할 수 있다.
     */
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        auth = Firebase.auth
        super.onCreate(savedInstanceState)

        // R 클래스는 XML로 정의된 리소스를 코드에서 참조할 수 있도록 정적 필드로 관리한다.
        // 레이아웃 문자열, 이미지, 스타일 등 다양한 리소스를 코드에서 접근할 때 사용한다.
        // res/layout/activity_main.xml 파일을 참조한다.
        setContentView(R.layout.activity_main)

        // Toast는 메시지를 띄워주는 객체
        //현재 UI 정보를 받아온다.
        Toast.makeText(this, auth.currentUser?.uid.toString(), Toast.LENGTH_SHORT).show()

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // 값들을 받는 법 첫번 째 방법
//        val email = findViewById<EditText>(R.id.emailArea)
//        val pwd = findViewById<EditText>(R.id.pwdArea)

        // 두번 째 방법 데이터 바인딩
        val email = binding.emailArea
        val pwd = binding.pwdArea

        findViewById<Button>(R.id.joinBtn).setOnClickListener {
            auth.createUserWithEmailAndPassword(email.text.toString(), pwd.text.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this,"ok", Toast.LENGTH_SHORT ).show()
                    } else {
                        Toast.makeText(this,"ok",Toast.LENGTH_SHORT).show()
                    }
                }
        }

        // logoutBtn을 클릭하면 어떻게 할건가?
        binding.logoutBtn.setOnClickListener {

            // 로그아웃
            auth.signOut()

            // 현재 UID 정보를 받아온다.
            Toast.makeText(this, auth.currentUser?.uid.toString(), Toast.LENGTH_SHORT).show()
        }

        // loginBtn을 클릭하면 어떻게 할건가?
        binding.loginBtn.setOnClickListener {

            val email = binding.emailArea
            val pwd = binding.pwdArea

            auth.createUserWithEmailAndPassword(
                email.text.toString(),
                pwd.text.toString()
            )
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val user = auth.currentUser

                        if (user != null) {
                            Log.d("LoginSuccess", "User logged in: $user.uid")

                            // 로그인 성공 후 화면 전환
                            val intent = Intent(this, BoardListActivity::class.java)
                            startActivity(intent)
                        } else {
                            Log.e("LoginError", "User is null after successful login.")
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(this,"ok", Toast.LENGTH_SHORT).show()
                            Toast.makeText(this, auth.currentUser?.uid.toString(), Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        // 로그인 실패시 에러 로그 찍기
                        Log.e("LoginError", "Login failed: ${task.exception?.message}")
                        Toast.makeText(this,"false", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }
}