package com.example.mkolay.Adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.mkolay.Activities.ShoppingDetailActivity
import com.example.mkolay.Models.ShoppingHistoryModel
import com.example.mkolay.R

class OldShoppingAdapter(
    private val context: Context,
    private val oldShoppingModelList: List<ShoppingHistoryModel>
) : RecyclerView.Adapter<OldShoppingAdapter.ViewHolder>() {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var shoppingHistoryRow: CardView
        var marketName: TextView
        var shoppingDate: TextView
        var oldTotalPrice: TextView
        var detail: ImageView

        init {
            shoppingHistoryRow = view.findViewById(R.id.shopping_history_row)
            marketName = view.findViewById(R.id.branch_name)
            shoppingDate = view.findViewById(R.id.shopping_date)
            oldTotalPrice = view.findViewById(R.id.old_total)
            detail = view.findViewById(R.id.nextToDetail)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater =
            LayoutInflater.from(context).inflate(R.layout.shopping_history, parent, false)
        return ViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val shopping = oldShoppingModelList[position]

        holder.marketName.text = shopping.marketName
        holder.shoppingDate.text = shopping.shoppingDdate
        holder.oldTotalPrice.text = shopping.shoppingTotalPrice + " TL"



        holder.shoppingHistoryRow.setOnClickListener {
            val intent = Intent(context, ShoppingDetailActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return oldShoppingModelList.size
    }
}




