package jp.kiriuru.pixabaytest.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import jp.kiriuru.pixabaytest.data.api.ApiHelper
import jp.kiriuru.pixabaytest.data.repository.Repo

class MainViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            MainViewModel(Repo(apiHelper)) as T
        } else {
            throw IllegalArgumentException("ViewModel not found")
        }
    }
}