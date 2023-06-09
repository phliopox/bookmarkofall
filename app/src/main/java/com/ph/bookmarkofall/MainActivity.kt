package com.ph.bookmarkofall

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.itemIconTintList = null
        val navController =
            supportFragmentManager.findFragmentById(R.id.mainContainer)?.findNavController()
        navController?.let {
            bottomNavigationView.setupWithNavController(it)
        }
        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            it.isSelected = !it.isSelected
            //바텀 메뉴 선택 해제
            bottomNavigationView.menu.setGroupCheckable(0, true, false)
            bottomNavigationView.menu.findItem(bottomNavigationView.selectedItemId).isChecked =
                false

        }
        bottomNavigationView.setOnItemSelectedListener {
            fab.isSelected = false //fab 선택해제
            true
        }
    }
}