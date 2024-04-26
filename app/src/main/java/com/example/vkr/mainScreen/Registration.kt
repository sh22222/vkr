package com.example.vkr.mainScreen

import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.vkr.DataBase.MainDataBase
import com.example.vkr.DataBase.Profile
import com.example.vkr.R

class Registration : AppCompatActivity() {
    fun showToast(text:String){
        val toast = Toast.makeText(applicationContext,text,Toast.LENGTH_LONG)
        toast.setGravity(Gravity.CENTER, 0,0)
        toast.show()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registration)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setSupportActionBar(findViewById(R.id.toolbarReg))
        var actionBar = getSupportActionBar()
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setTitle("Регистрация")
        }

        val btReg = findViewById<Button>(R.id.btReg)
        val etLogin = findViewById<EditText>(R.id.etLoginReg)
        val etEmail = findViewById<EditText>(R.id.etEmailReg)
        val etNewPass = findViewById<EditText>(R.id.etNewPasswordReg)
        val etOldPass = findViewById<EditText>(R.id.etOldPasswordReg)
        btReg.setOnClickListener {
            var login = etLogin.text.toString()
            var email = etEmail.text.toString()
            var newPass = etNewPass.text.toString()
            var oldPass = etOldPass.text.toString()
            var db = MainDataBase.getDataBase(this)
            var dao = db.getDao()
            var result = dao.findProfileReg(login,email)
            db.close()
            if (result.size == 0)
            {
                dao.insertProfile(Profile(login,email,newPass,""))
                showToast("Вы зарегистрированы")
            }
            else{
                showToast("Ошибка при регистрации")
            }
        }
    }
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