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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Hits

        if (id != other.id) return false
        if (pageURL != other.pageURL) return false
        if (type != other.type) return false
        if (tags != other.tags) return false
        if (previewURL != other.previewURL) return false
        if (previewWidth != other.previewWidth) return false
        if (previewHeight != other.previewHeight) return false
        if (webformatURL != other.webformatURL) return false
        if (webformatWidth != other.webformatWidth) return false
        if (webformatHeight != other.webformatHeight) return false
        if (largeImageURL != other.largeImageURL) return false
        if (fullHDURL != other.fullHDURL) return false
        if (imageURL != other.imageURL) return false
        if (imageWidth != other.imageWidth) return false
        if (imageHeight != other.imageHeight) return false
        if (imageSize != other.imageSize) return false
        if (views != other.views) return false
        if (downloads != other.downloads) return false
        if (favorites != other.favorites) return false
        if (likes != other.likes) return false
        if (comments != other.comments) return false
        if (userId != other.userId) return false
        if (user != other.user) return false
        if (userImageURL != other.userImageURL) return false

        return true
    }
}



