package com.example.akinator.network

import com.example.akinator.model.SongPreview
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PreviewApiResponse(
    @field:Json(name = "data") val data: List<PreviewData>
)

@JsonClass(generateAdapter = true)
data class PreviewData(
    @field:Json(name = "title") val title: String,
    @field:Json(name = "artist") val artist: PreviewArtist,
    @field:Json(name = "preview") val preview: String,
    @field:Json(name = "album") val album: PreviewAlbum
) {
    fun toSongPreview() = SongPreview(
        title,
        artist.name,
        preview,
        album.coverSmall
    )
}

@JsonClass(generateAdapter = true)
data class PreviewAlbum(
    @field: Json(name = "cover_small") val coverSmall: String
)

@JsonClass(generateAdapter = true)
data class PreviewArtist(
    @field:Json(name = "name") val name: String
)