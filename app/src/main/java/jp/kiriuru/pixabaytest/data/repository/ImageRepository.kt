package jp.kiriuru.pixabaytest.data.repository

import jp.kiriuru.pixabaytest.data.api.Api

class ImageRepository(private val apiService: Api) {

    suspend fun searchImage(req: String, perPage: Int) = apiService.searchImage(
        request = req,
        perPage = perPage
    )
}