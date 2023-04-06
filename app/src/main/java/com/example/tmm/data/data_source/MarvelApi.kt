package com.example.tmm.data.data_source

import com.example.tmm.data.data_source.dto.CharactersDTO
import com.example.tmm.data.data_source.dto.CreatorsDTO
import com.example.tmm.utils.Constants
import com.example.tmm.utils.Constants.API_KEY
import com.example.tmm.utils.Constants.CHARACTERS_ENDPOINT
import com.example.tmm.utils.Constants.CREATORS_ENDPOINT
import com.example.tmm.utils.Constants.TIMESTAMP
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelApi {

    @GET(CHARACTERS_ENDPOINT)
    suspend fun getAllCharacters(
        @Query("apikey")apikey:String = API_KEY,
        @Query("ts")ts:String = TIMESTAMP,
        @Query("hash")hash:String = Constants.hash(),
        @Query("offset")offset:String
    ) : CharactersDTO

    @GET(CREATORS_ENDPOINT)
    suspend fun getAllCreators(
        @Query("apikey")apikey:String = API_KEY,
        @Query("ts")ts:String = TIMESTAMP,
        @Query("hash")hash:String = Constants.hash(),
        @Query("offset")offset:String
    ) : CreatorsDTO



}