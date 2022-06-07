package jp.kiriuru.pixabaytest.di

import dagger.Binds
import dagger.Module
import jp.kiriuru.pixabaytest.data.repository.PexelsImageRepository
import jp.kiriuru.pixabaytest.data.repository.PexelsImageRepositoryImpl

@Module
interface AppModuleBinds {

//    @Suppress("PixabayRepo")
//    @Binds
//    fun bindRepositoryPixabay(imageRepositoryImpl: ImageRepositoryImpl): ImageRepository

    @Suppress("PexelsRepo")
    @Binds
    fun bindRepositoryPexels(pexelsImageRepositoryImpl: PexelsImageRepositoryImpl): PexelsImageRepository
}
