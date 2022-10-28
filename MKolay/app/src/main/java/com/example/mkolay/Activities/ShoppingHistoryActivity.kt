package com.example.mkolay.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mkolay.Models.ShoppingHistoryModel
import com.example.mkolay.Adapters.OldShoppingAdapter
import com.example.mkolay.R
import com.google.firebase.database.*

class ShoppingHistoryActivity : AppCompatActivity() {
    private lateinit var shopList: ArrayList<ShoppingHistoryModel>
    private lateinit var adapter: OldShoppingAdapter
    private lateinit var refOldShopping: DatabaseReference
    lateinit var rv: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping_history)
        initialize()
        allOldShopping()
    }

    fun allOldShopping() {
        refOldShopping.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                shopList.clear()

                for (c in snapshot.children) {
                    val shoppingHistoryModel = c.getValue(ShoppingHistoryModel::class.java)


                    if (shoppingHistoryModel != null) {
                        shoppingHistoryModel.categoryID = c.key
                        shoppingHistoryModel.marketName = c.child("market_name").getValue()?.toString()
                        shoppingHistoryModel.shoppingDdate = c.child("date").getValue()?.toString()
                        shoppingHistoryModel.shoppingTotalPrice = c.child("total_price").getValue()?.toString()
                        shopList.add(shoppingHistoryModel)

                    }
                }
                adapter.notifyDataSetChanged()

            }

            override fun onCancelled(error: DatabaseError) {
                System.out.println("Data Çekme Hatası ERR52")
            }

        })
    }

    fun initialize() {
        rv = findViewById(R.id.rv_shopping_detail)
        rv.setHasFixedSize(true)
        rv.layoutManager = LinearLayoutManager(this)

        val db = FirebaseDatabase.getInstance()
        refOldShopping = db.getReference("shopping_history")

        shopList = ArrayList()
        adapter = OldShoppingAdapter(this, shopList)
        rv.adapter = adapter
    }
}