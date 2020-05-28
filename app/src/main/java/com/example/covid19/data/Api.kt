package com.example.covid19.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Api {

    fun apiRequest(): Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://api.covid19api.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}