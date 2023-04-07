package com.example.tmm.data.data_source.dto

data class Characters(
    val available: Int,
    val collectionURI: String,
    val items: List<Any>,
    val returned: Int
)