package com.example.covid19.ui.news

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.covid19.R
import com.example.covid19.adapter.NewsRecyclerAdapter
import com.example.covid19.data.Api
import com.example.covid19.data.ApiService
import com.example.covid19.model.MNews
import com.example.covid19.response.ResponseNews
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsFragment : Fragment() {

    private lateinit var newsViewModel: NewsViewModel
    private lateinit var recyclerview: RecyclerView

    private lateinit var mLoading: ProgressDialog

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        newsViewModel =
            ViewModelProviders.of(this).get(NewsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_news, container, false)
        recyclerview = root.findViewById(R.id.recyclerview)

        mLoading = ProgressDialog(context!!)
        mLoading.setCancelable(false)
        mLoading.setMessage("Loading ...")

        getData()
        return root
    }

    private fun getData(){
        mLoading.show()
        val service = Api().baseUrlNews().create(ApiService::class.java)
        service.getNews().enqueue(object: Callback<ResponseNews>{
            override fun onFailure(call: Call<ResponseNews>, t: Throwable) {
                mLoading.dismiss()
                Toast.makeText(context!!, "Something went wrong", Toast.LENGTH_LONG)
            }

            override fun onResponse(call: Call<ResponseNews>, response: Response<ResponseNews>) {
                mLoading.dismiss()
                if(response.body() != null){
                    if(response.body()!!.status == "ok"){
                        recyclerview.apply {
                            layoutManager = LinearLayoutManager(context!!)
                            adapter = NewsRecyclerAdapter(context!!, response.body()!!.articles)

                            layoutAnimation = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation)
                            adapter?.notifyDataSetChanged()
                            scheduleLayoutAnimation()
                        }
                    }
                }
            }

        })
    }
}
