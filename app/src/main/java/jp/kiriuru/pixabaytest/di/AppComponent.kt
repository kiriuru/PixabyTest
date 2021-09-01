package jp.kiriuru.pixabaytest.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.multibindings.IntoMap
import jp.kiriuru.pixabaytest.ui.MainActivity
import jp.kiriuru.pixabaytest.ui.main.MainFragment
import jp.kiriuru.pixabaytest.ui.main.MainViewModel
import jp.kiriuru.pixabaytest.ui.main.MainViewModelFactory

@Component()
interface  AppComponent {

    fun inject(activity:MainActivity)
    fun inject(fragment: MainFragment)
    //val factory:MainViewModelFactory


}
@Module
interface ApBindsModule{
    @Binds
    @[IntoMap MainViewModelKey(MainViewModel::class)]
    fun provideMainViewModel(mainViewModel: MainViewModel): ViewModel

}