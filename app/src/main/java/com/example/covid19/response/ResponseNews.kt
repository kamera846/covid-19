package com.example.covid19.response

import com.example.covid19.model.MNews

data class ResponseNews(
    var status: String,
    var totalResults: Int,
    var articles: ArrayList<MNews>
)