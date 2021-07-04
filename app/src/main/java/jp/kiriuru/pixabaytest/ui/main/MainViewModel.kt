package jp.kiriuru.pixabaytest.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import jp.kiriuru.pixabaytest.data.repository.ImageRepository
import jp.kiriuru.pixabaytest.utils.Resource

const val TAG = "MAIN_VIEW_MODEL"


class MainViewModel(private val repository: ImageRepository, var request: String) : ViewModel() {

    fun searchImage(req: String, perPage: Int) = liveData(viewModelScope.coroutineContext) {
        request = req
        Log.d(TAG, "Start")
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = repository.searchImage(request, perPage)))
            Log.d(TAG, "Succses")
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

}

