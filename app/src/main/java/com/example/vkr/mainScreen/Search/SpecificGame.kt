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
import com.example.vkr.mainScreen.ForNews.News

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
        image.setImageResource(game.imageId)
        tvGenre.setText("Жанр: " + game.genre)
        tvPlatform.setText("Платформа: " + game.platform)
        tvDeveloper.setText("Разработчик: " + game.developer)
        tvPublisher.setText("Издатель: " + game.publisher)
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