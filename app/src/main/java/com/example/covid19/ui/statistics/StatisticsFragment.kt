package com.example.covid19.ui.statistics

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.covid19.R
import com.example.covid19.adapter.CountriesRecyclerAdapter
import com.example.covid19.model.MCountries
import com.example.covid19.ui.CustomMarker
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet

class StatisticsFragment : Fragment() {

    private lateinit var statisticsViewModel: StatisticsViewModel
    private lateinit var lineChart: LineChart
    private lateinit var recyclerview: RecyclerView
    private lateinit var lnrSearch: LinearLayout
    private lateinit var lnrClose: LinearLayout
    private lateinit var etSearch: EditText
    private lateinit var btnSearch: ImageView
    private lateinit var btnClose: ImageView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        statisticsViewModel =
            ViewModelProviders.of(this).get(StatisticsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_statistics, container, false)

        lineChart = root.findViewById(R.id.lineChart)
        recyclerview = root.findViewById(R.id.rv_countries)
        lnrSearch = root.findViewById(R.id.lnr_search)
        lnrClose = root.findViewById(R.id.lnr_close)
        etSearch = root.findViewById(R.id.et_search)
        btnSearch = root.findViewById(R.id.btn_search)
        btnClose = root.findViewById(R.id.btn_close)

        btnSearch.setOnClickListener {
            lnrClose.visibility = View.VISIBLE
            lnrSearch.visibility = View.GONE
            etSearch.requestFocus()
        }

        btnClose.setOnClickListener {
            lnrClose.visibility = View.GONE
            lnrSearch.visibility = View.VISIBLE
        }

        statisticsViewModel.dataChart.observe(viewLifecycleOwner, Observer {
            setDataChart(it)
        })

        statisticsViewModel.dataList.observe(viewLifecycleOwner, Observer {
            setDataList(it)
        })

        return root
    }

    private fun setDataChart(data: LineDataSet) {
        //Part5
        lineChart.xAxis.labelRotationAngle = 0f

        //Part6
        lineChart.data = LineData(data)
        lineChart.data.setValueTextColor(R.color.colorWhite)

        //Part7
        lineChart.axisRight.isEnabled = false
//            lineChart.xAxis.axisMaximum = j+0.1f

        //Part8
        lineChart.setTouchEnabled(true)
        lineChart.setPinchZoom(true)

        //Part9
        lineChart.description.text = "People"
        lineChart.setNoDataText("Something went wrong")

        //Part10
        lineChart.animateY(2000,Easing.EaseInBounce)

        //Part11
        val markerView = CustomMarker(context!!, R.layout.marker_view)
        lineChart.marker = markerView
    }

    private fun setDataList(data: ArrayList<MCountries>) {
        recyclerview.apply {
            layoutManager = LinearLayoutManager(context!!)
            adapter = CountriesRecyclerAdapter(context!!, data)

            layoutAnimation = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation)
            adapter?.notifyDataSetChanged()
            scheduleLayoutAnimation()
        }
    }
}
