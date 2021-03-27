package com.wednesdayApp.iTunesAPI;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.wednesdayApp.iTunesAPI.api.model.Track;

@Database(entities = {Track.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract TaskDao taskDao();
}
