package com.example.tmm.domain.use_cases

import com.example.tmm.domain.model.Character
import com.example.tmm.domain.model.MarvelEvent
import com.example.tmm.domain.model.MarvelSeries
import com.example.tmm.domain.repository.MarvelRepository
import com.example.tmm.utils.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class SeriesUseCase @Inject constructor(
    private val repository: MarvelRepository
) {
    operator fun invoke(offset : Int) : Flow<Response<List<MarvelSeries>>> = flow {
        try{
            emit(Response.Loading<List<MarvelSeries>>())
            val list = repository.getAllSeries(offset = offset).data.results.map {
                it.toMarvelSeries()
            }
            emit(Response.Success<List<MarvelSeries>>(list))
        }
        catch (e : HttpException){
            emit(Response.Error<List<MarvelSeries>>(e.printStackTrace().toString()))
        }
        catch (e : IOException)
        {
            emit(Response.Error<List<MarvelSeries>>(e.printStackTrace().toString()))
        }
    }
}