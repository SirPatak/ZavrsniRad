package com.example.krizickruzic

import DatabaseHelper
import GameDataHelper
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class profile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val databaseHelper = DatabaseHelper(this)

        val db = databaseHelper.readableDatabase

        val projection = arrayOf(DatabaseHelper.COLUMN_PLAYER_TURN, DatabaseHelper.COLUMN_ROUND_AI)

        val cursor = db.query(DatabaseHelper.TABLE_NAME, projection, null, null, null, null, null)

        with(cursor) {
            while (moveToNext()) {
                val roundAI = getInt(getColumnIndexOrThrow(DatabaseHelper.COLUMN_ROUND_AI))

                val brojRunde = findViewById<TextView>(R.id.round)
                (brojRunde).text = (roundAI-1).toString()

                val postotakPobjede = findViewById<TextView>(R.id.postotak)
                postotakPobjede.text = ((MainActivity.xPobjedeAI.toFloat() / (roundAI-1).toFloat()) * 100).toInt().toString()

                val xPlus = findViewById<TextView>(R.id.pobjede)
                xPlus.text = MainActivity.xPobjedeAI.toString()

                val oPlus = findViewById<TextView>(R.id.gubitci)
                oPlus.text = MainActivity.oPobjedeAI.toString()

                val forfeit = findViewById<TextView>(R.id.forfeit)
                forfeit.text = MainActivity.forfeit.toString()
            }
        }

        val home: Button = findViewById(R.id.home)
        home.setOnClickListener {
            val intent= Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val restart: Button = findViewById(R.id.reset)
        restart.setOnClickListener {
            MainActivity.resetValues()
            val gameDataHelper = GameDataHelper(this)
            gameDataHelper.saveGameData(MainActivity.playerTurn, MainActivity.roundAI, MainActivity.xPobjedeAI, MainActivity.oPobjedeAI, MainActivity.forfeit)

            val intent= Intent(this, profile::class.java)
            startActivity(intent)
        }
    }
}