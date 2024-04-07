package com.example.vkr.mainScreen.ForNews

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.vkr.R

class SpecificNews : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_specific_news)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setSupportActionBar(findViewById(R.id.toolbarSpecificNews))
        //добавляем стрелку в toolbar
        var actionBar = getSupportActionBar()
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home->{
                true
            }
        }

        return super.onContextItemSelected(item)
    }
}