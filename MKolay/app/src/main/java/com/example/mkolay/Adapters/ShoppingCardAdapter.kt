package com.example.mkolay.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.mkolay.R
import com.example.mkolay.Models.ShoppingCardModel
import com.squareup.picasso.Picasso

class ShoppingCardAdapter(
    private val context: Context,
    private val shoppingCardModelList: List<ShoppingCardModel>
) : RecyclerView.Adapter<ShoppingCardAdapter.ViewHolder>() {

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
            LayoutInflater.from(context).inflate(R.layout.shopping_card_row, parent, false)
        return ViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val card = shoppingCardModelList[position]


        holder.productName.text = card.productName
        holder.productWeight.text = card.productWeight
        holder.unitPrice.text = card.unitPrice + " TL"
        holder.productPiece.text = card.productPiece + " Adet"


        val url = "https://firebasestorage.googleapis.com/v0/b/huso-deneme.appspot.com/o/images%2F${card.productPicture}"
        Picasso.get().load(url).into(holder.picture)

    }

    override fun getItemCount(): Int {
        return shoppingCardModelList.size
    }

}