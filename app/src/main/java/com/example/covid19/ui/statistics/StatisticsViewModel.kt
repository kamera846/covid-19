package com.example.covid19.ui.statistics

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.covid19.R
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineDataSet

class StatisticsViewModel : ViewModel() {

    private val _data = MutableLiveData<LineDataSet>().apply {
        //Part1
        val entries = ArrayList<Entry>()

        //Part2
        entries.add(Entry(1f, 10f))
        entries.add(Entry(2f, 2f))
        entries.add(Entry(3f, 7f))
        entries.add(Entry(4f, 20f))
        entries.add(Entry(5f, 16f))

        //Part3
        val vl = LineDataSet(entries, "My Type")

        //Part4
        vl.setDrawValues(false)
        vl.setDrawFilled(true)
        vl.lineWidth = 3f
        vl.fillColor = R.color.colorAccent
        vl.fillAlpha = R.color.colorRed

        value = vl
    }
    val data: LiveData<LineDataSet> = _data
}