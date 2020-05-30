package com.example.covid19.data

import com.example.covid19.model.MCountry
import com.example.covid19.response.ResponseNews
import com.example.covid19.response.ResponseSummary
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("summary")
    fun getSummary(): Call<ResponseSummary>

    @GET("news")
    fun getNews(): Call<ResponseNews>
}