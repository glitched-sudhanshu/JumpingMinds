package com.example.tmm.data.data_source.dto

data class Price(
    val price: Float,
    val type: String
){
    fun getPrice() : Int{
        return price.toInt()
    }
}