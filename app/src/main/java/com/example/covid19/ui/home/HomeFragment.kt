package com.example.covid19.ui.home

import android.animation.ValueAnimator
import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.animation.doOnEnd
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.covid19.R
import com.example.covid19.data.Api
import com.example.covid19.data.ApiService
import com.example.covid19.model.MCountry
import com.example.covid19.response.ResponseSummary
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Response
import java.io.Console
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*


class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var tvPositive: TextView
    private lateinit var tvDeath: TextView
    private lateinit var tvRecovered: TextView
    private lateinit var imgVirus: ImageView

    private lateinit var mLoading: ProgressDialog

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        mLoading = ProgressDialog(context!!)
        mLoading.setCancelable(false)
        mLoading.setMessage("Loading ...")

        imgVirus = root.findViewById(R.id.img_virus)
        tvPositive = root.findViewById(R.id.tv_positive)
        tvDeath = root.findViewById(R.id.tv_death)
        tvRecovered = root.findViewById(R.id.tv_recovered)

        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            Glide.with(root)
                .load(it)
                .into(img_view)
        })

        startTextAnimation()

        return root
    }

    private fun startTextAnimation(){
        mLoading.show()
        val service = Api().apiRequest().create(ApiService::class.java)
        service.getSummary().enqueue(object: retrofit2.Callback<ResponseSummary> {
            override fun onFailure(call: Call<ResponseSummary>, t: Throwable) {
                mLoading.dismiss()
                Toast.makeText(context!!, "Something went wrong", Toast.LENGTH_LONG)
            }

            override fun onResponse(call: Call<ResponseSummary>, response: Response<ResponseSummary>) {
                if(response.body() != null){
                    val data = response.body()!!.Global

                    val countPositive = data.TotalConfirmed
                    val countDeaths = data.TotalDeaths
                    val countRecovered = data.TotalRecovered

                    val formatter = NumberFormat.getNumberInstance(Locale.ENGLISH)

                    val animatorPositive = ValueAnimator.ofInt(0, countPositive)
                    animatorPositive.duration = 2000
                    animatorPositive.addUpdateListener { animation -> tvPositive.text = animation.animatedValue.toString() }
                    animatorPositive.doOnEnd {
                        tvPositive.text = formatter.format(countPositive)
                    }
                    animatorPositive.start()
                    val animatorDeath = ValueAnimator.ofInt(0, countDeaths)
                    animatorDeath.duration = 2000
                    animatorDeath.addUpdateListener { animation -> tvDeath.text = animation.animatedValue.toString() }
                    animatorDeath.doOnEnd {
                        tvDeath.text = formatter.format(countDeaths)
                    }
                    animatorDeath.start()
                    val animatorRecovered = ValueAnimator.ofInt(0, countRecovered)
                    animatorRecovered.duration = 2000
                    animatorRecovered.addUpdateListener { animation -> tvRecovered.text = animation.animatedValue.toString() }
                    animatorRecovered.doOnEnd {
                        tvRecovered.text = formatter.format(countRecovered)
                    }
                    animatorRecovered.start()

                    mLoading.dismiss()
                    startImageAnimation()
                }
            }

        })
    }

    private fun startImageAnimation(){
        imgVirus.animate().apply {
            duration = 1000

            rotationXBy(360f)
            rotationYBy(360f)
        }.withEndAction {
            imgVirus.animate().apply {
                duration = 1000

                rotationXBy(-360f)
                rotationYBy(-360f)
            }
        }
    }

}
