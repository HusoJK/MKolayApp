package com.example.mkolay.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mkolay.R
import com.google.firebase.database.*

class EnjoyShoppingActivity : AppCompatActivity() {
    private lateinit var enjoyDBRef: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enjoy_shopping)
        enjoyDBRef = FirebaseDatabase.getInstance().getReference("status")
        BindStatus()
    }

    fun BindStatus() {
        enjoyDBRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(data: DataSnapshot) {
                if (data.value == "2") {
                    startNewActiviy()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                System.out.println("Data Çekme Hatası ERR52")
            }

        })
    }

    fun startNewActiviy() {
        val intent = Intent(this, ShoppingCardActivity::class.java)
        startActivity(intent)
    }
}