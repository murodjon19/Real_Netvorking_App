package com.example.real_netvorking_app.network.retrofit.services

import com.example.real_netvorking_app.model.Home
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface HomeServices {

    companion object {
        private const val ACCESS_KEY = "8YjdV7JWcTFKKT3auGZbv9GjAAc_zGHJC-NE9k520To"
        const val client_id = "Client-ID"
    }

    @Headers("Authorization:$client_id $ACCESS_KEY")

    @GET("photos")
    fun getPhotos(@Query("page") page: Int, @Query("per_page") perPage: Int): Call<ArrayList<Home>>}
