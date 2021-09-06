package jp.kiriuru.pixabaytest.ui.main

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.Subcomponent
import dagger.multibindings.IntoMap
import jp.kiriuru.pixabaytest.di.MainViewModelKey

@Subcomponent(modules = [MainModule::class])
interface MainComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): MainComponent
    }

    fun inject(fragment: MainFragment)
}

@Module
abstract class MainModule {

    @Binds
    @IntoMap
    @MainViewModelKey(MainViewModel::class)
    abstract fun bindBViewModel(viewModel: MainViewModel): ViewModel
}