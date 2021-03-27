package com.wednesdayApp.iTunesAPI.api

import com.wednesdayApp.iTunesAPI.api.model.TrackModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface APIService {
    @GET("search")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    fun getTracks(@Query("term") term: String?): Call<TrackModel?>?
}