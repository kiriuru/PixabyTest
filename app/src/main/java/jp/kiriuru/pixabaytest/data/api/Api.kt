package jp.kiriuru.pixabaytest.data.api

import jp.kiriuru.pixabaytest.data.model.PixabayResponse
import jp.kiriuru.pixabaytest.utils.Const.Companion.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("?")
    suspend fun searchImage(
        @Query("key") key: String = API_KEY,
        @Query("q") request: String,
        //  @Query("image_type") image_type: String = "photo",
        @Query("per_page") perPage: Int = 30
    ): PixabayResponse
}