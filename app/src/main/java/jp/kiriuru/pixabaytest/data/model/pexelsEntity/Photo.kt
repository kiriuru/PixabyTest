package jp.kiriuru.pixabaytest.data.model.pexelsEntity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Photo(
    @SerializedName("alt") val alt: String,
    @SerializedName("avg_color") val avg_color: String,
    @SerializedName("height") val height: Int,
    @SerializedName("id") val id: Int,
    @SerializedName("liked") val liked: Boolean,
    @SerializedName("photographer") val photographer: String,
    @SerializedName("photographer_id") val photographer_id: Int,
    @SerializedName("photographer_url") val photographer_url: String,
    @SerializedName("src") val src: Src,
    @SerializedName("url") val url: String,
    @SerializedName("width") val width: Int
) : Parcelable