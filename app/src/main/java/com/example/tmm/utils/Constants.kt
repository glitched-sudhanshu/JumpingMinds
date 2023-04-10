package com.example.tmm.utils

import java.math.BigInteger
import java.security.MessageDigest
import java.sql.Timestamp

object Constants {
    const val BASE_URL = "https://gateway.marvel.com"
    val TIMESTAMP = Timestamp(System.currentTimeMillis()).time.toString()
    const val API_KEY = "1b13162ea4bd5b921555f30472cbfae3"
    const val PRIVATE_KEY = "319bdac84d50efa4be529190d67a8489acf7c18c"
    const val CHARACTERS_ENDPOINT = "/v1/public/characters"
    const val CREATORS_ENDPOINT = "/v1/public/creators"
    const val COMICS_ENDPOINT = "/v1/public/comics"
    const val EVENTS_ENDPOINT = "/v1/public/events"
    const val SERIES_ENDPOINT = "/v1/public/series"
    const val limit = 20
    fun hash() : String{
        val input = "$TIMESTAMP$PRIVATE_KEY$API_KEY"
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32,'0')
    }


    const val SP_CHARACTERS = "100"
    const val SP_CREATORS = "101"
    const val SP_COMICS = "102"
    const val SP_EVENTS = "103"
    const val SP_SERIES = "104"

    var PAGINATED_VALUE_FOR_CHARACTERS = 0
    var PAGINATED_VALUE_FOR_CREATORS = 0
    var PAGINATED_VALUE_FOR_COMICS = 0
    var PAGINATED_VALUE_FOR_EVENTS = 0
    var PAGINATED_VALUE_FOR_SERIES = 0


    var LIMIT_VALUE_FOR_CHARACTERS = "15"
    var LIMIT_VALUE_FOR_CREATORS = "15"
    var LIMIT_VALUE_FOR_COMICS = "15"
    var LIMIT_VALUE_FOR_EVENTS = "15"
    var LIMIT_VALUE_FOR_SERIES = "15"

    const val SEARCH_FOR_CHARACTERS = "Search characters by Name"
    const val SEARCH_FOR_CREATORS = "Search creators by Name"
    const val SEARCH_FOR_COMICS = "Search comics by Title"
    const val SEARCH_FOR_EVENTS = "Search Events by Title"
    const val SEARCH_FOR_SERIES = "Search series by Title"

    const val SHARED_PREF = "myPreferences"

    const val CHARACTER_TABLE_NAME = "Charactertablename"
    const val DATABASE_NAME = "MarvelDb"
}