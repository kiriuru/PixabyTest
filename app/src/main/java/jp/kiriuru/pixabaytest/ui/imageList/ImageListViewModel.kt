package jp.kiriuru.pixabaytest.ui.imageList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import jp.kiriuru.pixabaytest.data.model.Hits
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import javax.inject.Provider

class ImageListViewModel @Inject constructor(
    private val queryImageUseCase: Provider<QueryImageUseCase>
) : ViewModel() {

    private val _query = MutableStateFlow("")
    val query: StateFlow<String> = _query.asStateFlow()

    private var newPagingSource: PagingSource<*, *>? = null

    @FlowPreview
    val image: StateFlow<PagingData<Hits>> = query
        .map(::newPager)
        .flatMapConcat { pager -> pager.flow }
        .stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())


    fun newPager(query: String): Pager<Int, Hits> {
        return Pager(PagingConfig(5, enablePlaceholders = false)) {
            val queryImageCase = queryImageUseCase.get()
            queryImageCase(query).also { newPagingSource = it }
        }

    }

    fun setQuery(query: String) {
        _query.tryEmit(query)
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





