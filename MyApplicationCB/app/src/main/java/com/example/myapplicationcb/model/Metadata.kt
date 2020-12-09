package com.example.myapplicationcb.model


import com.google.gson.annotations.SerializedName

data class Metadata(
    @SerializedName("age")
    val age: String,
    @SerializedName("color")
    val color: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("msrp")
    val msrp: String,
    @SerializedName("size")
    val size: String,
    @SerializedName("unit")
    val unit: String
)