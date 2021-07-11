package jp.kiriuru.pixabaytest.data.api

import jp.kiriuru.pixabaytest.utils.Const.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {
    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiService: Api = getRetrofit().create(Api::class.java)
}