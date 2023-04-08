package com.example.tmm.domain.use_cases

import com.example.tmm.domain.model.MarvelComic
import com.example.tmm.domain.repository.MarvelRepository
import com.example.tmm.utils.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class SearchComicUseCase @Inject constructor(
    private val repository: MarvelRepository
){
    operator fun invoke(search : String) : Flow<Response<List<MarvelComic>>> = flow{
        try {
            emit(Response.Loading<List<MarvelComic>>())
            val list = repository.getAllSearchComics(search = search).data.results.map {
                it.toComic()
            }
            emit(Response.Success<List<MarvelComic>>(list))
        }
        catch (e : HttpException){
            emit(Response.Error<List<MarvelComic>>(e.printStackTrace().toString()))
        }
        catch (e : IOException) {
            emit(Response.Error<List<MarvelComic>>(e.printStackTrace().toString()))
        }
    }
}