package com.example.real_netvorking_app.network.retrofit

import com.example.real_netvorking_app.network.retrofit.services.HomeServices
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHttp {
    private const val IS_TESTER = true
    private const val SERVER_DEVELOPMENT = "https://api.unsplash.com/"
    private const val SERVER_PRODUCTION = "https://api.unsplash.com/"

    private val retrofit: Retrofit =
        Retrofit.Builder().baseUrl(server()).addConverterFactory(GsonConverterFactory.create())
            .build()

    private fun server(): String {
        if (IS_TESTER)
            return SERVER_DEVELOPMENT
        return SERVER_PRODUCTION
    }

    val homeServices: HomeServices = retrofit.create(HomeServices::class.java)
}