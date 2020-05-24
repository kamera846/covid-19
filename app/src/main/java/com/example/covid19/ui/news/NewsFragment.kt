package com.example.covid19.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.covid19.R
import com.example.covid19.adapter.NewsRecyclerAdapter

class NewsFragment : Fragment() {

    private lateinit var newsViewModel: NewsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        newsViewModel =
            ViewModelProviders.of(this).get(NewsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_news, container, false)
        val recyclerview: RecyclerView = root.findViewById(R.id.recyclerview)

        newsViewModel.data.observe(viewLifecycleOwner, Observer {
            recyclerview.apply {
                layoutManager = LinearLayoutManager(context!!)
                adapter = NewsRecyclerAdapter(context!!, it)

                layoutAnimation = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation)
                adapter?.notifyDataSetChanged()
                scheduleLayoutAnimation()
            }
        })
        return root
    }
}
