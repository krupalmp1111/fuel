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
import com.google.firebase.firestore.FirebaseFirestore

class SignupActivity : AppCompatActivity() {

    lateinit var firebaseAuth: FirebaseAuth

    private lateinit var etEmailAddress: EditText
    private lateinit var etPassword: EditText
    private lateinit var etConfirmPassword: EditText
    private lateinit var etUserName: EditText
    private lateinit var etMobileNo: EditText
    private lateinit var btnSignUp: Button
    private lateinit var btnLogin: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        etEmailAddress = findViewById(R.id.etEmailAddress)
        etPassword = findViewById(R.id.etPassword)
        etConfirmPassword = findViewById(R.id.etConfirmPassword)
        btnSignUp = findViewById(R.id.btnSignUp)
        btnLogin = findViewById(R.id.btnLogin)
        etUserName = findViewById(R.id.etUsername)
        etMobileNo = findViewById(R.id.etMobileNo)


        firebaseAuth = FirebaseAuth.getInstance()

        btnSignUp.setOnClickListener {
            val username = etUserName.text.toString()
            val mobileno = etMobileNo.text.toString()
            val email = etEmailAddress.text.toString()
            savefirestore(username, mobileno, email)
            signUpUser()
        }

        btnLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun savefirestore(username: String, mobileno: String, email: String) {
        val db = FirebaseFirestore.getInstance()
        val user: MutableMap<String, Any> = HashMap()
        user["username"] = username
        user["MobileNo"] = mobileno
        user["email"] = email

        db.collection("user")
            .add(user)
            .addOnSuccessListener {
                Toast.makeText(this, "record added successfully", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Toast.makeText(this, "record Fail to add", Toast.LENGTH_SHORT).show()
            }

    }

    private fun signUpUser() {
        val email = etEmailAddress.text.toString()
        val password = etPassword.text.toString()
        val confirmPassword = etConfirmPassword.text.toString()


        if (password != confirmPassword) {
            Toast.makeText(this, "Password and ConfirmPassword doesn't match.", Toast.LENGTH_SHORT)
                .show()
            return
        }

        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) {
                if (it.isSuccessful) {
                    Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, "Error creating user.", Toast.LENGTH_SHORT).show()
                }
            }
    }
}