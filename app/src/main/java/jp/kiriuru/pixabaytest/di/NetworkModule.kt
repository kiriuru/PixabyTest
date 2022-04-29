package jp.kiriuru.pixabaytest.di

import android.app.Application
import coil.ImageLoader
import dagger.Module
import dagger.Provides
import jp.kiriuru.pixabaytest.data.api.Api
import jp.kiriuru.pixabaytest.utils.Const
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    fun provideNetworkService(): Api {
        val retrofit = Retrofit.Builder()
            .baseUrl(Const.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create()
    }

    @Provides
    fun provideImageLoader(application: Application): ImageLoader {
        return ImageLoader(application)
    }
}