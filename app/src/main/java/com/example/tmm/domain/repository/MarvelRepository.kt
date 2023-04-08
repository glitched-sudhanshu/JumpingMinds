package com.example.tmm.domain.repository

import com.example.tmm.data.data_source.dto.*
import com.example.tmm.domain.model.Creator

interface MarvelRepository {

    suspend fun getAllCharacter(offset: Int): CharactersDTO

    suspend fun getAllSearchCharacter(search: String): CharactersDTO

    suspend fun getAllCreator(offset: Int): CreatorsDTO

    suspend fun getAllSearchCreator(search: String): CreatorsDTO

    suspend fun getAllComics(offset: Int): ComicsDTO

    suspend fun getAllSearchComics(search: String): ComicsDTO

    suspend fun getAllEvents(offset: Int): EventsDTO

    suspend fun getAllSearchEvents(search: String): EventsDTO

    suspend fun getAllSeries(offset: Int): SeriesDTO

    suspend fun getAllSearchSeries(search: String): SeriesDTO
}