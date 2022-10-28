package com.example.mkolay.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mkolay.R
import com.example.mkolay.Models.ShoppingCardModel
import com.example.mkolay.Adapters.ShoppingCardAdapter
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.firebase.database.*

class ShoppingCardActivity : AppCompatActivity() {
    private lateinit var cardList: ArrayList<ShoppingCardModel>
    private lateinit var adapter: ShoppingCardAdapter
    private lateinit var refCard: DatabaseReference
    private lateinit var sheet: FrameLayout
    private lateinit var totalMoney: TextView
    lateinit var rv: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping_card)
        initialize()
        allShoppingCards()
    }

    fun allShoppingCards() {
        refCard.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                cardList.clear()
                var total: Double = 0.0

                for (c in snapshot.children) {
                    val shoppingCardModel = c.getValue(ShoppingCardModel::class.java)

                    var totalPriceSum: Double = c.child("price").getValue().toString().toDouble()
                    total = total + totalPriceSum
                    var allPrice:Double
                    if (shoppingCardModel != null ) {
                        shoppingCardModel.cardID = c.key
                        shoppingCardModel.productName = c.child("product_name").getValue()?.toString()
                        shoppingCardModel.productPiece = c.child("piece").getValue()?.toString()
                        shoppingCardModel.unitPrice = c.child("price").getValue()?.toString()
                        shoppingCardModel.productPicture = c.child("product_picture").getValue()?.toString()
                        shoppingCardModel.productWeight = c.child("weight").getValue()?.toString()
                        cardList.add(shoppingCardModel)
                    }


                }
                totalMoney.text = total.toString() + " TL"

                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                System.out.println("Data Çekme Hatası ERR52")
            }

        })
    }

    fun initialize(){
        rv = findViewById(R.id.rv_shopping_basket)
        totalMoney = findViewById(R.id.date_text)

        rv.setHasFixedSize(true)
        rv.layoutManager = LinearLayoutManager(this)

        val db = FirebaseDatabase.getInstance()
        refCard = db.getReference("shopping_card")

        cardList = ArrayList()

        adapter = ShoppingCardAdapter(this, cardList)

        rv.adapter = adapter

        sheet = findViewById(R.id.basket_frame)

        BottomSheetBehavior.from(sheet).apply {
            peekHeight = 150
            this.state = BottomSheetBehavior.STATE_COLLAPSED
        }
    }

}