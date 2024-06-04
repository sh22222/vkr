package com.example.vkr.mainScreen.Search

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.vkr.R
import com.example.vkr.mainScreen.Profile.Profile
import com.example.vkr.mainScreen.hideSystemUI
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import java.io.File

class SpecificGame : AppCompatActivity() {
    private lateinit var profile: Profile
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_specific_game)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        hideSystemUI(window)
        val image = findViewById<ImageView>(R.id.sivSpecificGame)
        val tvGenre = findViewById<TextView>(R.id.tvGenre)
        val tvPlatform = findViewById<TextView>(R.id.tvPlatform)
        val tvDeveloper = findViewById<TextView>(R.id.tvDeveloper)
        val tvPublisher = findViewById<TextView>(R.id.tvPublisher)
        val tvDate = findViewById<TextView>(R.id.tvReleaseDate)
        val tvDescription = findViewById<TextView>(R.id.tvDescriptionGame)

        profile = intent.getSerializableExtra("profile") as Profile
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
        //опрделяем, будет ли игра удаляться или добавляться в избранное
        val db = FirebaseFirestore.getInstance()
        val btAddWishlist = findViewById<Button>(R.id.btAddWishlist)
        var gameInWishlist = false
        var wishlist = ArrayList<Long>()
        db.collection("profile").document(profile.getId())
            .get().addOnSuccessListener {document->
            if(document.exists()){
                wishlist = document.data?.get("listGames") as ArrayList<Long>
            }
                for(i in wishlist){
                    if(i.compareTo(game.idGame)==0){
                        gameInWishlist = true
                    }
                }
                if(gameInWishlist){
                    gameInWishlist = true
                    btAddWishlist.setText("Удалить из избранного")
                }
                else {
                    gameInWishlist = false
                    btAddWishlist.setText("Добавить в избранное")
                }
        }
        btAddWishlist.setOnClickListener {
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
        //определяем рейтинг
        val ratingBar = findViewById<RatingBar>(R.id.ratingBar)
        ratingBar.setOnRatingBarChangeListener { rBar, rating, fromUser ->
            addRating(game.idGame.toString(),rBar.rating)
        }
        countRating(game.idGame.toString())
    }
    fun countRating(game: String){
        val db = FirebaseFirestore.getInstance()
        val ratingBar = findViewById<RatingBar>(R.id.ratingBar)
        val ratingText = findViewById<TextView>(R.id.ratingText)
        val voted = findViewById<TextView>(R.id.voted)
        db.collection("game").document(game)
            .collection("rating").get().addOnSuccessListener { rating->
                var sumRating = 0.0
                var n = 0
                if (rating != null){
                    for(r in rating){
                        sumRating += r.data.get("rating").toString().toFloat()
                        n++
                        if (r.data.get("login").toString().compareTo(profile.getLogin())==0){
                            ratingBar.rating = r.data.get("rating").toString().toFloat()
                        }
                    }
                    if(n!= 0){
                        ratingText.setText("Текущий рейтинг: ${(sumRating/n).toFloat()}")
                    }
                    else ratingText.setText("Текущий рейтинг: 0")
                    voted.setText("Проголосовало: $n")
                }
            }
    }
    fun addRating( game: String, rating: Float){
        val db = FirebaseFirestore.getInstance()
        db.collection("game").document(game)
            .collection("rating").document(profile.getLogin())
            .set(Rating(profile.getLogin(),rating.toDouble()))
        countRating(game)
    }
    //возврат на главное активити
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home->{
                setResult(Activity.RESULT_OK)
                finish()
            }
        }
        return super.onContextItemSelected(item)
    }
}