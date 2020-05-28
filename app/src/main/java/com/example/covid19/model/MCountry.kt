package com.example.covid19.model

data class MCountry (
    var flag: String,
    var Country: String,
    var TotalConfirmed: Int,
    var TotalDeaths: Int,
    var TotalRecovered: Int,
    var Date: String
)