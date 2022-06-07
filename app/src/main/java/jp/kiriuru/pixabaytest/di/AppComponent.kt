package jp.kiriuru.pixabaytest.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import jp.kiriuru.pixabaytest.data.repository.PexelsImageRepository
import jp.kiriuru.pixabaytest.ui.imageList.ImageListComponent


//Example from https://github.com/android/architecture-samples/tree/dev-dagger
@Component(
    modules = [
        AppModuleBinds::class,
        ViewModelBuilderModule::class,
        NetworkModule::class,
        SubcomponentModule::class
    ]
)
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): AppComponent
    }

    fun imageListComponent(): ImageListComponent.Factory

    //   val imageRepository: ImageRepository
    val pexelsImageRepository: PexelsImageRepository
}

@Module(subcomponents = [ImageListComponent::class])
object SubcomponentModule