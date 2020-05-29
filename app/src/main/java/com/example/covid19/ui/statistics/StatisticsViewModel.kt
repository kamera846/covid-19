package com.example.covid19.ui.statistics

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.covid19.data.Api
import com.example.covid19.data.ApiService
import com.example.covid19.model.MCountry
import com.example.covid19.response.ResponseSummary
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineDataSet
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

    private val _dataList = MutableLiveData<ArrayList<MCountry>>().apply {
//        val list = ArrayList<MCountry>()
//
//        val service = Api().apiRequest().create(ApiService::class.java)
//        service.getSummary().enqueue(object: Callback<ResponseSummary>{
//            override fun onFailure(call: Call<ResponseSummary>, t: Throwable) {
//                value = list
//            }
//
//            override fun onResponse(
//                call: Call<ResponseSummary>,
//                response: Response<ResponseSummary>
//            ) {
//                if (response.body() != null){
//                    val data = response.body()!!.Countries
//                    for(i in 0 until data.size){
//                        if(data[i].TotalConfirmed != 0){
//                            list.add(data[i])
//                        }
//                    }
//                    Log.d("LOGLOGAN", list.toString())
//                    value = list
//                }
//            }
//
//        })

    }

//    val dataList: LiveData<ArrayList<MCountry>> = _dataList
    val dataChart: LiveData<LineDataSet> = _dataChart
}