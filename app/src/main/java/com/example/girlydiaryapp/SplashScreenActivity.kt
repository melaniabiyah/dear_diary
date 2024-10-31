package com.example.girlydiaryapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        // Delay for a few seconds and then start the main activity
        Handler().postDelayed({
            val intent = Intent(this, SignUpActivity::class.java) // Change to your main activity
            startActivity(intent)
            finish() // Close the splash screen activity
        }, 3000) // Duration in milliseconds (e.g., 3000 = 3 seconds)
    }
}
