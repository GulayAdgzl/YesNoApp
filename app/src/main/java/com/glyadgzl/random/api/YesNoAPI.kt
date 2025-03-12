package com.glyadgzl.random.api
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
interface YesNoAPI {
    @GET("/api")
    suspend fun getRandomYesNo(): Response<YesNoAPI>
}