package jp.kiriuru.pixabaytest.data.repository

import androidx.paging.PagingSource
import jp.kiriuru.pixabaytest.data.api.Api
import jp.kiriuru.pixabaytest.data.api.ImagePagingSource
import jp.kiriuru.pixabaytest.data.model.Hits
import jp.kiriuru.pixabaytest.data.model.PixabayResponse
import retrofit2.Response
import javax.inject.Inject


class ImageRepository @Inject constructor(private val imagePagingSourceFactory: ImagePagingSource.Factory) {
    fun searchImage(query: String): PagingSource<Int, Hits> {
        return imagePagingSourceFactory.create(query)
    }
}

//interface ImageRepository {
//    suspend fun searchImage(req: String, perPage: Int): Response<PixabayResponse>
//}

//class ImageRepositoryImpl @Inject constructor(private val apiService: Api) : ImageRepository {
//
//    override suspend fun searchImage(req: String, perPage: Int) = apiService.searchImage(
//        q = req,
//        perPage = perPage
//    )
//}

//interface ImageRepository {
//    fun searchImage(query: String): PagingSource<Int, Hits>
//}
//
//class ImageRepositoryImpl @Inject constructor(private val imagePagingSource: ImagePagingSource.Factory) :
//    ImageRepository {
//    override fun searchImage(query: String): PagingSource<Int, Hits> {
//        return imagePagingSource.create(query = query)
//    }
//}