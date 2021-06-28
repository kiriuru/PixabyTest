package jp.kiriuru.myapplication21.data.entitys

import com.google.gson.annotations.SerializedName


data class PixabayResponse(
    @SerializedName("total") val total: Long,
    @SerializedName("totalHits") val totalHits: Long,
    @SerializedName("hits") val hits: List<Hits>
)
