package com.example.tmm.domain.use_cases

import com.example.tmm.domain.model.MarvelEvent
import com.example.tmm.domain.repository.MarvelRepository
import com.example.tmm.utils.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class SearchEventUseCase @Inject constructor(
    private val repository: MarvelRepository
) {
    operator fun invoke(search : String) : Flow<Response<List<MarvelEvent>>> = flow {
        try{
            emit(Response.Loading<List<MarvelEvent>>())
            val list = repository.getAllSearchEvents(search = search).data.results.map {
                it.toMarvelEvent()
            }
            emit(Response.Success<List<MarvelEvent>>(list))
        }
        catch (e : HttpException){
            emit(Response.Error<List<MarvelEvent>>(e.printStackTrace().toString()))
        }
        catch (e : IOException)
        {
            emit(Response.Error<List<MarvelEvent>>(e.printStackTrace().toString()))
        }
    }
}