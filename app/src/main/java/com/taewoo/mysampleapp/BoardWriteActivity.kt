package com.taewoo.mysampleapp

import android.os.Bundle
import android.widget.Button
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
            // Write a message to the database
            val database = Firebase.database
            val myRef = database.getReference("message")
            myRef.setValue("Hello, World!")

            // git 감시확인
        }
    }
}