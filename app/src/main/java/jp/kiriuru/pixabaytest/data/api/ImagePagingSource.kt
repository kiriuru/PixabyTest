package jp.kiriuru.pixabaytest.data.api

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import jp.kiriuru.pixabaytest.data.model.Hits
import jp.kiriuru.pixabaytest.data.model.toHits
import retrofit2.HttpException


class ImagePagingSource @AssistedInject constructor(
    private val apiService: Api,
    @Assisted("query") private val query: String
) : PagingSource<Int, Hits>() {

    override fun getRefreshKey(state: PagingState<Int, Hits>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Hits> {
        if (query.isEmpty()) {
            return LoadResult.Page(emptyList(), prevKey = null, nextKey = null)
        }
        try {
            val page: Int = params.key ?: 1
            val pageSize: Int = params.loadSize.coerceAtMost(Api.MAX_PAGE_SIZE)
            val response = apiService.searchImage(page = page, perPage = pageSize, q = query)

           if (response.hits.isNotEmpty()) {
                val hits = response.hits.map { it.toHits() }
                val nextPage = if (hits.isEmpty()) null else page + 1
                val prevKey = if (page > 1) page - 1 else null
            return    LoadResult.Page(hits, prevKey = prevKey, nextKey = nextPage)
            } else {
                return  LoadResult.Page(emptyList(), prevKey = null, nextKey = null)
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
}