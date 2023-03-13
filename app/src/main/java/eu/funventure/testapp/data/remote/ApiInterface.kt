package eu.funventure.testapp.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {

    @GET("random/math")
    suspend fun getRandomData(): Response<NumberInfoDTO>

    @GET("{number}")
    suspend fun getData(
        @Path("number") number: Int
    ): Response<NumberInfoDTO>
}