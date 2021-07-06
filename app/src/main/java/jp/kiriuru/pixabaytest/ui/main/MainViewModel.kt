package jp.kiriuru.pixabaytest.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jp.kiriuru.pixabaytest.data.repository.ImageRepository
import jp.kiriuru.pixabaytest.utils.Resource
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.shareIn

const val TAG = "MAIN_VIEW_MODEL"


class MainViewModel(private val repository: ImageRepository, var request: String) : ViewModel() {

    //SharedFlow + Resource
    fun searchImage(req: String, perPage: Int) = flow {
        request = req
        Log.d(TAG, "Start")
        emit(Resource.loading(data = null))
        try {
            Log.d(TAG, "Success")
            emit(Resource.success(data = repository.searchImage(request, perPage)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred"))
        }
    }.shareIn(viewModelScope, started = SharingStarted.Lazily, replay = 1)


    //SharedFlow без Resource
//    fun search3(req: String, perPage: Int) = flow {
//        request = req
//        emit(repository.searchImage(req, perPage))
//    }.shareIn(viewModelScope, started = SharingStarted.Lazily, replay = 1)

//    пример когда в  Api отдаем Flow<PixBay> убирая suspendы
//
//    suspend fun search(request: String, perPage: Int) {
//        val image: SharedFlow<PixabayResponse> = repository.searchImage(request, perPage)
//            .shareIn(viewModelScope, started = SharingStarted.Lazily, replay = 1)
//    }

    //livedata
//    fun searchImage(req: String, perPage: Int) = liveData(viewModelScope.coroutineContext) {
//        request = req
//        Log.d(TAG, "Start")
//        emit(Resource.loading(data = null))
//        try {
//            emit(Resource.success(data = repository.searchImage(request, perPage)))
//            Log.d(TAG, "Succses")
//        } catch (exception: Exception) {
//            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
//        }
//    }

}

