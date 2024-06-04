package com.example.vkr.mainScreen.News

import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.vkr.R
import com.example.vkr.mainScreen.hideSystemUI
import com.google.android.material.imageview.ShapeableImageView
import com.google.firebase.storage.FirebaseStorage
import java.io.File

class SpecificNews : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_specific_news)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        hideSystemUI(window)
        var tvDes = findViewById<TextView>(R.id.tvDescriptionNews)
        var image = findViewById<ShapeableImageView>(R.id.sivNews)
        //перехват данных
        var news : News = intent.getSerializableExtra("newsItem") as News
        tvDes.setText(news.description)
        //image.setImageResource(news.imageId)
        val path = news.pathImage
        if (path != ""){
            var storageRef = FirebaseStorage.getInstance().reference.child(path)
            var suff = ""
            if (path.contains(".jpg")) {
                suff = "jpg"
            } else if (path.contains(".png")) {
                suff = "png"
            }
            var temp = File.createTempFile("tmpImage", suff)
            storageRef.getFile(temp).addOnSuccessListener {
                val bitmap = BitmapFactory.decodeFile(temp.absolutePath)
                image.setImageBitmap(bitmap)
            }
        }

        setSupportActionBar(findViewById(R.id.toolbarSpecificNews))
        //добавляем стрелку в toolbar
        var actionBar = getSupportActionBar()
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setTitle(news.headline)
        }
    }
    //возврат на главное активити
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home->{
                finish()
                true
            }
        }
        return super.onContextItemSelected(item)
    }
}