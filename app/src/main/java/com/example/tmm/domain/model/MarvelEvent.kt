package com.example.tmm.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MarvelEvent (
    val id : Int,
    val title : String,
    val description : String,
    val thumbnail : String,
    val thumbnailExt: String
        ) : Parcelable