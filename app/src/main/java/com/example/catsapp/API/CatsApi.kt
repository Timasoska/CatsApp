package com.example.catsapp.API

import retrofit2.http.GET
import retrofit2.http.Query

interface CatsApi {
    @GET("/v1/images/search")
    suspend fun getCats(
        @Query("q")
    )
}