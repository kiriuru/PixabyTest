package jp.kiriuru.pixabaytest.data.model


data class Hits(
    val id: Long,
    val pageURL: String,
    val type: String,
    val tags: String,
    val previewURL: String,
    val previewWidth: Long,
    val previewHeight: Long,
    val webformatURL: String,
    val webformatWidth: Long,
    val webformatHeight: Long,
    val largeImageURL: String,
    val fullHDURL: String,
    val imageURL: String,
    val imageWidth: Long,
    val imageHeight: Long,
    val imageSize: Long,
    val views: Long,
    val downloads: Long,
    val favorites: Long,
    val likes: Long,
    val comments: Long,
    val userId: String,
    val user: String,
    val userImageURL: String
)


internal fun HitsDto.toHits(): Hits {
    return Hits(
        id = id,
        pageURL = pageURL,
        type = type,
        tags = tags,
        previewURL = previewURL,
        previewWidth = previewWidth,
        previewHeight = previewHeight,
        webformatURL = webformatURL,
        webformatWidth = webformatWidth,
        webformatHeight = webformatHeight,
        largeImageURL = largeImageURL,
        fullHDURL = fullHDURL,
        imageURL = imageURL,
        imageWidth = imageWidth,
        imageHeight = imageHeight,
        imageSize = imageSize,
        views = views,
        downloads = downloads,
        favorites = favorites,
        likes = likes,
        comments = comments,
        userId = userId,
        user = user,
        userImageURL = userImageURL
    )
}