package com.example.covid19.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "https://i.pinimg.com/564x/08/78/53/0878535c72c7b2f6a7cc9b139cd673dd.jpg"
    }
    val text: LiveData<String> = _text
}