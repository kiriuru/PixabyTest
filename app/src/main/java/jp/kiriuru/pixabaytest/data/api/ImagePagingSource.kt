package jp.kiriuru.pixabaytest.data.api

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import jp.kiriuru.pixabaytest.data.model.Hits
import jp.kiriuru.pixabaytest.utils.Const.Companion.MAX_PAGE_SIZE
import retrofit2.HttpException


class ImagePagingSource @AssistedInject constructor(
    private val apiService: Api,
    @Assisted("query") private val query: String
) : PagingSource<Int, Hits>() {

    override fun getRefreshKey(state: PagingState<Int, Hits>): Int? {
        Log.d(TAG, " get refresh key")
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Hits> {
        Log.d(TAG, " load paging data 1, $query")
//        if (query.isBlank()) {
//            return LoadResult.Page(emptyList(), prevKey = null, nextKey = null)
//        }
        try {
            val page: Int = params.key ?: 1
            val pageSize: Int = params.loadSize.coerceAtMost(MAX_PAGE_SIZE)
            val response = apiService.searchImage(q = query, page = page, perPage = pageSize)
            Log.d(
                TAG, "query $query load data ${response.body()!!.hits[1]} " +
                        "\n page $page " +
                        "\n page Size $pageSize"
            )
            if (response.isSuccessful) {
                val hits = response.body()!!.hits
                val nextPage = if (hits.isEmpty()) null else page + 1
                val prevPage = if (page > 1) page - 1 else null
                Log.d(
                    TAG, "hits ${hits[1]}" +
                            "\n next page $nextPage" +
                            "\n prev page $prevPage"
                )
                return LoadResult.Page(
                    hits,
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
        fun create(@Assisted("query") query: String): ImagePagingSource
    }

    companion object {
        const val TAG = "ImagePagingSource"
    }
}