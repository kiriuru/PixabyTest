package jp.kiriuru.pixabaytest.data.api

import androidx.annotation.IntRange
import jp.kiriuru.pixabaytest.data.model.PixabayResponse
import jp.kiriuru.pixabaytest.utils.Const.Companion.API_KEY
import jp.kiriuru.pixabaytest.utils.Const.Companion.DEFAULT_PAGE_SIZE
import jp.kiriuru.pixabaytest.utils.Const.Companion.MAX_PAGE_SIZE
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("?")
    suspend fun searchImages(
        @Query("key") key: String = API_KEY,
        @Query("q") q: String,
        @Query("page") @IntRange(from = 1) page: Int = 1,
        @Query("per_page") @IntRange(
            from = 2,
            to = MAX_PAGE_SIZE.toLong()
        ) perPage: Int = DEFAULT_PAGE_SIZE
    ): Response<PixabayResponse>

}