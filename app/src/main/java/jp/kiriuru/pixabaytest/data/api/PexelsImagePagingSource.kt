package jp.kiriuru.pixabaytest.data.api

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import jp.kiriuru.pixabaytest.data.model.pexelsEntity.Photo
import jp.kiriuru.pixabaytest.utils.Const
import retrofit2.HttpException

class PexelsImagePagingSource @AssistedInject constructor(
    private val apiService: PexelsApi,
    @Assisted("query") private val query: String
) : PagingSource<Int, Photo>() {

    override fun getRefreshKey(state: PagingState<Int, Photo>): Int? {
        Log.d(PixabayImagePagingSource.TAG, " get refresh key")
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Photo> {

        try {
            val page: Int = params.key ?: 1
            val pageSize: Int = params.loadSize.coerceAtMost(Const.MAX_PAGE_SIZE)
            val response = apiService.searchImages(query = query, page = page, perPage = pageSize)
            Log.d(
                "Pexels", "query $query load data ${response.body()!!.photos[1]} " +
                        "\n page $page " +
                        "\n page Size $pageSize"
            )
            if (response.isSuccessful) {
                val photos = response.body()!!.photos
                val nextPage = if (photos.isEmpty()) null else page + 1
                val prevPage = if (page > 1) page - 1 else null
                Log.d(
                    "Pexels ", "hits ${photos[1]}" +
                            "\n next page $nextPage" +
                            "\n prev page $prevPage"
                )
                return LoadResult.Page(
                    photos,
                    prevKey = prevPage,
                    nextKey = nextPage
                )
            } else {
                return LoadResult.Error(HttpException(response))
            }
        } catch (e: HttpException) {
            return LoadResult.Error(e)
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(@Assisted("query") query: String): PexelsImagePagingSource
    }
}