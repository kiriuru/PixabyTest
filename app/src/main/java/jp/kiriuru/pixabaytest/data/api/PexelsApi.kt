package jp.kiriuru.pixabaytest.data.api

import androidx.annotation.IntRange
import jp.kiriuru.pixabaytest.data.model.pexelsEntity.PexelsResponse
import jp.kiriuru.pixabaytest.utils.Const
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface PexelsApi {
    @Headers(Const.PEXELS_API_KEY)
    @GET("v1/search")
    suspend fun searchImages(
        @Query("query") query: String,
        @Query("page") @IntRange(from = 1) page: Int = 1,
        @Query("per_page") @IntRange(
            from = 2,
            to = Const.MAX_PAGE_SIZE.toLong()
        ) perPage: Int = Const.DEFAULT_PAGE_SIZE
    ): Response<PexelsResponse>

}