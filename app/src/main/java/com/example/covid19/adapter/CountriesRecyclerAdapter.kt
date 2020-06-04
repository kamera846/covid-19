package com.example.covid19.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.covid19.R
import com.example.covid19.model.MCountry
import com.example.covid19.ui.CountryDetailActivity
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class CountriesRecyclerAdapter(var mContext: Context, var mData: ArrayList<MCountry>) :
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
        val formatter = NumberFormat.getNumberInstance(Locale.ENGLISH)

        holder.itemView.setOnClickListener {
            mContext.startActivity(Intent(mContext, CountryDetailActivity::class.java)
                .putExtra("itemPosition", position))
        }

        holder.tvName.text = "${position+1}. ${mData[position].Country}"
        holder.tvPositive.text = formatter.format(mData[position].TotalConfirmed)
        holder.tvDeath.text = formatter.format(mData[position].TotalDeaths)
        holder.tvRecovered.text = formatter.format(mData[position].TotalRecovered)

    }
}