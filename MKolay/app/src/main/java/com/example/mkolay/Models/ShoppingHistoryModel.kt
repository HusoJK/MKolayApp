package com.example.mkolay.Models

import com.google.firebase.database.IgnoreExtraProperties
import java.io.Serializable

@IgnoreExtraProperties
        data class ShoppingHistoryModel(
        var categoryID:String?="",
        var marketName:String?="",
        var shoppingDdate:String?="",
        var shoppingTotalPrice:String?="",
) {

}