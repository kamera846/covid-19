package com.example.covid19.data

import com.example.covid19.model.MCountry
import com.example.covid19.model.MNews
import com.example.covid19.response.ResponseNews
import com.example.covid19.response.ResponseSummary
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("summary")
    fun getSummary(): Call<ResponseSummary>

    @GET("v2/top-headlines?country=us&category=health&apiKey=7588798c67d344dca4917eddff6658cc")
    fun getNews(): Call<ResponseNews>
}