package com.example.crud.ui.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.crud.R
import com.example.crud.databinding.ActivityNavigationBinding
import com.example.crud.ui.fragment.HomeFragment
import com.example.crud.ui.fragment.NotificationFragment
import com.example.crud.ui.fragment.ProfileFragment
import com.example.crud.ui.fragment.SearchFragment

class NavigationActivity : AppCompatActivity() {


     lateinit var navigationBinding: ActivityNavigationBinding
    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager : FragmentManager = supportFragmentManager
        val fragmentTransaction : FragmentTransaction = fragmentManager.beginTransaction()

        fragmentTransaction.replace(R.id.frameDashboard,fragment)

        fragmentTransaction.commit()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        navigationBinding= ActivityNavigationBinding.inflate(layoutInflater)


        setContentView(navigationBinding.root)
        navigationBinding.bottomView.setOnItemSelectedListener { menu ->
            when(menu.itemId){
                R.id.navHome-> replaceFragment(HomeFragment())
                R.id.navSearch -> replaceFragment(SearchFragment())
                R.id.navProfile -> replaceFragment(ProfileFragment())
                R.id.navNotification -> replaceFragment(NotificationFragment())

                else -> {}
            }
            true
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.bottomView)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}