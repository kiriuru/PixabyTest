package jp.kiriuru.pixabaytest.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import jp.kiriuru.pixabaytest.data.api.ImagePagingSource
import jp.kiriuru.pixabaytest.data.model.Hits
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


interface ImageRepository {

    fun getAllImages(query: String): Flow<PagingData<Hits>>
}

class ImageRepositoryImpl @Inject constructor(private val imagePagingSource: ImagePagingSource.Factory) :
    ImageRepository {

    override fun getAllImages(query: String): Flow<PagingData<Hits>> {
        return Pager(
            config = PagingConfig(pageSize = 5, enablePlaceholders = false),
            pagingSourceFactory = { imagePagingSource.create(query) },
            initialKey = 1
        ).flow
    }


    companion object {
        const val TAG = "REPO"
    }
}