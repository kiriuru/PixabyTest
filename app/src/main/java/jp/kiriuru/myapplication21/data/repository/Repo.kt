package jp.kiriuru.myapplication21.data.repository

import jp.kiriuru.myapplication21.data.api.ApiHelper

class Repo(private val apiHelper: ApiHelper) {
    suspend fun searchImage(req: String) = apiHelper.searchImage(
        req = req
    )
}