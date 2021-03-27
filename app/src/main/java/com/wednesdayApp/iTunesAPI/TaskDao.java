package com.wednesdayApp.iTunesAPI;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.wednesdayApp.iTunesAPI.api.model.Track;

import java.util.List;

@Dao
public interface TaskDao {

    @Query("SELECT * FROM Track")
    List<Track> getAll();

    @Insert
    void insert(Track task);


}