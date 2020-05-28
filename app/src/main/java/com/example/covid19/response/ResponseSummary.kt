package com.example.covid19.response

import com.example.covid19.model.MCountry
import com.example.covid19.model.MGlobal

data class ResponseSummary (
    var Global: MGlobal,
    var Countries: ArrayList<MCountry>
)