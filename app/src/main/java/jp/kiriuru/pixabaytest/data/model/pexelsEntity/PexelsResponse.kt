package jp.kiriuru.pixabaytest.data.model.pexelsEntity

import androidx.annotation.IntRange
import com.google.gson.annotations.SerializedName

data class PexelsResponse(
    @SerializedName("photos") val photos: List<Photo>,
    @SerializedName("total_results") @IntRange(from = 1) val total_results: Int
)