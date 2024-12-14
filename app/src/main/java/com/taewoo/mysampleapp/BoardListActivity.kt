package com.taewoo.mysampleapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class BoardListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_board_list)

        val writeBtn = findViewById<Button>(R.id.writeBtn)
        writeBtn.setOnClickListener {

            // this는 현재 BoardListActivity 인스턴스를 가르키고 있음
            // wirteBtn을 클릭하면 BoardWriteActivity로 넘어가게 설계가 되어있음
            val intent = Intent(this, BoardWriteActivity::class.java)
            startActivity(intent)

        }
    }
}