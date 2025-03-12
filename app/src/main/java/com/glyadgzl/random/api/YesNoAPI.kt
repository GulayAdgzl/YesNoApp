package com.glyadgzl.random.api
import com.glyadgzl.random.model.YesNoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
interface YesNoAPI {
    @GET("/api")
    suspend fun getRandomYesNo(): Response<YesNoResponse> // Burada dönüş tipi düzeltildi
}
