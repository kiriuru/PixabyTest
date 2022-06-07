package jp.kiriuru.pixabaytest.data.model.pixabayEntity

import androidx.annotation.IntRange
import com.google.gson.annotations.SerializedName



data class PixabayResponse(
    @SerializedName("total") val total: Long,
    @SerializedName("totalHits") @IntRange(from = 1) val totalHits: Long,
    @SerializedName("hits") val hits: List<Hits>
)
