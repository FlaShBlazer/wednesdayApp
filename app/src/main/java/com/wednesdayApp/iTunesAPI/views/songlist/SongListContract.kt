package com.wednesdayApp.iTunesAPI.views.songlist

import com.wednesdayApp.iTunesAPI.api.model.Track

class SongListContract {
    interface View {
        fun displayMessage(message: String?)
        fun setLoadingIndicator(isLoading: Boolean)
        fun displayTracks(dataTracks: List<Track?>?)
    }

    internal interface Presenter {
        fun getTracks(term: String?)
    }
}