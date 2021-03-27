package com.wednesdayApp.iTunesAPI.views.songdetail

import com.wednesdayApp.iTunesAPI.api.model.Track

class SongDetailContract {
    internal interface View {
        fun displayMessage(message: String?)
        fun displayTrack(track: Track?)
    }
}