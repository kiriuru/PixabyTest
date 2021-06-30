package jp.kiriuru.pixabaytest.data.api

class ApiHelper(private val apiService: Api) {
    suspend fun searchImage(req: String, perPage: Int) = apiService.searchImage(
        request = req,
        perPage = perPage
    )
}