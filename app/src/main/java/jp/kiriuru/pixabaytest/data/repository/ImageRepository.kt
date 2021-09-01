package jp.kiriuru.pixabaytest.data.repository

import jp.kiriuru.pixabaytest.data.api.Api
import javax.inject.Inject

class ImageRepository @Inject constructor(private val apiService: Api) {

    suspend fun searchImage(req: String, perPage: Int) = apiService.searchImage(
        request = req,
        perPage = perPage
    )
}