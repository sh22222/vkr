package com.example.vkr.mainScreen.News

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.vkr.R

class AdapterForNews (private val listNews: List<News>):RecyclerView.Adapter<AdapterForNews.ViewHolder>(){
    private lateinit var itemClickListener: onItemClickListener
    interface onItemClickListener{
        fun onItemClick(position:Int, newsItem : News)
    }
    fun setOnClickListener(itemClickListener: onItemClickListener){
        this.itemClickListener = itemClickListener
    }
    class ViewHolder(view: View, listener: onItemClickListener):RecyclerView.ViewHolder(view){
        val image = view.findViewById<ImageView>(R.id.shapeableImageView)
        val tvHeadLine = view.findViewById<TextView>(R.id.tvHeadline)
        val tvDate = view.findViewById<TextView>(R.id.tvDatePublish)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news,parent,false)
        return ViewHolder(view,itemClickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val newsItem = listNews[position]
        holder.image.setImageResource(newsItem.imageId)
        holder.tvHeadLine.setText(newsItem.headline)
        holder.tvDate.setText(newsItem.date)
        holder.itemView.setOnClickListener {
            if(itemClickListener != null){
                itemClickListener.onItemClick(position, newsItem)
            }
        }
    }
    override fun getItemCount(): Int {
        return listNews.size
    }
}