package jp.kiriuru.myapplication21.data.api

import jp.kiriuru.myapplication21.data.entitys.PixabayResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("?")
    suspend fun searchImage(
        @Query("key") key: String = "22189183-d9e96d94abe4ae7504f982890",
        @Query("q") request: String,
        @Query("image_type") image_type: String = "photo",
        @Query("per_page") per_page: Int = 30
    ): PixabayResponse
}