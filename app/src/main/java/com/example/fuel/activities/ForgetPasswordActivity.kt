package com.example.fuel.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.fuel.R
import com.google.firebase.auth.FirebaseAuth

class ForgetPasswordActivity : AppCompatActivity() {

    lateinit var firebaseAuth: FirebaseAuth

    private lateinit var etEmailAddress1: EditText
    private lateinit var btnForgetPassword: Button
    private lateinit var backtoLogin: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_password)

        firebaseAuth = FirebaseAuth.getInstance()

        etEmailAddress1 = findViewById(R.id.etEmailAddress1)
        btnForgetPassword = findViewById(R.id.btnForgetPassword)
        backtoLogin = findViewById(R.id.backtoLogin)

        backtoLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        btnForgetPassword.setOnClickListener {
            val email = etEmailAddress1.text.toString()
            if (email.isEmpty()) {
                Toast.makeText(this, "Please Enter Email.", Toast.LENGTH_SHORT).show()
            } else {
                FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(
                                this,
                                "Email Sent Successfully To Reset Password",
                                Toast.LENGTH_LONG
                            ).show()
                            finish()
                        } else {
                            Toast.makeText(
                                this,
                                task.exception!!.message.toString(),
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
            }
        }
    }
}