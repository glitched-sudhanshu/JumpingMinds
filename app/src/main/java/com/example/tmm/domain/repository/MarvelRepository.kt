package com.example.tmm.domain.repository

import com.example.tmm.data.data_source.dto.*
import com.example.tmm.domain.model.Creator

interface MarvelRepository {

    suspend fun getAllCharacter(offset: Int): CharactersDTO

    suspend fun getAllCreator(offset: Int): CreatorsDTO

    suspend fun getAllComics(offset: Int): ComicsDTO

    suspend fun getAllEvents(offset: Int): EventsDTO

    suspend fun getAllSeries(offset: Int): SeriesDTO
}