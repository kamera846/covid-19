package com.example.covid19.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.covid19.R
import com.example.covid19.model.MCountries

class CountriesRecyclerAdapter(var mContext: Context, var mData: ArrayList<MCountries>) :
    RecyclerView.Adapter<CountriesRecyclerAdapter.ViewHolder>() {
    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var imgCountries = v.findViewById<ImageView>(R.id.img_countries)
        var tvName = v.findViewById<TextView>(R.id.tv_name)
        var tvPositive = v.findViewById<TextView>(R.id.tv_positive)
        var tvDeath = v.findViewById<TextView>(R.id.tv_death)
        var tvRecovered = v.findViewById<TextView>(R.id.tv_recovered)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_countries, parent, false)
    )

    override fun getItemCount() = mData.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//            Glide.with(mContext)
//                .load(mData[position].flag)
//                .into(holder.imgCountries)

        holder.tvName.text = mData[position].name
        holder.tvPositive.text = mData[position].positive.toString()
        holder.tvDeath.text = mData[position].death.toString()
        holder.tvRecovered.text = mData[position].recovered.toString()
    }
}