package com.example.tmm.domain.use_cases

import com.example.tmm.domain.model.Character
import com.example.tmm.domain.model.Creator
import com.example.tmm.domain.repository.MarvelRepository
import com.example.tmm.utils.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CreatorUseCase @Inject constructor(
    private val repository: MarvelRepository
    ) {
        operator fun invoke(offset : Int) : Flow<Response<List<Creator>>> = flow {
            try{
                emit(Response.Loading<List<Creator>>())
                val list = repository.getAllCreator(offset = offset).data.results.map {
                    it.toCreator()
                }
                emit(Response.Success<List<Creator>>(list))
            }
            catch (e : HttpException){
                emit(Response.Error<List<Creator>>(e.printStackTrace().toString()))
            }
            catch (e : IOException)
            {
                emit(Response.Error<List<Creator>>(e.printStackTrace().toString()))
            }
        }
    }