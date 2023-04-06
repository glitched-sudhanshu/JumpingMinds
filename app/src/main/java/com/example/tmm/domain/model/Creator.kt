package com.example.tmm.domain.model

import com.example.tmm.data.data_source.dto.ComicsX
import com.example.tmm.data.data_source.dto.Series

data class Creator(
    val id: Int,
    val firstName : String,
    val middleName : String,
    val lastName : String,
    val thumbnail : String,
    val thumbnailExt : String,
    val comics : List<String>,
    val series: List<String>
    )