package jp.kiriuru.pixabaytest.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import jp.kiriuru.pixabaytest.data.api.ImagePagingSource
import jp.kiriuru.pixabaytest.data.model.Hits
import javax.inject.Inject


interface ImageRepository {

    fun getAllImages(query: String): LiveData<PagingData<Hits>>
}

class ImageRepositoryImpl @Inject constructor(private val imagePagingSource: ImagePagingSource.Factory) :
    ImageRepository {
    override fun getAllImages(query: String): LiveData<PagingData<Hits>> {
        return Pager(
            config = PagingConfig(pageSize = 5, enablePlaceholders = false),
            pagingSourceFactory = { imagePagingSource.create(query) },
            initialKey = 1
        ).liveData
    }


    companion object {
        const val TAG = "REPO"
    }
}