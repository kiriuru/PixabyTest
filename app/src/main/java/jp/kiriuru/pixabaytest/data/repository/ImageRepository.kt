package jp.kiriuru.pixabaytest.data.repository

import jp.kiriuru.pixabaytest.data.api.Api
import jp.kiriuru.pixabaytest.data.model.PixabayResponse
import javax.inject.Inject


interface ImageRepository {
    suspend fun searchImage(req: String, perPage: Int): PixabayResponse
}

class ImageRepositoryImpl @Inject constructor(private val apiService: Api) : ImageRepository {

    override suspend fun searchImage(req: String, perPage: Int) = apiService.searchImage(
        request = req,
        perPage = perPage
    )
}