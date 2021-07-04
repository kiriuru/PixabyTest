package jp.kiriuru.pixabaytest.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import jp.kiriuru.pixabaytest.data.api.Api
import jp.kiriuru.pixabaytest.data.repository.ImageRepository

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(private val apiHelper: Api, private val request: String) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            MainViewModel(ImageRepository(apiHelper), request) as T
        } else {
            throw IllegalArgumentException("ViewModel not found")
        }
    }
}