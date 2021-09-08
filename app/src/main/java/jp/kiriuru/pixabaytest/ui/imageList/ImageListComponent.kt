package jp.kiriuru.pixabaytest.ui.imageList

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.Subcomponent
import dagger.multibindings.IntoMap
import jp.kiriuru.pixabaytest.di.MainViewModelKey


//Example from https://github.com/android/architecture-samples/tree/dev-dagger
@Subcomponent(modules = [ImageListModule::class])
interface ImageListComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): ImageListComponent
    }

    fun inject(fragment: ImageListFragment)
}

@Module
abstract class ImageListModule {

    @Binds
    @IntoMap
    @MainViewModelKey(ImageListViewModel::class)
    abstract fun bindBViewModel(viewModel: ImageListViewModel): ViewModel
}