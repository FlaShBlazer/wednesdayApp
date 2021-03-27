package com.wednesdayApp.iTunesAPI.views.songdetail;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.Snackbar;
import com.wednesdayApp.iTunesAPI.DatabaseClient;
import com.wednesdayApp.iTunesAPI.R;
import com.wednesdayApp.iTunesAPI.Task1;
import com.wednesdayApp.iTunesAPI.api.model.Track;
import com.wednesdayApp.iTunesAPI.views.songlist.SongListView;

public class SongDetailView extends AppCompatActivity implements SongDetailContract.View {

    Context context;
    LinearLayout main;
    ImageView imgArtwork;
    TextView txtArtistName, txtGenre, txtPrice;
    VideoView videoView;
    Track t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.song_detail);
        t = new Track();
        context = SongDetailView.this;

        main = (LinearLayout) findViewById(R.id.song_detail_main);
        imgArtwork = (ImageView) findViewById(R.id.imgArtworkDetail);
        txtArtistName = (TextView) findViewById(R.id.artist_name_detail);
        txtGenre = (TextView) findViewById(R.id.genre_detail);
        txtPrice = (TextView) findViewById(R.id.price_detail);
        videoView = (VideoView) findViewById(R.id.videoView);
        t = (Track) getIntent().getSerializableExtra("track");
        this.getSupportActionBar().setDisplayShowHomeEnabled(true);
        try {
            displayTrack((Track) getIntent().getSerializableExtra("track"));
        } catch (Exception e) {
            displayMessage("Problem while getting song info, Try again.");
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        @SuppressLint("StaticFieldLeak")
        class SaveTask extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {

                //creating a task
                Task1 task = new Task1();
                Log.e(t.getTrackName(), t.getArtistName());
                task.setTask(t.getTrackName());
                task.setDesc(t.getArtistName());
                //adding to database
                Log.e("Here!", task.getTask());
                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                        .taskDao()
                        .insert(t);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                finish();
                startActivity(new Intent(getApplicationContext(), SongListView.class));
                Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
            }
        }

        SaveTask st = new SaveTask();
        st.execute();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void displayMessage(String message) {
        Snackbar.make(main, message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void displayTrack(Track track) {

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(track.getTrackName());
        }

        String artworkUrl = track.getArtworkUrl100();
        Glide.with(context).load(artworkUrl).placeholder(R.drawable.ic_logo).into(imgArtwork);

        txtArtistName.setText(track.getArtistName());
        txtGenre.setText(track.getPrimaryGenreName());
        txtPrice.setText(String.format("₹ %s /-", track.getTrackPrice()));

        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        Uri video = Uri.parse(track.getPreviewUrl());
        videoView.setMediaController(mediaController);
        videoView.setVideoURI(video);
        videoView.start();
    }
}