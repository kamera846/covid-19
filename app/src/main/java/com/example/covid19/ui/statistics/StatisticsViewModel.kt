package com.example.covid19.ui.statistics

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.covid19.R
import com.example.covid19.model.MCountries
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineDataSet

class StatisticsViewModel : ViewModel() {

    private val _dataChart = MutableLiveData<LineDataSet>().apply {
        //Part1
        val entries = ArrayList<Entry>()

        //Part2
        entries.add(Entry(1f, 10f))
        entries.add(Entry(2f, 2f))
        entries.add(Entry(3f, 7f))
        entries.add(Entry(4f, 20f))
        entries.add(Entry(5f, 16f))
        entries.add(Entry(6f, 15f))
        entries.add(Entry(7f, 8f))
        entries.add(Entry(8f, 10f))
        entries.add(Entry(9f, 20f))
        entries.add(Entry(10f, 16f))

        //Part3
        val vl = LineDataSet(entries, "In the world")

        //Part4
        vl.setDrawValues(false)
        vl.setDrawFilled(true)
        vl.lineWidth = 3f

        value = vl
    }

    private val _dataList = MutableLiveData<ArrayList<MCountries>>().apply {
        val list = ArrayList<MCountries>()

        for(i in 0 until 10){
            list.add(i, MCountries(
                "",
                "Region",
                0,
                0,
                0
            ))
        }

        value = list

    }

    val dataList: LiveData<ArrayList<MCountries>> = _dataList
    val dataChart: LiveData<LineDataSet> = _dataChart
}