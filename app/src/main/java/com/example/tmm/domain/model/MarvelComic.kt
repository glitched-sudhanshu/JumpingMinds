package com.example.tmm.domain.model

class MarvelComic (
    val id: Int,
    val title : String,
    val price : Int,
    val description : String = "",
    val thumbnail : String,
    val thumbnailExt : String
)