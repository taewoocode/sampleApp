package com.taewoo.mysampleapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Firebase
import com.google.firebase.database.database

class BoardWriteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_board_wirte)

//        val writeBtn = findViewById<Button>(R.id.writeUploadBtn)
        val writeBtn : Button = findViewById(R.id.writeUploadBtn)
        writeBtn.setOnClickListener {

            val writeText = findViewById<EditText>(R.id.writeTextArea)

            // Write a message to the database
            val database = Firebase.database

            // Ref는 데이터베이스가 저장되는 경로
            val myRef = database.getReference("board")

            // key - value 형태로 저장
//            myRef.push().setValue(writeText.text.toString())

            // Model의 형태로 데이터 저장
            myRef.push().setValue(
                Model(writeText.text.toString())
            )

            // git 감시확인

        }
    }
}