package com.example.vkr

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import com.example.vkr.mainScreen.MainScreen
import com.example.vkr.mainScreen.Profile.Profile
import com.example.vkr.mainScreen.Registration
import com.example.vkr.mainScreen.md5
import com.example.vkr.mainScreen.showCustomToast
import com.google.firebase.Firebase
import com.google.firebase.firestore.Filter
import com.google.firebase.firestore.firestore
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { view, windowInsets ->
            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemGestures())
            // Apply the insets as padding to the view. Here, set all the dimensions
            // as appropriate to your layout. You can also update the view's margin if
            // more appropriate.
            view.updatePadding(insets.left, insets.top, insets.right, insets.bottom)

            // Return CONSUMED if you don't want the window insets to keep passing down
            // to descendant views.
            WindowInsetsCompat.CONSUMED
        }
        val etLogin = findViewById<EditText>(R.id.etLogin)
        val etPswd = findViewById<EditText>(R.id.etPswd)
        val btEnter = findViewById<Button>(R.id.btEnter)
        val btReg = findViewById<Button>(R.id.btReg)

        btEnter.setOnClickListener {
            var login : String = etLogin.text.toString()
            var pswd : String = etPswd.text.toString().md5()
            if (login != "" && pswd != "") {
                val db = Firebase.firestore.collection("profile")
                db.where(Filter.and(
                    Filter.equalTo("login", login),
                    Filter.equalTo("password", pswd)
                )).get().addOnSuccessListener { document->
                    if(document.size() == 1){
                        var l = ""
                        var e = ""
                        var id = ""
                        var list = ArrayList<Long>()
                        for (d in document){
                            l = d.data.get("login").toString()
                            e = d.data.get("email").toString()
                            id = d.id
                            list = d.data.get("listGames") as ArrayList<Long>
                        }
                        Toast(this).showCustomToast("Вход", this)
                        var intent = Intent(this, MainScreen::class.java)
                        var profile = Profile(l,e,id,list)
                        intent.putExtra("profile", profile)
                        startActivity(intent)
                    }
                    else{
                        Toast(this).showCustomToast("Попробуйте снова", this)
                    }

                }
            }
        }
        btReg.setOnClickListener{
            var intent = Intent(this, Registration::class.java)
            startActivity(intent)
        }

    }
}