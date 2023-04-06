package com.example.tmm.utils

import java.math.BigInteger
import java.security.MessageDigest
import java.sql.Timestamp

object Constants {
    const val BASE_URL = "https://gateway.marvel.com"
    val TIMESTAMP = Timestamp(System.currentTimeMillis()).time.toString()
    const val API_KEY = ""
    const val PRIVATE_KEY = ""
    const val CHARACTERS_ENDPOINT = "/v1/public/characters"
    const val CREATORS_ENDPOINT = "/v1/public/creators"
    const val limit = 20
    fun hash() : String{
        val input = "$TIMESTAMP$PRIVATE_KEY$API_KEY"
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32,'0')
    }
}