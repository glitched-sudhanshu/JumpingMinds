package com.example.tmm.data.data_source.dto

data class DataXXX(
    val count: Int,
    val limit: Int,
    val offset: Int,
    val results: List<ResultXXX>,
    val total: Int
)