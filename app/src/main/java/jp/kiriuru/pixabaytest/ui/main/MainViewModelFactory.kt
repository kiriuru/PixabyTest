package jp.kiriuru.pixabaytest.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import jp.kiriuru.pixabaytest.data.api.Api
import jp.kiriuru.pixabaytest.data.repository.ImageRepository
import javax.inject.Inject
import javax.inject.Provider

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory @Inject constructor(
    private val viewModelFactories: Map<Class<out ViewModel>,
            @JvmSuppressWildcards Provider<ViewModel>>,
) : ViewModelProvider.Factory {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return viewModelFactories.getValue(modelClass as Class<out ViewModel>).get() as T
    }

    val viewModelClasses get() = viewModelFactories.keys

//    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//        return if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
//            MainViewModel(ImageRepository(apiHelper), request) as T
//        } else {
//            throw IllegalArgumentException("ViewModel not found")
//        }
//    }
}