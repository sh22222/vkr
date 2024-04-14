package com.example.vkr.mainScreen.Search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.vkr.R
import com.example.vkr.mainScreen.ForNews.AdapterForNews
import com.example.vkr.mainScreen.ForNews.News
import com.google.android.material.imageview.ShapeableImageView

class AdapterForGames (private val games : ArrayList<Game>) : RecyclerView.Adapter<AdapterForGames.ViewHolder>(){
    private lateinit var itemClickListener: onItemClickListener
    interface onItemClickListener{
        fun onItemClick(position:Int, gameItem : Game)
    }
    fun setOnItemClickListener(itemClickListener:onItemClickListener){
        this.itemClickListener = itemClickListener
    }
    class ViewHolder (view:View, listener: onItemClickListener) : RecyclerView.ViewHolder(view){
        val image = view.findViewById<ImageView>(R.id.sivGames)
        val tvHeadline = view.findViewById<TextView>(R.id.tvHeadlineGames)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_games,parent,false)
        return  ViewHolder(view, itemClickListener)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val game = games[position]
        holder.image.setImageResource(game.imageId)
        holder.tvHeadline.setText(game.name)
        holder.itemView.setOnClickListener {
            if(itemClickListener!=null){
                itemClickListener.onItemClick(position,game)
            }
        }
    }
    override fun getItemCount(): Int {
        return games.size
    }

}