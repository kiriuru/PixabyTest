package jp.kiriuru.myapplication21.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import jp.kiriuru.myapplication21.data.api.ApiHelper
import jp.kiriuru.myapplication21.data.repository.Repo

class MainViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            MainViewModel(Repo(apiHelper)) as T
        } else {
            throw IllegalArgumentException("ViewModel not found")
        }
    }
}