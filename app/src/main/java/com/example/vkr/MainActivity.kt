package com.example.vkr

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
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

class MainActivity : AppCompatActivity() {
    fun showToast(text:String){
        val toast = Toast.makeText(applicationContext,text, Toast.LENGTH_LONG)
        toast.setGravity(Gravity.CENTER, 0,0)
        toast.show()
    }
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
        val btReg = findViewById<Button>(R.id.btReg)

        btEnter.setOnClickListener {
            val db = MainDataBase.getDataBase(applicationContext)
            val dao = db.getDao()
            var login : String = etLogin.text.toString()
            var pswd : String = etPswd.text.toString()
            var profiles = dao.findProfile(login,pswd)
            if (profiles.size==1) {
                showToast("Вход")
                var intent = Intent(this, MainScreen::class.java)
                var profile = Profile(profiles[0].login,profiles[0].email)
                intent.putExtra("profile",profile)
                startActivity(intent)
            }
            else{
                showToast("Попробуйте снова")
            }
        }
        btReg.setOnClickListener{
            var intent = Intent(this, Registration::class.java)
            startActivity(intent)
        }

    }
}