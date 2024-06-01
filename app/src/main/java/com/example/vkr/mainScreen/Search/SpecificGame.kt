package com.example.vkr.mainScreen.Search

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.vkr.R
import com.example.vkr.mainScreen.Profile.Profile
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import java.io.File

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

        val profile = intent.getSerializableExtra("profile") as Profile
        val game : Game = intent.getSerializableExtra("gamesItem") as Game
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

        val path = game.pathImage
        val storageRef = FirebaseStorage.getInstance().reference.child("$path")
        var suff = ""
        if (path.contains(".jpg")){
            suff = "jpg"
        }
        else if (path.contains(".png")){
            suff = "png"
        }
        val temp = File.createTempFile("tmpImage", suff)

        storageRef.getFile(temp).addOnSuccessListener {
            val bitmap = BitmapFactory.decodeFile(temp.absolutePath)
            val width = bitmap.width.toDouble()
            val height = bitmap.height.toDouble()
            val pr = width/height
            val w = 300
            val h = (w/pr).toInt()
            val bitmapMutable = Bitmap.createScaledBitmap(bitmap, w, h, true)
            image.setImageBitmap(bitmapMutable)
        }


        setSupportActionBar(findViewById(R.id.toolbarSpecificGame))
        val actionBar = getSupportActionBar()
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setTitle(game.name)
        }

        val db = FirebaseFirestore.getInstance()
        val btAddWishlist = findViewById<Button>(R.id.btAddWishlist)
        var gameInWishlist = false
        for(i in profile.getList()){
            if(i.compareTo(game.idGame)==0){
                gameInWishlist = true
            }
        }
        if(gameInWishlist){
            btAddWishlist.setText("Удалить из избранного")
        }
        else {
            btAddWishlist.setText("Добавить в избранное")
        }

        btAddWishlist.setOnClickListener {
            gameInWishlist = false
            for(i in profile.getList()){
                if(i.compareTo(game.idGame)==0){
                    gameInWishlist = true
                }
            }
            if(gameInWishlist){
                db.collection("profile").document(profile.getId())
                    .update("listGames", FieldValue.arrayRemove(game.idGame))
                profile.deleteGame(game.idGame.toLong())
                btAddWishlist.setText("Добавить в избранное")
            }
            else {
                db.collection("profile").document(profile.getId())
                    .update("listGames", FieldValue.arrayUnion(game.idGame))
                profile.addGame(game.idGame.toLong())
                btAddWishlist.setText("Удалить из избранного")
            }
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