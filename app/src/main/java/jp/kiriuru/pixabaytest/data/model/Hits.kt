package jp.kiriuru.pixabaytest.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Hits(
    @SerializedName("id") val id: Long,
    @SerializedName("pageURL") val pageURL: String,
    @SerializedName("type") val type: String,
    @SerializedName("tags") val tags: String,
    @SerializedName("previewURL") val previewURL: String,
    @SerializedName("previewWidth") val previewWidth: Long,
    @SerializedName("previewHeight") val previewHeight: Long,
    @SerializedName("webformatURL") val webformatURL: String,
    @SerializedName("webformatWidth") val webformatWidth: Long,
    @SerializedName("webformatHeight") val webformatHeight: Long,
    @SerializedName("largeImageURL") val largeImageURL: String,
    @SerializedName("fullHDURL") val fullHDURL: String,
    @SerializedName("imageURL") val imageURL: String,
    @SerializedName("imageWidth") val imageWidth: Long,
    @SerializedName("imageHeight") val imageHeight: Long,
    @SerializedName("imageSize") val imageSize: Long,
    @SerializedName("views") val views: Long,
    @SerializedName("downloads") val downloads: Long,
    @SerializedName("favorites") val favorites: Long,
    @SerializedName("likes") val likes: Long,
    @SerializedName("comments") val comments: Long,
    @SerializedName("user_id") val userId: String,
    @SerializedName("user") val user: String,
    @SerializedName("userImageURL") val userImageURL: String
) : Parcelable {
    override fun hashCode(): Int {
        return super.hashCode()
    }
}



