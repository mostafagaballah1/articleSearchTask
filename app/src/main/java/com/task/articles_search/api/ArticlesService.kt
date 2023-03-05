package com.task.articles_search.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ArticlesService {

    @GET("svc/search/v2/articlesearch.json?$API_KEY")
    suspend fun searchArticles(
        @Query("q") query: String,
        @Query("page") page: Int,
    ): SearchResponse

    @GET("svc/mostpopular/v2/emailed/7.json?$API_KEY")
    suspend fun mostEmailedArticles(): PopularResponse

    @GET("svc/mostpopular/v2/viewed/7.json?$API_KEY")
    suspend fun mostViewedArticles(): PopularResponse

    @GET("svc/mostpopular/v2/shared/7.json?$API_KEY")
    suspend fun mostSharedArticles(): PopularResponse


    companion object {
        private const val BASE_URL = "https://api.nytimes.com/"

        fun create(): ArticlesService {
            val logger = HttpLoggingInterceptor()
            logger.level = Level.BASIC

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ArticlesService::class.java)
        }
    }
}

private const val API_KEY = "api-key=0VRnxPOdXwPyqwnWWOdTB0EDarQaxRAa"
