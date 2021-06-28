package jp.kiriuru.myapplication21.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import jp.kiriuru.myapplication21.data.repository.Repo
import jp.kiriuru.myapplication21.utils.Resource
import kotlinx.coroutines.Dispatchers

const val TAG = "MAIN_VIEW_MODEL"


class MainViewModel(private val repo: Repo) : ViewModel() {
    fun searchImage(req: String) = liveData(Dispatchers.IO) {
        Log.d(TAG, "Start")
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = repo.searchImage(req)))
            Log.d(TAG, "Succses")
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

}

