package jp.kiriuru.myapplication21.data.api

class ApiHelper(private val apiService: Api) {
    suspend fun searchImage(req: String) = apiService.searchImage(request = req)
}