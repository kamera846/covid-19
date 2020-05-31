package com.example.covid19.ui.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.covid19.model.MNews

class NewsViewModel : ViewModel() {

    private val _data = MutableLiveData<ArrayList<MNews>>().apply {

    }
    val data: LiveData<ArrayList<MNews>> = _data
}