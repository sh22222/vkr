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
import com.example.vkr.DataBase.MainDataBase
import com.example.vkr.mainScreen.MainScreen
import com.example.vkr.mainScreen.Profile.Profile
import com.example.vkr.mainScreen.Registration
import com.example.vkr.mainScreen.showCustomToast
import com.google.firebase.Firebase
import com.google.firebase.firestore.Filter
import com.google.firebase.firestore.firestore
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val etLogin = findViewById<EditText>(R.id.etLogin)
        val etPswd = findViewById<EditText>(R.id.etPswd)
        val btEnter = findViewById<Button>(R.id.btEnter)
        val btReg = findViewById<Button>(R.id.btReg)

        btEnter.setOnClickListener {
            var login : String = etLogin.text.toString()
            var pswd : String = etPswd.text.toString()
            if (login != "" && pswd != "") {
                val db = Firebase.firestore.collection("profile")
                db.where(Filter.and(
                    Filter.equalTo("login", login),
                    Filter.equalTo("password", pswd)
                )).get().addOnSuccessListener { document->
                    if(document.size() == 1){
                        var l = ""
                        var e = ""
                        for (d in document){
                            l = d.data.get("login").toString()
                            e = d.data.get("email").toString()
                        }
                        Toast(this).showCustomToast("Вход", this)
                        var intent = Intent(this, MainScreen::class.java)
                        var profile = Profile(l,e)
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