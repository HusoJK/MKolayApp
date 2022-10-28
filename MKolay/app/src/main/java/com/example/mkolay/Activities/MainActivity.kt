package com.example.mkolay.Activities

import android.app.ActionBar
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.example.mkolay.R


class MainActivity : AppCompatActivity() {
    private lateinit var cardBazaar: CardView
    private lateinit var cardCanteen: CardView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initialize()
        cardBazaar.setOnClickListener {
            val intent = Intent(this, QRActivity::class.java)
            startActivity(intent)
        }
        cardCanteen.setOnClickListener {
            val intent = Intent(this, QRActivity::class.java)
            startActivity(intent)

        }
    }

    fun initialize() {
        cardBazaar = findViewById(R.id.card_bazaar)
        cardCanteen = findViewById(R.id.card_canteen)
    }
}