package com.example.girlydiaryapp

import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.PopupMenu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.ContextThemeWrapper

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val menuButton: ImageView = findViewById(R.id.menuButton)

        menuButton.setOnClickListener {
            showPopupMenu(menuButton)
        }
    }

    private fun showPopupMenu(view: ImageView) {
        val wrapper = ContextThemeWrapper(this, R.style.CustomPopupMenu)
        val popupMenu = PopupMenu(wrapper, view)
        popupMenu.menuInflater.inflate(R.menu.main_menu, popupMenu.menu)

        popupMenu.setOnMenuItemClickListener { menuItem: MenuItem ->
            when (menuItem.itemId) {
                R.id.action_my_information -> {
                    // Handle "My Information" action
                    true
                }
                R.id.action_logout -> {
                    // Handle "Logout" action
                    true
                }
                else -> false
            }
        }

        popupMenu.show()
    }
}
