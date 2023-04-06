package com.example.tmm.data.repository

import com.example.tmm.data.data_source.MarvelApi
import com.example.tmm.data.data_source.dto.CharactersDTO
import com.example.tmm.data.data_source.dto.CreatorsDTO
import com.example.tmm.domain.repository.MarvelRepository
import javax.inject.Inject

class MarvelRepositoryImpl @Inject constructor(
    private val api : MarvelApi
) : MarvelRepository {
    override suspend fun getAllCharacter(offset: Int): CharactersDTO {
        return api.getAllCharacters(offset = offset.toString())
    }

    override suspend fun getAllCreator(offset: Int): CreatorsDTO {
        return api.getAllCreators(offset = offset.toString())
    }
}