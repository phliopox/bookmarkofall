package com.ph.bookmarkofall

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.itemIconTintList = null
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.frag_container) as NavHostFragment
        val navController = navHostFragment.navController
        bottomNavigationView.setupWithNavController(navController)


        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.isSelected = true
        fab.setOnClickListener {
            it.isSelected = true
            //바텀 메뉴 선택 해제
            bottomNavigationView.menu.setGroupCheckable(0, true, false)
            bottomNavigationView.menu.findItem(bottomNavigationView.selectedItemId).isChecked = false
            navController.navigate(R.id.navigation_book_mark)
        }

        bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_book -> {
                    bottomNavigationView.menu.findItem(R.id.navigation_my_page)?.isChecked = false
                    navController.navigate(R.id.navigation_book)
                }
                R.id.navigation_my_page -> {
                    bottomNavigationView.menu.findItem(R.id.navigation_book)?.isChecked = false
                    navController.navigate(R.id.navigation_my_page)
                }
            }
            fab.isSelected = false //fab 선택해제
            true
        }
    }
}