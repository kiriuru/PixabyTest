package jp.kiriuru.pixabaytest.data.api

import jp.kiriuru.pixabaytest.data.entitys.PixabayResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("?")
    suspend fun searchImage(
        @Query("key") key: String = "22189183-d9e96d94abe4ae7504f982890",
        @Query("q") request: String,
        //  @Query("image_type") image_type: String = "photo",
        @Query("per_page") perPage: Int = 30
    ): PixabayResponse
}