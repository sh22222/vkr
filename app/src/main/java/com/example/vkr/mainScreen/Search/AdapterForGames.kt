package com.example.vkr.mainScreen.Search

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.vkr.R
import com.google.firebase.storage.FirebaseStorage
import java.io.File

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
        var path = game.pathImage
        if (path != "") {
            var storageRef = FirebaseStorage.getInstance().reference.child("$path")
            var suff = ""
            if (path.contains(".jpg")) {
                suff = "jpg"
            } else if (path.contains(".png")) {
                suff = "png"
            }
            var temp = File.createTempFile("tmpImage", suff)

            storageRef.getFile(temp).addOnSuccessListener {
                val bitmap = BitmapFactory.decodeFile(temp.absolutePath)
                var width = bitmap.width.toDouble()
                var height = bitmap.height.toDouble()
                var pr = width/height
                var w = 300
                var h = (w/pr).toInt()
                var bitmapMutable = Bitmap.createScaledBitmap(bitmap, w, h, true)
                holder.image.setImageBitmap(bitmapMutable)
            }
        }
        //holder.image.setImageResource(game.pathImage)
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