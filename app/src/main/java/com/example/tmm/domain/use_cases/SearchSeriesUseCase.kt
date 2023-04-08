package com.example.tmm.domain.use_cases

import com.example.tmm.domain.model.MarvelSeries
import com.example.tmm.domain.repository.MarvelRepository
import com.example.tmm.utils.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class SearchSeriesUseCase @Inject constructor(
    private val repository: MarvelRepository
) {
    operator fun invoke(search : String) : Flow<Response<List<MarvelSeries>>> = flow {
        try{
            emit(Response.Loading<List<MarvelSeries>>())
            val list = repository.getAllSearchSeries(search = search).data.results.map {
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