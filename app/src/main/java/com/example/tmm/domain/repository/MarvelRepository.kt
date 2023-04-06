package com.example.tmm.domain.repository

import com.example.tmm.data.data_source.dto.CharactersDTO
import com.example.tmm.data.data_source.dto.CreatorsDTO
import com.example.tmm.domain.model.Creator

interface MarvelRepository {

    suspend fun getAllCharacter(offset : Int):CharactersDTO

    suspend fun getAllCreator(offset: Int): CreatorsDTO
}