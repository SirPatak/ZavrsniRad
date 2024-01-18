package com.example.krizickruzic

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    companion object {
        var playerTurn: Boolean = true
        var xPobjede: Int = 0
        var oPobjede: Int = 0
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val pvpPlay: Button = findViewById(R.id.pvp)
        pvpPlay.setOnClickListener {
            val intent= Intent(this, PVP::class.java)
            startActivity(intent)
        }
        val aiPlay: Button = findViewById(R.id.ai)
        aiPlay.setOnClickListener {
            val intent= Intent(this, PVE::class.java)
            startActivity(intent)
        }
    }
}