
package com.example.fuel.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fuel.R
import com.example.fuel.adapters.PumpAdapter
import com.example.fuel.models.pump
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class Pump_list : AppCompatActivity() {
    //private lateinit var appBar: MaterialToolbar
    private var Pump_list = mutableListOf<pump>()
    private lateinit var pumpRecyclerView: RecyclerView
    private lateinit var btnaddpump: Button
    private lateinit var btnupdatepump: Button
    lateinit var firestore: FirebaseFirestore
    lateinit var adapter: PumpAdapter
    var path = ""
    val userType = FirebaseAuth.getInstance().currentUser!!.email
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pump_list)
        //appBar=findViewById(R.id.appBar)
        pumpRecyclerView = findViewById(R.id.pumplistRecyclerView)
        btnaddpump = findViewById(R.id.btnaddpump)
        btnupdatepump = findViewById(R.id.btnupdatepump)
        if (userType.equals("krupalmp1111@gmail.com")) {
            btnaddpump.visibility = View.VISIBLE
            btnupdatepump.visibility = View.VISIBLE
        } else {
            btnaddpump.visibility = View.INVISIBLE
            btnupdatepump.visibility = View.INVISIBLE
        }
        setUpViews()
        btnaddpump.setOnClickListener() {
            val intent = Intent(this, AddPumpActivity::class.java)
            intent.putExtra("pet", path)
            startActivity(intent)
        }
        btnupdatepump.setOnClickListener() {
            val intent = Intent(this, EditPumpActivity::class.java)
            intent.putExtra("pet", path)
            startActivity(intent)

        }
    }

    fun setUpViews() {
        getUpdatedList()
        setUpRecyclerview()
    }


    private fun getUpdatedList() {
        var firestore = FirebaseFirestore.getInstance()
        path = intent.getStringExtra("cat").toString();

        val collectionReference = firestore.collection(path.toString())
        collectionReference.get().addOnCompleteListener {
            if (it.isSuccessful) {
                Pump_list = it.result.toObjects(pump::class.java)
                setUpRecyclerview()
            }
        }
    }

    private fun setUpRecyclerview() {

        adapter =
            PumpAdapter(this, Pump_list as ArrayList<pump>, intent.getStringExtra("cat").toString())
        pumpRecyclerView.layoutManager = GridLayoutManager(this, 2)
        pumpRecyclerView.adapter = adapter
    }
}
