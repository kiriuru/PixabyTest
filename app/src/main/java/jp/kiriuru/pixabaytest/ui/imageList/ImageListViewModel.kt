package jp.kiriuru.pixabaytest.ui.imageList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import jp.kiriuru.pixabaytest.data.model.Hits
import jp.kiriuru.pixabaytest.data.repository.ImageRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class ImageListViewModel @Inject constructor(
    private val repository: ImageRepository
) : ViewModel() {

    companion object {
        const val TAG = "ViewModel"
    }

    fun getImageList(query: String): Flow<PagingData<Hits>> =
        repository.getAllImages(query)
            .cachedIn(viewModelScope)
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), PagingData.empty())

}




