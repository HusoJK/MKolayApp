package com.example.mkolay.Adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.mkolay.R
import com.example.mkolay.Models.ShoppingDetailModel
import com.squareup.picasso.Picasso

class ShoppingDetailAdapter(
    private val context: Context,
    private val shoppingCardList: List<ShoppingDetailModel>
) : RecyclerView.Adapter<ShoppingDetailAdapter.ViewHolder>() {

    inner class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        var shoppingCard: CardView
        var productName: TextView
        var unitPrice: TextView
        var productPiece: TextView
        var productWeight: TextView
        var picture: ImageView

        init {
            shoppingCard = view.findViewById(R.id.shopping_card)
            productName = view.findViewById(R.id.product_name)
            unitPrice = view.findViewById(R.id.unit_price)
            productPiece = view.findViewById(R.id.piece_text)
            productWeight = view.findViewById(R.id.product_unit)
            picture = view.findViewById(R.id.product_image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater =
            LayoutInflater.from(context).inflate(R.layout.shopping_history_row, parent, false)
        return ViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val card = shoppingCardList[position]

        holder.productName.text = card.productName
        holder.productWeight.text = card.productWeight
        holder.unitPrice.text = card.unitPrice + " TL"
        holder.productPiece.text = card.productPiece + " Adet"


        val url = "https://firebasestorage.googleapis.com/v0/b/huso-deneme.appspot.com/o/images%2F${card.productPicture}"
        Picasso.get().load(url).into(holder.picture)

        // holder.detail.setImageResource(context.resources.getIdentifier(shopping.nextIcon,"drawble",context.packageName))

        holder.shoppingCard.setOnClickListener {
            Toast.makeText(context,"HELLO", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return shoppingCardList.size
    }

}