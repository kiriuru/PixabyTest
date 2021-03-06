package jp.kiriuru.pixabaytest.ui.imageList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

//Example from https://github.com/android/architecture-samples/tree/dev-dagger
@Suppress("UNCHECKED_CAST")
class MainViewModelFactory @Inject constructor(
    private val viewModelFactories: Map<Class<out ViewModel>,
            @JvmSuppressWildcards Provider<ViewModel>>,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        var creator: Provider<out ViewModel>? = viewModelFactories[modelClass]
        if (creator == null) {
            for ((key, value) in viewModelFactories) {
                if (modelClass.isAssignableFrom(key)) {
                    creator = value
                    break
                }
            }
        }
        if (creator == null) {
            throw IllegalArgumentException("Unknown model class: $modelClass")
        }
        try {
            @Suppress("UNCHECKED_CAST")
            return creator.get() as T
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}