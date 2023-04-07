package com.example.tmm.domain.use_cases

import com.example.tmm.domain.model.MarvelComic
import com.example.tmm.domain.repository.MarvelRepository
import com.example.tmm.utils.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ComicUseCase @Inject constructor(
    private val repository: MarvelRepository
){
    operator fun invoke(offset : Int) : Flow<Response<List<MarvelComic>>> = flow{
        try {
            emit(Response.Loading<List<MarvelComic>>())
            val list = repository.getAllComics(offset = offset).data.results.map {
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