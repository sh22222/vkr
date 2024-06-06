package com.example.vkr.mainScreen

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.Fragment
import com.example.vkr.R
import com.example.vkr.mainScreen.News.NewsFragment
import com.example.vkr.mainScreen.Profile.Profile
import com.example.vkr.mainScreen.Profile.ProfileFragment
import com.example.vkr.mainScreen.Search.SearchFragment
import com.example.vkr.mainScreen.Wishlist.WishlistFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainScreen : AppCompatActivity(),ProfileFragment.DataTransfer {
    //функция для смены фрагмента при переключении в нижнем меню
    lateinit var profile: Profile
    private fun ChangeFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.frameLayout, fragment).commit()
    }
    //передача данных через интерфейс
    override fun onDataTransfer(p: Profile) {
        profile = p
    }
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main_screen)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        hideSystemUI(window)
        //данные профиля
        profile = intent.getSerializableExtra("profile") as Profile
        //изначально загружаем новости
        ChangeFragment(NewsFragment())
        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigation.setOnItemSelectedListener {
            val bundle = Bundle()
            bundle.putSerializable("profile", profile)
            when(it.itemId){
                R.id.news ->{
                    ChangeFragment(NewsFragment())
                    true
                }
                R.id.search ->{
                    val fragment = SearchFragment()
                    fragment.arguments = bundle
                    ChangeFragment(fragment)
                    true
                }
                R.id.wish ->{
                    val fragment = WishlistFragment()
                    fragment.arguments = bundle
                    ChangeFragment(fragment)
                    true
                }
                R.id.profile ->{
                    var fragment = ProfileFragment()
                    fragment.arguments = bundle
                    ChangeFragment(fragment)
                    true
                }
                else -> {false}
            }
        }
    }
}