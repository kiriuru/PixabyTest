package jp.kiriuru.pixabaytest.data.repository

import android.util.Log
import androidx.paging.PagingSource
import jp.kiriuru.pixabaytest.data.api.Api
import jp.kiriuru.pixabaytest.data.api.ImagePagingSource
import jp.kiriuru.pixabaytest.data.model.Hits
import jp.kiriuru.pixabaytest.data.model.PixabayResponse
import retrofit2.Response
import javax.inject.Inject


//class ImageRepository @Inject constructor(private val imagePagingSourceFactory: ImagePagingSource.Factory) {
//    fun searchImage(query: String): PagingSource<Int, Hits> {
//        Log.d(TAG, " creqte response with query $query")
//        return imagePagingSourceFactory.create(query)
//    }
//
//    companion object{
//        const val TAG = "REPO"
//    }
//}

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

interface ImageRepository {
    fun searchImage(query: String): PagingSource<Int, Hits>
}

class ImageRepositoryImpl @Inject constructor(private val imagePagingSource: ImagePagingSource.Factory) :
    ImageRepository {
    override fun searchImage(query: String): PagingSource<Int, Hits> {
        Log.d(TAG, " create response with query $query")
        return imagePagingSource.create(query)
    }

    companion object{
        const val TAG = "REPO"
    }

//    override fun searchImage(query: String) = imagePagingSource.create(query = query)

}