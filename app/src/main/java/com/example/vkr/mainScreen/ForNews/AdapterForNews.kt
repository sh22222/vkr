package com.example.vkr.mainScreen.ForNews

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.vkr.R

class AdapterForNews (private val listNews: List<News>):RecyclerView.Adapter<AdapterForNews.ViewHolder>(){
    class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        val image = view.findViewById<ImageView>(R.id.shapeableImageView)
        val tvHeadLine = view.findViewById<TextView>(R.id.tvHeadline)
        val tvDate = view.findViewById<TextView>(R.id.tvDatePublish)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val newsItem = listNews[position]
        holder.image.setImageResource(newsItem.imageId)
        holder.tvHeadLine.setText(newsItem.headline)
        holder.tvDate.setText(newsItem.date)

    }

    override fun getItemCount(): Int {
        return listNews.size
    }
}