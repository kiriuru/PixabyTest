package jp.kiriuru.pixabaytest.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import jp.kiriuru.pixabaytest.data.repository.Repo
import jp.kiriuru.pixabaytest.utils.Resource
import kotlinx.coroutines.Dispatchers

const val TAG = "MAIN_VIEW_MODEL"


class MainViewModel(private val repo: Repo) : ViewModel() {
    fun searchImage(req: String, perPage: Int) = liveData(Dispatchers.IO) {
        Log.d(TAG, "Start")
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = repo.searchImage(req, perPage)))
            Log.d(TAG, "Succses")
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

}

