package com.example.fuel.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.fuel.R
import com.google.firebase.firestore.FirebaseFirestore

private lateinit var etEnterPumpName: EditText
private lateinit var etEnterOwnerName: EditText
private lateinit var etEnterPrice: EditText
private lateinit var etEnterDescription: EditText
private lateinit var AddPumps: Button
private lateinit var etEnterImageUrl: EditText

var paths = ""
var bid = ""

class AddPumpActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_pump)
        etEnterPumpName = findViewById(R.id.etEnterPumpName)
        etEnterOwnerName = findViewById(R.id.etEnterOwnerName)
        etEnterPrice = findViewById(R.id.etEnterPrice)
        etEnterDescription = findViewById(R.id.etEnterDescription)
        AddPumps = findViewById(R.id.AddPumps)
        etEnterImageUrl = findViewById(R.id.etEnterImageUrl)


        AddPumps.setOnClickListener() {
            val pumpname = etEnterPumpName.text.toString()
            val ownername = etEnterOwnerName.text.toString()
            val price = etEnterPrice.text.toString()
            val description = etEnterDescription.text.toString()
            val image = etEnterImageUrl.text.toString()
            savepump(pumpname, ownername, price, description, image)
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }
    }

    private fun savepump(
        pumpname: String,
        ownername: String,
        price: String,
        description: String,
        image: String
    ) {
        val db = FirebaseFirestore.getInstance()
        paths = intent.getStringExtra("pet").toString();
        val pump: MutableMap<String, Any> = HashMap()
        pump["pumpname"] = pumpname
        pump["ownername"] = ownername
        pump["price"] = price
        pump["description"] = description
        pump["pumpimage"] = image

        db.collection(paths)
            .add(pump)
            .addOnSuccessListener {
                bid = it.id
                Toast.makeText(this, "record added successfully", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Toast.makeText(this, "record Fail to add", Toast.LENGTH_SHORT).show()
            }
    }
}







