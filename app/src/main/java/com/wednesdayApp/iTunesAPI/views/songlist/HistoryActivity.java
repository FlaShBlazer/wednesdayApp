package com.wednesdayApp.iTunesAPI.views.songlist;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.cooltechworks.views.shimmer.ShimmerRecyclerView;
import com.google.android.material.snackbar.Snackbar;
import com.wednesdayApp.iTunesAPI.DatabaseClient;
import com.wednesdayApp.iTunesAPI.R;
import com.wednesdayApp.iTunesAPI.api.model.Track;

import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends AppCompatActivity implements SongListContract.View {
    private final List<Track> dataTracks = new ArrayList<>();
    ShimmerRecyclerView s;
    LinearLayout main;
    TextView t1, t2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        t1 = findViewById(R.id.t1);
        t2 = findViewById(R.id.t2);
        getTasks();

    }

    public void getTasks() {
        class GetTasks extends AsyncTask<Void, Void, List<Track>> {

            @Override
            protected List<Track> doInBackground(Void... voids) {
                return DatabaseClient
                        .getInstance(getApplicationContext())
                        .getAppDatabase()
                        .taskDao()
                        .getAll();

            }

            @Override
            protected void onPostExecute(List<Track> tracks) {
                super.onPostExecute(tracks);
                Log.e("LIst", tracks.get(1).getTrackName());
                t1.setText(tracks.get(1).getTrackName());
                t2.setText(tracks.get(1).getArtistName());
            }
        }
        GetTasks gt = new GetTasks();
        gt.execute();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void displayMessage(String message) {
        setLoadingIndicator(false);
        Snackbar.make(main, message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void setLoadingIndicator(boolean isLoading) {
        if (isLoading) {
            s.showShimmerAdapter();
        } else {
            s.hideShimmerAdapter();
        }
    }

    @Override
    public void displayTracks(List<Track> dataTracks) {
        setLoadingIndicator(false);
    }
}
