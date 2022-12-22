package com.example.fuel.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.fuel.R
import com.google.firebase.auth.FirebaseAuth

class SplashActivity : AppCompatActivity() {

    private lateinit var btnGetStarted: Button
    val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        btnGetStarted = findViewById(R.id.btnGetStarted)


        if (auth.currentUser != null) {
            Toast.makeText(this, "User is already logged in!", Toast.LENGTH_SHORT).show()
            redirect("MAIN")
        }

        btnGetStarted.setOnClickListener {
            redirect("LOGIN")
        }

    }

    private fun redirect(name: String) {
        val intent = when (name) {
            "LOGIN" -> Intent(this, LoginActivity::class.java)
            "MAIN" -> Intent(this, MainActivity::class.java)
            else -> throw Exception("No Path Exists")
        }
        startActivity(intent)
        finish()
    }
}