package jp.kiriuru.pixabaytest.ui.imageList

import androidx.paging.PagingSource
import jp.kiriuru.pixabaytest.data.model.Hits
import jp.kiriuru.pixabaytest.data.repository.ImageRepository
import javax.inject.Inject

class QueryImageUseCase @Inject constructor(private val repository: ImageRepository) {
    operator fun invoke(query: String): PagingSource<Int, Hits> {
        return repository.searchImage(query)
    }
}