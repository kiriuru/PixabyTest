package jp.kiriuru.pixabaytest.ui.imageList

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import jp.kiriuru.pixabaytest.data.api.ImagePagingSource
import jp.kiriuru.pixabaytest.data.model.Hits
import jp.kiriuru.pixabaytest.data.repository.ImageRepository
import jp.kiriuru.pixabaytest.data.repository.ImageRepositoryImpl
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class ImageListViewModel @Inject constructor(
    private val repository: ImageRepository
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

    @FlowPreview
    val image: StateFlow<PagingData<Hits>> = _query
        .map(::newPager)
        .flatMapConcat { pager -> pager.flow }
        .stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())


    private fun newPager(query: String): Pager<Int, Hits> {
        Log.d(TAG, " New Pager $query")
        return Pager(PagingConfig(5, enablePlaceholders = false)) {
            val queryImageCase = repository.searchImage(query)
            Log.d(TAG, "repo paging data $queryImageCase ")
            queryImageCase.also { newPagingSource = it }


        }

    }

    fun setQuery(query: String) {
        _query.tryEmit(query)
        Log.d(TAG, " viewModel _query = ${_query.value} and Query $query")
    }
}


//private val _data = MutableStateFlow<PixabayResponse?>(null)
//val data: StateFlow<PixabayResponse?> = _data.asStateFlow()
//
//init {
//    viewModelScope.launch {
//        _data.value = repository.searchImage("", 30).body()
//    }
//}
//
//fun setData(searchRequest: String, perPage: Int) {
//    viewModelScope.launch {
//        _data.tryEmit(repository.searchImage(req = searchRequest, perPage = perPage).body())
//    }
//}





