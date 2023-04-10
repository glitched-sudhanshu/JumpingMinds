package com.example.tmm.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MarvelSeries (
    val id : Int,
    val title : String,
    val description : String,
    val noOfCharacters : Int,
    val noOfComics : Int,
    val thumbnail : String,
    val thumbnailExt: String
        ) : Parcelable