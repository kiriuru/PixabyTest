package jp.kiriuru.pixabaytest.di

import dagger.Binds
import dagger.Module
import jp.kiriuru.pixabaytest.data.repository.ImageRepository
import jp.kiriuru.pixabaytest.data.repository.ImageRepositoryImpl

@Module
interface AppModuleBinds {

    @Suppress("Fun Name")
    @Binds
    fun bindRepository(imageRepositoryImpl: ImageRepositoryImpl): ImageRepository
}
