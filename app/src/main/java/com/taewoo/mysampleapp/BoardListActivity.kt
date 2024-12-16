package com.taewoo.mysampleapp

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database

class BoardListActivity : AppCompatActivity() {

    /** 어댑터 선언 **/
    /**
     * lateinit은 타입은 정해주고 LVAdapter의 값은 나중에 정해준다는 것
     *
     */
     lateinit var LVAdapter : ListViewAdapter


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

        /** LVAdapter 연결 **/
        val list = mutableListOf<Model>()
        list.add(Model("a"))
        list.add(Model("b"))
        list.add(Model("c"))
        LVAdapter = ListViewAdapter(list)
        val lv = findViewById<ListView>(R.id.lv)
        lv.adapter = LVAdapter

        getData()

    }

    fun getData() {

        val database = Firebase.database
        val myRef = database.getReference("board")

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get Post object and use the values to update the UI
                for (dataModel in dataSnapshot.children) {
//                    Log.d("boardListActivity", dataSnapshot.toString())
                    /** item **/
                    val item = dataModel.getValue(Model::class.java)
                    Log.d("BoardListActivity", item.toString())
                }
            }
            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w("BoardListActivity", "loadPost:onCancelled", databaseError.toException())
            }
        }
        myRef.addValueEventListener(postListener)
    }

}