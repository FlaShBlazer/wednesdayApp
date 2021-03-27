package com.wednesdayApp.iTunesAPI.api.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class TrackModel {
    @SerializedName("resultCount")
    @Expose
    var resultCount: Int? = null

    @SerializedName("results")
    @Expose
    var tracks: List<Track>? = null
}