package com.example.finalproject

import android.os.Bundle
import android.util.Log
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.finalproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val TAG = "MainActivity"
    private lateinit var binding: ActivityMainBinding
    companion object {
        var currentFragmentId: Int = R.id.navigation_info
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mActionBar = supportActionBar
        mActionBar!!.hide()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_info, R.id.navigation_lectures, R.id.navigation_picked, R.id.navigation_community
            )
        )
        navView.setupWithNavController(navController)
        navView.selectedItemId = currentFragmentId

    }

    fun changeFragment(fragment: Fragment) {
        binding.navView.selectedItemId = currentFragmentId
        supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment_activity_main,fragment).commit()
    }


}