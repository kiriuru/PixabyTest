package jp.kiriuru.pixabaytest.di

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import jp.kiriuru.pixabaytest.ui.imageList.MainViewModelFactory

@Module
interface ViewModelBuilderModule {
    @Binds
    fun bindViewModelFactory(
        factory: MainViewModelFactory
    ): ViewModelProvider.Factory
}