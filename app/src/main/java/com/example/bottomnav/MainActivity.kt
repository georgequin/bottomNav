package com.example.bottomnav

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.View.*
import android.view.WindowManager
import android.widget.PopupMenu
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.bottomnav.databinding.ActivityMainBinding


open class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding

    protected val fullScreenSystemUiVisibility = SYSTEM_UI_FLAG_IMMERSIVE or
            SYSTEM_UI_FLAG_LAYOUT_STABLE or
            SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
            SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION

    protected open val systemUiVisibility = when {
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.P -> {
            fullScreenSystemUiVisibility or
                    SYSTEM_UI_FLAG_LIGHT_STATUS_BAR or
                    SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
        }
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.M -> {
            SYSTEM_UI_FLAG_VISIBLE or
                    SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
        else -> {
            SYSTEM_UI_FLAG_VISIBLE
        }
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // setSupportActionBar(R.id.my_toolbar)

            window.decorView.run {
                systemUiVisibility = this@MainActivity.systemUiVisibility}


        WindowCompat.setDecorFitsSystemWindows(window, false)
        val windowInsetsController =
            ViewCompat.getWindowInsetsController(window.decorView)

        windowInsetsController?.isAppearanceLightNavigationBars = true
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navController = findNavController(R.id.main_fragment1)
        //setupActionBarWithNavController(navController)
        setupSmoothBottomMenu()
        //supportActionBar?.hide()

    }

    private fun setupSmoothBottomMenu() {
        val popupMenu = PopupMenu(this, null)
        popupMenu.inflate(R.menu.bottom_nav_menu)
        val menu = popupMenu.menu
        binding.bottomBar.setupWithNavController(menu, navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}


