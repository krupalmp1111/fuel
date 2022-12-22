package com.example.fuel.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fuel.R
import com.example.fuel.adapters.CategoryAdapter
import com.example.fuel.models.pumpcategory
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.navigation.NavigationView
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {

    private lateinit var appBar: MaterialToolbar
    private lateinit var mainDrawer: DrawerLayout
    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    lateinit var adapter: CategoryAdapter
    private var categoryList = mutableListOf<pumpcategory>()
    private lateinit var categoryRecyclerView: RecyclerView
    lateinit var firestore: FirebaseFirestore
    private lateinit var navigationView: NavigationView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        appBar = findViewById(R.id.appBar)
        mainDrawer = findViewById(R.id.mainDrawer)
        categoryRecyclerView = findViewById(R.id.categoryRecyclerView)
        navigationView = findViewById(R.id.navigationView)

        appBar.setOnClickListener{
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }


        setUpViews()
    }


    fun setUpViews() {
        setUpFireStore()
        setUpDrawerLayout()
        setUpRecyclerview()
        //setUpDatePicker()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setUpFireStore() {
        firestore = FirebaseFirestore.getInstance()
        val collectionReference = firestore.collection("Petrol")
        collectionReference.addSnapshotListener { value, error ->
            if (value == null || error != null) {
                Toast.makeText(this, "Error Fetching Data", Toast.LENGTH_SHORT).show()
                return@addSnapshotListener
            }
            Log.d("DATA", value.toObjects(pumpcategory::class.java).toString())
            categoryList.clear()
            categoryList.addAll(value.toObjects(pumpcategory::class.java))
            adapter.notifyDataSetChanged()
        }
    }

    private fun setUpRecyclerview() {
        adapter = CategoryAdapter(this, categoryList)
        categoryRecyclerView.layoutManager = GridLayoutManager(this, 2)
        categoryRecyclerView.adapter = adapter
    }

    private fun setUpDrawerLayout() {
//        setSupportActionBar(appBar)
        actionBarDrawerToggle = ActionBarDrawerToggle(this, mainDrawer, R.string.app_name, R.string.app_name)
        actionBarDrawerToggle.syncState()
        navigationView.setNavigationItemSelectedListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
            mainDrawer.closeDrawers()
            true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}