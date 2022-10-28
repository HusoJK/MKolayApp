package com.example.mkolay.Activities

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.cardview.widget.CardView
import com.example.mkolay.R
import com.google.firebase.database.*
import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.google.zxing.qrcode.QRCodeWriter

class QRActivity : AppCompatActivity() {
    lateinit var shoppingHistory: CardView
    lateinit var addNewCard: CardView
    lateinit var img: ImageView
    private lateinit var qrStatusInView: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qr)
        initialize()
        bindQRStatus()
        shoppingHistory.setOnClickListener {
            val intent = Intent(this, ShoppingHistoryActivity::class.java)
            startActivity(intent)

        }

        addNewCard.setOnClickListener {
            val intent = Intent(this, AddNewCardActivity::class.java)
            startActivity(intent)
        }

    }

    fun genQR() {
        val data = System.currentTimeMillis().toString()

        if (data.isEmpty()) {
            Toast.makeText(this, "QR KOD OLUŞTURULAMADI", Toast.LENGTH_SHORT).show()

        } else {
            val writer = QRCodeWriter()
            try {
                val bitMatrix = writer.encode(data, BarcodeFormat.QR_CODE, 512, 512)
                val width = bitMatrix.width
                val height = bitMatrix.height
                val bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)

                for (x in 0 until width) {
                    for (y in 0 until height) {
                        bmp.setPixel(x, y, if (bitMatrix[x, y]) Color.BLACK else Color.WHITE)
                    }
                }
                img.setImageBitmap(bmp)
            } catch (e: WriterException) {
                e.printStackTrace()
            }
        }
    }

    fun bindQRStatus() {
        qrStatusInView.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(data: DataSnapshot) {
                if (data.value == "1") {
                    startNewActiviy()
                } else if (data.value == "101") {
                    genQR()
                } else if (data.value == "1001") {
                    throw RuntimeException("Test Crash")
                }

            }

            override fun onCancelled(error: DatabaseError) {
                throw RuntimeException("Test Crash")
            }

        })
    }

    fun startNewActiviy() {
        val intent = Intent(this, EnjoyShoppingActivity::class.java)
        startActivity(intent)
    }

    fun initialize() {
        shoppingHistory = findViewById(R.id.card_shopping_history)
        addNewCard = findViewById(R.id.card_add_new_card)
        img = findViewById(R.id.img_qr_code)

        genQR()

        val db = FirebaseDatabase.getInstance()
        qrStatusInView = db.getReference("qrStatus")
    }

}