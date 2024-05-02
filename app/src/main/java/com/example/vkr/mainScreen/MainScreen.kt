package com.example.vkr.mainScreen

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.vkr.R
import com.example.vkr.mainScreen.News.NewsFragment
import com.example.vkr.mainScreen.Profile.Profile
import com.example.vkr.mainScreen.Profile.ProfileFragment
import com.example.vkr.mainScreen.Search.SearchFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainScreen : AppCompatActivity() {
    //функция для смены фрагмента при переключении в нижнем меню
    private fun ChangeFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.frameLayout, fragment).commit()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main_screen)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //данные профиля
        var profile = intent.getSerializableExtra("profile") as Profile
        //изначально загружаем новости
        ChangeFragment(NewsFragment())
        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigation.setOnItemSelectedListener {
            when(it.itemId){
                R.id.news ->{
                    ChangeFragment(NewsFragment())
                    true
                }
                R.id.search ->{
                    ChangeFragment(SearchFragment())
                    true
                }
                R.id.wish ->{
                    true
                }
                R.id.profile ->{
                    val mbundle = Bundle()
                    mbundle.putSerializable("profile", profile)
                    var fragment = ProfileFragment()
                    fragment.arguments = mbundle
                    ChangeFragment(fragment)
                    true
                }
                else -> {false}
        }
        }

    }
}