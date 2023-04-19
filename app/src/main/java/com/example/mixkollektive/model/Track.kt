package com.example.mixkollektive

import com.google.gson.annotations.SerializedName


data class Item(
    @SerializedName("track") val track: Track,
)

data class Image(
    @SerializedName("url") val url: String, @SerializedName("height") val height: Int
)

data class Album(
    @SerializedName("images") val images: List<Image>
)

data class Track(
    @SerializedName("external_urls") val externalUrls: SpotifyLink,
    @SerializedName("name") val name: String,
    @SerializedName("album") val album: Album
)


data class SpotifyLink(
    @SerializedName("spotify") val spotify: String,
)
