package com.example.covid19.ui

import android.animation.ValueAnimator
import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.core.animation.doOnEnd
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.covid19.R
import com.example.covid19.adapter.CountriesRecyclerAdapter
import com.example.covid19.data.Api
import com.example.covid19.data.ApiService
import com.example.covid19.model.MCountry
import com.example.covid19.response.ResponseSummary
import kotlinx.android.synthetic.main.activity_country_detail.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.NumberFormat
import java.util.*
import kotlin.Comparator
import kotlin.collections.ArrayList

class CountryDetailActivity : AppCompatActivity() {

    private lateinit var mLoading: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country_detail)

        mLoading = ProgressDialog(this@CountryDetailActivity)
        mLoading.setCancelable(false)
        mLoading.setMessage("Loading ...")

        if(intent != null){
            setData(intent.getIntExtra("itemPosition", 0))
        }

        img_back.setOnClickListener {
            finish()
        }

    }

    private fun setData(position: Int){
        mLoading.show()
        val list = ArrayList<MCountry>()

        val service = Api().apiRequest().create(ApiService::class.java)
        service.getSummary().enqueue(object: Callback<ResponseSummary>{
            override fun onFailure(call: Call<ResponseSummary>, t: Throwable) {
                mLoading.dismiss()
                Toast.makeText(this@CountryDetailActivity, "Something went wrong", Toast.LENGTH_LONG)
            }

            override fun onResponse(
                call: Call<ResponseSummary>,
                response: Response<ResponseSummary>
            ) {
                if (response.body() != null) {
                    val data = response.body()!!.Countries
                    for (i in 0 until data.size) {
                        if (data[i].TotalConfirmed != 0) {
                            list.add(data[i])
                        }
                    }

                    list.sortWith(Comparator { lhs, rhs ->
                        rhs.TotalConfirmed.compareTo(lhs.TotalConfirmed)
                    })

                    tv_country.text = list[position].Country

                    startAnimationTotal(list, position)

                    mLoading.dismiss()
                }
            }

        })
    }

    private fun startAnimationTotal(list: ArrayList<MCountry>, position: Int){
        val countPositive = list[position].TotalConfirmed
        val countDeaths = list[position].TotalDeaths
        val countRecovered = list[position].TotalRecovered

        val formatter = NumberFormat.getNumberInstance(Locale.ENGLISH)

        val animatorPositive = ValueAnimator.ofInt(0, countPositive)
        animatorPositive.duration = 2000
        animatorPositive.addUpdateListener { animation -> tv_positive.text = animation.animatedValue.toString() }
        animatorPositive.doOnEnd {
            tv_positive.text = formatter.format(countPositive)
        }
        animatorPositive.start()
        val animatorDeath = ValueAnimator.ofInt(0, countDeaths)
        animatorDeath.duration = 2000
        animatorDeath.addUpdateListener { animation -> tv_death.text = animation.animatedValue.toString() }
        animatorDeath.doOnEnd {
            tv_death.text = formatter.format(countDeaths)
        }
        animatorDeath.start()
        val animatorRecovered = ValueAnimator.ofInt(0, countRecovered)
        animatorRecovered.duration = 2000
        animatorRecovered.addUpdateListener { animation -> tv_recovered.text = animation.animatedValue.toString() }
        animatorRecovered.doOnEnd {
            tv_recovered.text = formatter.format(countRecovered)
        }
        animatorRecovered.start()
    }
}
