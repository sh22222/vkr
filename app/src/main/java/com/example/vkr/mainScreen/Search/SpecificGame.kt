package com.example.vkr.mainScreen.Search

import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.vkr.R

class SpecificGame : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_specific_game)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val image = findViewById<ImageView>(R.id.sivSpecificGame)
        val tvGenre = findViewById<TextView>(R.id.tvGenre)
        val tvPlatform = findViewById<TextView>(R.id.tvPlatform)
        val tvDeveloper = findViewById<TextView>(R.id.tvDeveloper)
        val tvPublisher = findViewById<TextView>(R.id.tvPublisher)
        val tvDate = findViewById<TextView>(R.id.tvReleaseDate)
        val tvDescription = findViewById<TextView>(R.id.tvDescriptionGame)

        var game : Game = intent.getSerializableExtra("gamesItem") as Game
        //image.setImageResource(game.pathImage)
        var genre = ""
        for (i in 0..game.genre.size-1){
            if(i==game.genre.size-1){
                genre += game.genre[i]
            }
            else{
                genre += "${game.genre[i]}, "
            }
        }
        tvGenre.setText("Жанр: " + genre)
        var platform= ""
        for (i in 0..game.platform.size-1){
            if(i == game.platform.size-1){
                platform += game.platform[i]
            }
            else{
                platform += "${game.platform[i]}, "
            }
        }
        tvPlatform.setText("Платформа: " + platform)
        tvDeveloper.setText("Разработчик: " + game.developer)
        var publisher = ""
        for (i in 0..game.publisher.size-1){
            if(i == game.publisher.size-1){
                publisher += game.publisher[i]
            }
            else{
                publisher += "${game.publisher[i]}, "
            }
        }
        tvPublisher.setText("Издатель: " + publisher)
        tvDate.setText("Дата выпуска: " + game.releaseDate)
        tvDescription.setText(game.description)
        setSupportActionBar(findViewById(R.id.toolbarSpecificGame))
        var actionBar = getSupportActionBar()
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setTitle(game.name)
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