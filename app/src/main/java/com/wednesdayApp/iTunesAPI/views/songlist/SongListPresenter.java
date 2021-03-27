package com.wednesdayApp.iTunesAPI.views.songlist;


import androidx.annotation.NonNull;

import com.wednesdayApp.iTunesAPI.api.APIService;
import com.wednesdayApp.iTunesAPI.api.ServiceFactory;
import com.wednesdayApp.iTunesAPI.api.model.TrackModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


class SongListPresenter implements SongListContract.Presenter {

    private final SongListContract.View songListView;

    SongListPresenter(SongListContract.View songListView) {
        this.songListView = songListView;
    }

    @Override
    public void getTracks(String term) {
        APIService service = ServiceFactory.getInstance();
        Call<TrackModel> trackModelCall = service.getTracks(term);
        trackModelCall.enqueue(new Callback<TrackModel>() {
            @Override
            public void onResponse(@NonNull Call<TrackModel> call, @NonNull Response<TrackModel> response) {
                TrackModel trackModel = response.body();
                if (trackModel.getResultCount() > 0) {
                    songListView.displayTracks(trackModel.getTracks());
                } else {
                    songListView.displayMessage("No songs found, Try again.");
                }
            }

            @Override
            public void onFailure(@NonNull Call<TrackModel> call, @NonNull Throwable t) {
                songListView.displayMessage("No songs found, Try again.");
            }
        });
    }
}