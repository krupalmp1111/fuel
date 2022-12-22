package com.example.fuel.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.fuel.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class ProfileActivity : AppCompatActivity() {
    lateinit var firebaseAuth: FirebaseAuth

    private lateinit var txtEmail: TextView
    private lateinit var btnLogout: Button
    private lateinit var txtUsername: TextView
    private lateinit var txtMobileNo: TextView
    private var cemail: String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        txtEmail = findViewById(R.id.txtEmail)
        btnLogout = findViewById(R.id.btnLogout)
        txtUsername = findViewById(R.id.txtUserName)
        txtMobileNo = findViewById(R.id.txtMobileNo)

        firebaseAuth = FirebaseAuth.getInstance()
        txtEmail.text = firebaseAuth.currentUser?.email
        cemail = firebaseAuth.currentUser?.email.toString()
        readFirestoreData()

        btnLogout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    fun readFirestoreData() {

        val db = FirebaseFirestore.getInstance()
        db.collection("user")
            .whereEqualTo("email", cemail)
            .get()
            .addOnCompleteListener {
                val result: StringBuffer = StringBuffer()
                val result1: StringBuffer = StringBuffer()
                if (it.isSuccessful) {
                    for (document in it.result) {
                        result.append(document.data.getValue("username"))
                        result1.append(document.data.getValue("MobileNo"))
                    }
                    txtUsername.setText("Username:" + result)
                    txtMobileNo.setText("MobileNo:" + result1)

                }
            }

    }
}