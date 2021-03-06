package com.example.covid19.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.covid19.R
import com.example.covid19.model.MNews
import com.example.covid19.ui.WebViewActivity

class NewsRecyclerAdapter(var mContext: Context, var mData: ArrayList<MNews>) :
    RecyclerView.Adapter<NewsRecyclerAdapter.ViewHolder>() {
    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var imgContent = v.findViewById<ImageView>(R.id.img_content)
        var title = v.findViewById<TextView>(R.id.title)
        var description = v.findViewById<TextView>(R.id.description)

        var itemTop = v.findViewById<CardView>(R.id.item_top)
        var itemCardView = v.findViewById<CardView>(R.id.item_card)

        var imgTop = v.findViewById<ImageView>(R.id.img_top)
        var titleTop = v.findViewById<TextView>(R.id.title_top)
        var descriptionTop = v.findViewById<TextView>(R.id.description_top)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
    )

    override fun getItemCount() = mData.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (position == 0) {
            holder.itemTop.visibility = View.VISIBLE
            holder.itemCardView.visibility = View.GONE

            Glide.with(mContext)
                .load(mData[position].urlToImage)
                .into(holder.imgTop)

            holder.titleTop.text = mData[position].title
            holder.descriptionTop.text = mData[position].description

            holder.itemView.setOnClickListener {
                mContext.startActivity(Intent(mContext, WebViewActivity::class.java)
                    .putExtra("url", mData[position].url))
            }

        } else {
            holder.itemTop.visibility = View.GONE
            holder.itemCardView.visibility = View.VISIBLE

            Glide.with(mContext)
                .load(mData[position].urlToImage)
                .into(holder.imgContent)

            holder.title.text = mData[position].title
            holder.description.text = mData[position].description

            holder.itemView.setOnClickListener {
                mContext.startActivity(Intent(mContext, WebViewActivity::class.java)
                    .putExtra("url", mData[position].url))
            }
        }
    }
}