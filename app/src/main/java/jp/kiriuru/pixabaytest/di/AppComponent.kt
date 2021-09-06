package jp.kiriuru.pixabaytest.di

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import dagger.*
import jp.kiriuru.pixabaytest.data.api.Api
import jp.kiriuru.pixabaytest.data.repository.ImageRepository
import jp.kiriuru.pixabaytest.data.repository.ImageRepositoryImpl
import jp.kiriuru.pixabaytest.ui.main.MainComponent
import jp.kiriuru.pixabaytest.ui.main.MainViewModelFactory
import jp.kiriuru.pixabaytest.utils.Const
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

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

    fun mainComponent(): MainComponent.Factory


    val imageRepository: ImageRepository


}

//interface AppBindsModule{
//@Binds
//@[IntoMap MainViewModelKey(MainViewModel::class)]
//    fun provideMainViewModel(mainViewModel: MainViewModel): ViewModel
//}

@Module
interface ViewModelBuilderModule {
    @Binds
    fun bindViewModelFactory(
        factory: MainViewModelFactory
    ): ViewModelProvider.Factory
}

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
}

@Module
interface AppModuleBinds {

    @Suppress("Fun Name")
    @Binds
    fun bindRepository(imageRepositoryImpl: ImageRepositoryImpl): ImageRepository
}

@Module(subcomponents = [MainComponent::class])
object SubcomponentModule