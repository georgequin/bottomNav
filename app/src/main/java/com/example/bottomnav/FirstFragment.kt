package com.example.bottomnav

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

/**
 * A simple [Fragment] subclass.
 */
class FirstFragment : Fragment() {

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val fullScreenSystemUiVisibility = View.SYSTEM_UI_FLAG_IMMERSIVE or
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION

        val systemUiVisibility = when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.P -> {
                fullScreenSystemUiVisibility or
                        View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR or
                        View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
            }
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.M -> {
                View.SYSTEM_UI_FLAG_VISIBLE or
                        View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            }
            else -> {
                View.SYSTEM_UI_FLAG_VISIBLE
            }
        }

        return inflater.inflate(R.layout.fragment_first, container, false)
    }

}