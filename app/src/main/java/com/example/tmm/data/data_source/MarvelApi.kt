package com.example.tmm.data.data_source

import com.example.tmm.data.data_source.dto.*
import com.example.tmm.utils.Constants
import com.example.tmm.utils.Constants.API_KEY
import com.example.tmm.utils.Constants.CHARACTERS_ENDPOINT
import com.example.tmm.utils.Constants.COMICS_ENDPOINT
import com.example.tmm.utils.Constants.CREATORS_ENDPOINT
import com.example.tmm.utils.Constants.EVENTS_ENDPOINT
import com.example.tmm.utils.Constants.SERIES_ENDPOINT
import com.example.tmm.utils.Constants.TIMESTAMP
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelApi {

    @GET(CHARACTERS_ENDPOINT)
    suspend fun getAllCharacters(
        @Query("apikey") apikey: String = API_KEY,
        @Query("ts") ts: String = TIMESTAMP,
        @Query("hash") hash: String = Constants.hash(),
        @Query("offset") offset: String,
    ): CharactersDTO

    @GET(CHARACTERS_ENDPOINT)
    suspend fun getAllSearchCharacters(
        @Query("apikey") apikey: String = API_KEY,
        @Query("ts") ts: String = TIMESTAMP,
        @Query("hash") hash: String = Constants.hash(),
        @Query("nameStartsWith")search:String
    ): CharactersDTO

    @GET(CREATORS_ENDPOINT)
    suspend fun getAllCreators(
        @Query("apikey") apikey: String = API_KEY,
        @Query("ts") ts: String = TIMESTAMP,
        @Query("hash") hash: String = Constants.hash(),
        @Query("offset") offset: String,
    ): CreatorsDTO

    @GET(CREATORS_ENDPOINT)
    suspend fun getAllSearchCreators(
        @Query("apikey") apikey: String = API_KEY,
        @Query("ts") ts: String = TIMESTAMP,
        @Query("hash") hash: String = Constants.hash(),
        @Query("nameStartsWith")search:String
    ): CreatorsDTO

    @GET(COMICS_ENDPOINT)
    suspend fun getAllComics(
        @Query("apikey") apikey: String = API_KEY,
        @Query("ts") ts: String = TIMESTAMP,
        @Query("hash") hash: String = Constants.hash(),
        @Query("offset") offset: String,
    ): ComicsDTO

    @GET(COMICS_ENDPOINT)
    suspend fun getAllSearchComic(
        @Query("apikey") apikey: String = API_KEY,
        @Query("ts") ts: String = TIMESTAMP,
        @Query("hash") hash: String = Constants.hash(),
        @Query("titleStartsWith")search:String
    ): ComicsDTO

    @GET(EVENTS_ENDPOINT)
    suspend fun getAllEvents(
        @Query("apikey") apikey: String = API_KEY,
        @Query("ts") ts: String = TIMESTAMP,
        @Query("hash") hash: String = Constants.hash(),
        @Query("offset") offset: String,
    ): EventsDTO

    @GET(EVENTS_ENDPOINT)
    suspend fun getAllSearchEvents(
        @Query("apikey") apikey: String = API_KEY,
        @Query("ts") ts: String = TIMESTAMP,
        @Query("hash") hash: String = Constants.hash(),
        @Query("nameStartsWith")search:String
    ): EventsDTO

    @GET(SERIES_ENDPOINT)
    suspend fun getAllSeries(
        @Query("apikey") apikey: String = API_KEY,
        @Query("ts") ts: String = TIMESTAMP,
        @Query("hash") hash: String = Constants.hash(),
        @Query("offset") offset: String,
    ): SeriesDTO

    @GET(SERIES_ENDPOINT)
    suspend fun getAllSearchSeries(
        @Query("apikey") apikey: String = API_KEY,
        @Query("ts") ts: String = TIMESTAMP,
        @Query("hash") hash: String = Constants.hash(),
        @Query("titleStartsWith")search:String
    ): SeriesDTO

}