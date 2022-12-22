package com.example.fuel.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.fuel.R

import com.squareup.picasso.Picasso

class PumpActivity : AppCompatActivity() {
    private lateinit var btnbuy: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pump)

        var pump: TextView = findViewById(R.id.tv_title)
        var owner: TextView = findViewById(R.id.tv_owner)
        var p1: TextView = findViewById(R.id.tv_pricee)
        var img: ImageView = findViewById(R.id.pumpUimage)
        var descV: TextView = findViewById(R.id.textView3)

        btnbuy = findViewById(R.id.btnbuy)


        val pname = intent.getStringExtra("pname");
        val oname = intent.getStringExtra("oname");
        val price = intent.getStringExtra("price");
        val imgV = intent.getStringExtra("img");
        val desc = intent.getStringExtra("desc");

        Picasso.get().load(imgV).into(img)

        descV.setText(desc)
        pump.setText(pname)
        owner.setText(oname)
        p1.setText(price)
        btnbuy.setOnClickListener() {
            val intent = Intent(this, SuccessfulSplashActivity::class.java)
            startActivity(intent)
            finish()
        }


    }
}