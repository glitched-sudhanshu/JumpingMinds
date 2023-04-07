package com.example.tmm.data.data_source.dto

data class ComicsX(
    val available: Int,
    val collectionURI: String,
    val items: List<ItemX>,
    val returned: Int
)