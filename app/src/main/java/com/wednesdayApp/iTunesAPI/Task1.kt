package com.wednesdayApp.iTunesAPI

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
class Task1 : Serializable {
    /*
     * Getters and Setters
     * */
    @PrimaryKey(autoGenerate = true)
    var id = 0

    @ColumnInfo(name = "Song")
    var task: String? = null

    @ColumnInfo(name = "Description")
    var desc: String? = null
}