package jp.kiriuru.pixabaytest.ui.imageList

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import jp.kiriuru.pixabaytest.data.api.ImagePagingSource
import jp.kiriuru.pixabaytest.data.model.Hits
import jp.kiriuru.pixabaytest.data.repository.ImageRepository
import jp.kiriuru.pixabaytest.data.repository.ImageRepositoryImpl
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import javax.inject.Provider

class ImageListViewModel @Inject constructor(
    private val repository: Provider<ImageRepository>
) : ViewModel() {

    companion object {
        const val TAG = "ViewModel"
    }

    private val _query = MutableStateFlow("")
    val query: StateFlow<String> = _query.asStateFlow()

    private var newPagingSource: PagingSource<*, *>? = null


//    val flow = Pager(PagingConfig(pageSize = 5)) {
//        repository.searchImage(query = _query.value)
//    }.flow.cachedIn(viewModelScope)

    var image: StateFlow<PagingData<Hits>> = query
        .map(::newPager)
        .flatMapConcat { pager -> pager.flow }
        .cachedIn(scope = viewModelScope)
        .stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())


    private fun newPager(query: String): Pager<Int, Hits> {
        Log.d(TAG, " New Pager $query")
        return Pager(PagingConfig(5, enablePlaceholders = false)) {
            val queryImageCase = repository.get()
            Log.d(TAG, "repo paging data $queryImageCase ")
            queryImageCase.searchImage(query = query).also { newPagingSource = it}
        }

    }

    fun setQuery(query: String) {
        _query.tryEmit(query)
        Log.d(TAG, " viewModel _query = ${_query.value} and Query $query")
    }

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




