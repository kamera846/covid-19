package com.example.covid19.model

data class MNews(
    var source: Source,
    var author: String,
    var title: String,
    var description: String,
    var url: String,
    var urlToImage: String,
    var publishedAt: String,
    var content: String
){
    data class Source(
        var name: String
    )
}