package jp.kiriuru.pixabaytest.di

import dagger.Module
import dagger.Provides
import jp.kiriuru.pixabaytest.data.api.PexelsApi
import jp.kiriuru.pixabaytest.utils.Const
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

@Module
class NetworkModule {

    @Provides
    fun provideNetworkService(): PexelsApi {
        val retrofit = Retrofit.Builder()
            .baseUrl(Const.PEXELS_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create()
    }

}