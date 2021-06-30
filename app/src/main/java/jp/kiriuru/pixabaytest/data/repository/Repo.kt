package jp.kiriuru.pixabaytest.data.repository

import jp.kiriuru.pixabaytest.data.api.ApiHelper

class Repo(private val apiHelper: ApiHelper) {
    suspend fun searchImage(req: String, perPage: Int) = apiHelper.searchImage(
        req = req,
        perPage = perPage
    )
}