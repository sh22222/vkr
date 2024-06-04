package com.example.vkr.mainScreen

import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.vkr.R
import com.google.firebase.Firebase
import com.google.firebase.firestore.Filter
import com.google.firebase.firestore.firestore

class Registration : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registration)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        hideSystemUI(window)
        setSupportActionBar(findViewById(R.id.toolbarReg))
        val actionBar = getSupportActionBar()
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setTitle("Регистрация")
        }

        val btReg = findViewById<Button>(R.id.btReg)
        val etLogin = findViewById<EditText>(R.id.etLoginReg)
        val etEmail = findViewById<EditText>(R.id.etEmailReg)
        val etNewPass = findViewById<EditText>(R.id.etNewPasswordReg)
        val etOldPass = findViewById<EditText>(R.id.etOldPasswordReg)
        btReg.setOnClickListener {
            val login = etLogin.text.toString()
            val email = etEmail.text.toString()
            val pass1 = etNewPass.text.toString()
            val pass2 = etOldPass.text.toString()
            if (
                pass1.compareTo(pass2) == 0 &&
                login != "" &&
                email != "" &&
                pass1 != "" &&
                pass2 != ""
            ) {
                val db = Firebase.firestore.collection("profile")
                db.where(
                    Filter.or(
                        Filter.equalTo("login", login),
                        Filter.equalTo("email", email)
                    )
                )
                    .get()
                    .addOnSuccessListener { documents ->
                        if (documents.size() != 0) {
                            Toast(this).showCustomToast("Ошибка при регистрации", this)
                        } else {
                            val profile = hashMapOf(
                                "login" to login,
                                "email" to email,
                                "password" to pass1,
                                "listGames" to ArrayList<String>()
                            )
                            db.document().set(profile)
                            Toast(this).showCustomToast("Вы зарегистрированы", this)
                            //finish()
                        }
//                for(document in documents){
//                    var name = document.data.get("login").toString()
//                    var email = document.data.get("email").toString()
//                    Toast(this).showCustomToast("$name,$email",this)
//                }

                    }.addOnFailureListener {
                        Toast(this).showCustomToast("Ошибка соединения", this)
                    }
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onContextItemSelected(item)
    }
}