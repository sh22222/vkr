package com.example.vkr

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.vkr.mainScreen.MainScreen

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
        val loginSQL = "vlad"
        val pswdSQL = "123456"

        val etLogin = findViewById<EditText>(R.id.etLogin)
        val etPswd = findViewById<EditText>(R.id.etPswd)
        val btEnter = findViewById<Button>(R.id.btEnter)

        btEnter.setOnClickListener {
            var login : String = etLogin.text.toString()
            var pswd : String = etPswd.text.toString()
            if (login.compareTo(loginSQL) == 0 && pswd.compareTo(pswdSQL) == 0) {
                etLogin.setText("Hello")
                var intent = Intent(this, MainScreen::class.java)
                startActivity(intent)
            }
            else{
                etLogin.setText("No")
            }
        }
    }
}