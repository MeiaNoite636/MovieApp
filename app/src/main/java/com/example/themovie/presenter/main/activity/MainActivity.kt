package com.example.themovie.presenter.main.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.themovie.BuildConfig
import com.example.themovie.R
import com.example.themovie.databinding.ActivityMainBinding
import com.example.themovie.util.FirebaseHelper
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initNavigation()
    }

    private fun initNavigation(){
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        navController = navHostFragment.navController
        NavigationUI.setupWithNavController(binding.btnv, navController)

        navController.addOnDestinationChangedListener{controller, destination, arguments ->
            binding.btnv.isVisible =
                destination.id == R.id.menu_home     ||
                destination.id == R.id.menu_search   ||
                destination.id == R.id.menu_favorite ||
                destination.id == R.id.menu_download ||
                destination.id == R.id.menu_profile
        }
    }
}