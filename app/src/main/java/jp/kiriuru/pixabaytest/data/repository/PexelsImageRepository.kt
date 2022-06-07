package jp.kiriuru.pixabaytest.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import jp.kiriuru.pixabaytest.data.api.PexelsImagePagingSource
import jp.kiriuru.pixabaytest.data.model.pexelsEntity.Photo
import jp.kiriuru.pixabaytest.utils.Const
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


interface PexelsImageRepository {

    fun getAllImages(query: String): Flow<PagingData<Photo>>
}

class PexelsImageRepositoryImpl @Inject constructor(private val pexelsImagePagingSource: PexelsImagePagingSource.Factory) :
    PexelsImageRepository {

    override fun getAllImages(query: String): Flow<PagingData<Photo>> {
        return Pager(
            config = PagingConfig(pageSize = Const.DEFAULT_PAGE_SIZE, enablePlaceholders = false),
            pagingSourceFactory = { pexelsImagePagingSource.create(query) },
            initialKey = 1
        ).flow
    }
}


