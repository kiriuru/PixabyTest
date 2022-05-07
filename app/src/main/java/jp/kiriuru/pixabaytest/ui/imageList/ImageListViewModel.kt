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

    private val _data = MutableStateFlow<PagingData<Hits>?>(null)
    val data: StateFlow<PagingData<Hits>?> = _data.asStateFlow()

    init {
        viewModelScope.launch {
            getImagesList(" ").collectLatest { pagingData ->
                _data.tryEmit(pagingData)
            }
        }
    }

    fun setQuery(query: String) {
        viewModelScope.launch {
            getImagesList(query = query).collectLatest { pagingData ->
                _data.tryEmit(pagingData)
            }
        }
    }

    private fun getImagesList(query: String): Flow<PagingData<Hits>> =
        repository.getAllImages(query = query)
            .cachedIn(viewModelScope)
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), PagingData.empty())

}




