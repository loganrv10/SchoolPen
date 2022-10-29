package com.asmanmirza.schoolpen.presentation.main

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.asmanmirza.schoolpen.R
import com.asmanmirza.schoolpen.databinding.ActivityMainBinding
import com.asmanmirza.schoolpen.presentation.main.host.HostFragment
import dagger.hilt.android.AndroidEntryPoint


/**
 * @Author: Asman Mirza
 * @Email: asman@otmalse.com
 * @Date: 28-09-2022
 * @Time: 20:57
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, true)
        super.onCreate(savedInstanceState)
        instance = this
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    fun updateStatusBarColor(color: String?) { // Color must be in hexadecimal fromat
        val window: Window = window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = Color.parseColor(color)
    }

    companion object {
        lateinit var instance: MainActivity
            private set
    }
}