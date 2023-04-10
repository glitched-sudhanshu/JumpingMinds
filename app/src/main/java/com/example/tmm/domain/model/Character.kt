package com.example.tmm.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.tmm.utils.Constants.CHARACTER_TABLE_NAME
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = CHARACTER_TABLE_NAME)
data class Character(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    val description: String,
    val thumbnail: String,
    val thumbnailExt: String,
    val noOfComics: Int,
    var isLiked : Boolean = false
) : Parcelable