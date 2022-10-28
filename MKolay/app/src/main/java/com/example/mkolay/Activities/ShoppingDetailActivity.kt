package com.example.mkolay.Activities

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mkolay.R
import com.example.mkolay.Models.ShoppingCardModel
import com.example.mkolay.Adapters.ShoppingCardAdapter
import com.example.mkolay.Adapters.ShoppingDetailAdapter
import com.example.mkolay.Models.ShoppingDetailModel
import com.google.firebase.database.*

class ShoppingDetailActivity : AppCompatActivity() {
    private lateinit var cardList: ArrayList<ShoppingDetailModel>
    private lateinit var adapter: ShoppingDetailAdapter
    private lateinit var refCard: DatabaseReference
    lateinit var marketNameText: TextView
    lateinit var rv: RecyclerView
    lateinit var toplam: TextView
    lateinit var pdfButton: ImageView
    lateinit var dateText: TextView
    lateinit var basketTotal: TextView
    lateinit var amount: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping_detail)

        initialize()
        allShoppingCards()

    }


    fun allShoppingCards() {
        refCard.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                cardList.clear()
                var total = 0.0
                var acc = ""
                var amountPay = 5.40
                var basketTotalMoney = 0.0
                var basketTotalText = ""


                for (c in snapshot.children) {
                    val shoppingCardModel = c.getValue(ShoppingDetailModel::class.java)
                    var marketName123 = c.child("market_name").getValue()?.toString()
                    var date123 = c.child("date").getValue()?.toString()
                    var totalPriceSum = c.child("price").getValue()?.toString()!!.toDouble()
                    total = total + totalPriceSum
                    acc = total.toString().subSequence(0, 5) as String
                    basketTotalMoney = total - amountPay
                    basketTotalText = basketTotalMoney.toString().subSequence(0, 5) as String

                    if (shoppingCardModel != null) {
                        shoppingCardModel.productName =
                            c.child("product_name").getValue()?.toString()
                        shoppingCardModel.productPiece = c.child("piece").getValue()?.toString()
                        shoppingCardModel.unitPrice = c.child("price").getValue()?.toString()
                        shoppingCardModel.productPicture =
                            c.child("product_picture").getValue()?.toString()
                        shoppingCardModel.productWeight = c.child("weight").getValue()?.toString()
                        shoppingCardModel.marketName = c.child("market_name").getValue()?.toString()
                        cardList.add(shoppingCardModel)
                        marketNameText.text = marketName123
                    }


                }
                toplam.text = acc + " TL"
                basketTotal.text = basketTotalText + " TL"
                amount.text = amountPay.toString() + " TL"
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                System.out.println("Data Çekme Hatası ERR52")
            }

        })
    }

    fun initialize() {
        toplam = findViewById(R.id.total_text)
        rv = findViewById(R.id.rvvv)
        marketNameText = findViewById(R.id.market_name_text)
        pdfButton = findViewById(R.id.img_pdf)
        dateText = findViewById(R.id.date_text)
        basketTotal = findViewById(R.id.basket_total_text)
        amount = findViewById(R.id.amount_money_text)
        dateText.text = "21 Ekim 2021"

        pdfButton.setOnClickListener {
            val browserIntent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://migroskurumsalstr.blob.core.windows.net/migroskurumsalstr/migros-sunum-2c-2022-637961447675611792.pdf")
            )
            startActivity(browserIntent)

        }
        rv.setHasFixedSize(true)
        rv.layoutManager = LinearLayoutManager(this)

        val db = FirebaseDatabase.getInstance()
        refCard = db.getReference("shopping_detail")

        cardList = ArrayList()
        adapter = ShoppingDetailAdapter(this, cardList)
        rv.adapter = adapter
    }
}