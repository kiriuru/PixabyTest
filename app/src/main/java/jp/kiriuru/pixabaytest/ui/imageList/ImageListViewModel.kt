package jp.kiriuru.pixabaytest.ui.imageList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jp.kiriuru.pixabaytest.data.model.PixabayResponse
import jp.kiriuru.pixabaytest.data.repository.ImageRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class ImageListViewModel @Inject constructor(
    private val repository: ImageRepository
) : ViewModel() {

    private val _data = MutableStateFlow<PixabayResponse?>(null)
    val data: StateFlow<PixabayResponse?> = _data.asStateFlow()

    init {
        viewModelScope.launch {
            _data.value = repository.searchImage("", 30)
        }
    }

    fun setData(searchRequest: String, perPage: Int) {
        viewModelScope.launch {
            _data.tryEmit(repository.searchImage(req = searchRequest, perPage = perPage))
        }
    }


    //SharedFlow + Resource
//    fun searchImage(req: String, perPage: Int) = flow {
//        request = req
//        Log.d(TAG_VM, "Start")
//        emit(Resource.loading(data = null))
//        try {
//            Log.d(TAG_VM, "Success")
//            emit(Resource.success(data = repository.searchImage(req = request, perPage)))
//        } catch (exception: Exception) {
//            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred"))
//        }
//    }.shareIn(viewModelScope, started = SharingStarted.Lazily, replay = 1)


}

