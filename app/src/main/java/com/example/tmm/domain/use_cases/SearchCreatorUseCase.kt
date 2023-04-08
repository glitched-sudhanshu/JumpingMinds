package com.example.tmm.domain.use_cases

import com.example.tmm.domain.model.Creator
import com.example.tmm.domain.repository.MarvelRepository
import com.example.tmm.utils.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class SearchCreatorUseCase @Inject constructor(
    private val repository: MarvelRepository
) {
    operator fun invoke(search : String) : Flow<Response<List<Creator>>> = flow {
        try{
            emit(Response.Loading<List<Creator>>())
            val list = repository.getAllSearchCreator(search = search).data.results.map {
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