package com.example.tmm.di

import android.content.Context
import com.example.tmm.data.data_source.MarvelApi
import com.example.tmm.data.repository.MarvelRepositoryImpl
import com.example.tmm.domain.repository.MarvelRepository
import com.example.tmm.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(context: Context): OkHttpClient {
        val cacheSize = 5 * 1024 * 1024
        val cache = Cache(context.cacheDir, cacheSize.toLong())
        return OkHttpClient.Builder()
            .cache(cache)
            .build()
    }

    @Provides
    @Singleton
    fun provideMarvelApi(client: OkHttpClient): MarvelApi {
        val cacheControl = CacheControl.Builder()
            .maxAge(1, TimeUnit.MINUTES)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client.newBuilder().addInterceptor { chain ->
                val request = chain.request()
                    .newBuilder()
                    .header("Cache-Control", cacheControl.toString())
                    .build()
                chain.proceed(request)
            }.build())
            .build()
        return retrofit.create(MarvelApi::class.java)
    }


    @Provides
    @Singleton
    fun provideMarvelRepository(api: MarvelApi): MarvelRepository {
        return MarvelRepositoryImpl(api)
    }

}