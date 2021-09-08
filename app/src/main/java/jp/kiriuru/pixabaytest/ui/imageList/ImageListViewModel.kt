package jp.kiriuru.pixabaytest.ui.imageList

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jp.kiriuru.pixabaytest.data.repository.ImageRepository
import jp.kiriuru.pixabaytest.utils.Const.Companion.TAG_VM
import jp.kiriuru.pixabaytest.utils.Resource
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.shareIn
import javax.inject.Inject

class ImageListViewModel @Inject constructor(
    private val repository: ImageRepository
) : ViewModel() {
    var request: String = ""

    //SharedFlow + Resource
    fun searchImage(req: String, perPage: Int) = flow {
        request = req
        Log.d(TAG_VM, "Start")
        emit(Resource.loading(data = null))
        try {
            Log.d(TAG_VM, "Success")
            emit(Resource.success(data = repository.searchImage(req = request, perPage)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred"))
        }
    }.shareIn(viewModelScope, started = SharingStarted.Lazily, replay = 1)


}

