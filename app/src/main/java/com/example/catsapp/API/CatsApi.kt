package com.example.catsapp.API

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface CatsApi {
    @GET("v1/images/search")
    suspend fun getCats(
        //@Header("x-api-key") apiKey: String  // Добавление заголовка для API ключа
        @Query("") cats : String,
        //@Query("apiKey") apiKey : String,
    ) : Response <CatsModel>
}