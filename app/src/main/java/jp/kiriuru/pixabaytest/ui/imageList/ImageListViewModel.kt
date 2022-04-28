package jp.kiriuru.pixabaytest.ui.imageList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import jp.kiriuru.pixabaytest.data.model.Hits
import jp.kiriuru.pixabaytest.data.repository.ImageRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.shareIn
import javax.inject.Inject
import javax.inject.Provider

class ImageListViewModel @Inject constructor(
    private val repository: ImageRepository
) : ViewModel() {

    companion object {
        const val TAG = "ViewModel"
    }

    fun getImageList(query: String): Flow<PagingData<Hits>> =
        repository.getAllImages(query)
            .cachedIn(viewModelScope)
            .shareIn(viewModelScope, SharingStarted.WhileSubscribed(5000))


    @Suppress("UNCHECKED_CAST")
    class Factory @Inject constructor(
        private val viewModerProvider: Provider<ImageListViewModel>
    ) : ViewModelProvider.Factory {


        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            require(modelClass == ImageListViewModel::class.java)
            return viewModerProvider.get() as T
        }
    }

}




