package com.example.mkolay.Models

import android.widget.ImageView
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class ShoppingCardModel(
    var cardID: String? = "",
    var productPiece: String? = "",
    var unitPrice: String? = "",
    var productName: String? = "",
    var productPicture: String? = "",
    var productWeight: String? = "",
    var marketName: String? = ""
) {
}