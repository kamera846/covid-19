package com.example.covid19.ui.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.covid19.model.MNews

class NewsViewModel : ViewModel() {

    private val _data = MutableLiveData<ArrayList<MNews>>().apply {
        var data: MNews
        var list = ArrayList<MNews>()
        for (i in 0 until 10) {
            data = MNews(
                "https://i.pinimg.com/564x/08/78/53/0878535c72c7b2f6a7cc9b139cd673dd.jpg",
                "Virus Corona atau covid-19",
                "Virus ini adalah virus blabla blabl ab b bb ablblbalb blb labl blablbla lb bablblb ablbla bblblbasbd lbla bl blba"
            )

            list.add(i, data)
        }
        value = list
    }
    val data: LiveData<ArrayList<MNews>> = _data
}