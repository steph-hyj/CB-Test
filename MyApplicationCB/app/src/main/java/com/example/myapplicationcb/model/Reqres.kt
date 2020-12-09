package com.example.myapplicationcb.model


import com.google.gson.annotations.SerializedName

data class Reqres(
    @SerializedName("ASIN")
    val aSIN: String,
    @SerializedName("added_time")
    val addedTime: String,
    @SerializedName("alias")
    val alias: String,
    @SerializedName("barcode")
    val barcode: String,
    @SerializedName("brand")
    val brand: String,
    @SerializedName("category")
    val category: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("images")
    val images: Any,
    @SerializedName("manufacturer")
    val manufacturer: String,
    @SerializedName("metadata")
    val metadata: Metadata,
    @SerializedName("modified_time")
    val modifiedTime: String,
    @SerializedName("mpn")
    val mpn: String,
    @SerializedName("msrp")
    val msrp: String,
    @SerializedName("stores")
    val stores: Any,
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("timestamp")
    val timestamp: Int,
    @SerializedName("title")
    val title: String
)