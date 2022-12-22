package com.example.fuel.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.fuel.R
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions

var pathss: String = ""

class EditPumpActivity : AppCompatActivity() {
    private lateinit var etEnterPumpName: EditText
    private lateinit var etEnterownerName: EditText
    private lateinit var etEnterPrice: EditText
    private lateinit var etEnterDescription: EditText
    private lateinit var EditPump: Button
    private lateinit var etEnterImageUrl: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        etEnterPumpName = findViewById(R.id.etEnterPumpName)
        etEnterownerName = findViewById(R.id.etEnterOwnerName)
        etEnterPrice = findViewById(R.id.etEnterPrice)
        etEnterDescription = findViewById(R.id.etEnterDescription)
        EditPump = findViewById(R.id.AddPumps)
        etEnterImageUrl = findViewById(R.id.etEnterImageUrl)

        EditPump.setOnClickListener() {
            val pumpname = etEnterPumpName.text.toString()
            val ownername = etEnterownerName.text.toString()
            val price = etEnterPrice.text.toString()
            val description = etEnterDescription.text.toString()
            val image = etEnterImageUrl.text.toString()
            updatepump(pumpname, ownername, price, description, image)
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }
    }

    private fun updatepump(
        pumpname: String,
        ownername: String,
        price: String,
        description: String,
        image: String
    ) {
        val dbs = FirebaseFirestore.getInstance()
        pathss = intent.getStringExtra("pet").toString();
        val pump: MutableMap<String, Any> = HashMap()
        pump["pumpname"] = pumpname
        pump["ownername"] = ownername
        pump["price"] = price
        pump["description"] = description
        pump["pumpimage"] = image

        val querys = dbs.collection(pathss)
            .whereEqualTo("pumpname", pumpname)
            .get()
        querys.addOnSuccessListener {
            for (document in it) {
                dbs.collection(pathss).document(document.id).set(pump, SetOptions.merge())
            }
            Toast.makeText(this, "record updated successfully", Toast.LENGTH_SHORT).show()
        }
            .addOnFailureListener {
                Toast.makeText(this, "record Fail to update", Toast.LENGTH_SHORT).show()
            }

    }
}