package com.example.krizickruzic

import DatabaseHelper
import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Intent
import android.health.connect.datatypes.units.Percentage
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    fun saveGameData() {
        val databaseHelper = DatabaseHelper(this)
        val db = databaseHelper.writableDatabase
        val values = ContentValues().apply {
            put(DatabaseHelper.COLUMN_PLAYER_TURN, if (playerTurn) 1 else 0)
            put(DatabaseHelper.COLUMN_ROUND_AI, roundAI)
            put(DatabaseHelper.COLUMN_XPOBJEDE_AI, xPobjedeAI)
            put(DatabaseHelper.COLUMN_OPOBJEDE_AI, oPobjedeAI)
            put(DatabaseHelper.COLUMN_FORFEIT, forfeit)
        }
        val newRowId = db?.insert(DatabaseHelper.TABLE_NAME, null, values)
    }

    override fun onPause() {
        super.onPause()
        saveGameData()
    }

    fun loadGameData() {
        val databaseHelper = DatabaseHelper(this)
        val db = databaseHelper.readableDatabase

        val projection = arrayOf(DatabaseHelper.COLUMN_PLAYER_TURN, DatabaseHelper.COLUMN_ROUND_AI, DatabaseHelper.COLUMN_XPOBJEDE_AI, DatabaseHelper.COLUMN_OPOBJEDE_AI, DatabaseHelper.COLUMN_FORFEIT)

        val cursor = db.query(
            DatabaseHelper.TABLE_NAME,
            projection,
            null,
            null,
            null,
            null,
            null
        )

        with(cursor) {
            while (moveToNext()) {
                playerTurn = getInt(getColumnIndexOrThrow(DatabaseHelper.COLUMN_PLAYER_TURN)) != 0
                roundAI = getInt(getColumnIndexOrThrow(DatabaseHelper.COLUMN_ROUND_AI))
                xPobjedeAI = getInt(getColumnIndexOrThrow(DatabaseHelper.COLUMN_XPOBJEDE_AI))
                oPobjedeAI = getInt(getColumnIndexOrThrow(DatabaseHelper.COLUMN_OPOBJEDE_AI))
                forfeit = getInt(getColumnIndexOrThrow(DatabaseHelper.COLUMN_FORFEIT))
            }
        }

        cursor.close()
        db.close()
    }

    companion object {
        var playerTurn: Boolean = true
        var roundAI: Int = 1
        var xPobjedeAI: Int = 0
        var oPobjedeAI: Int = 0
        var forfeit: Int = 0

        var roundPvP: Int = 1
        var xPobjedePvP: Int = 0
        var oPobjedePvP: Int = 0

        fun resetValues() {
            playerTurn = true
            forfeit = 0
            roundAI = 1
            oPobjedeAI = 0
            xPobjedeAI = 0
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadGameData()

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
        val statistike: Button = findViewById(R.id.statistike)
        statistike.setOnClickListener {
            val intent= Intent(this, profile::class.java)
            startActivity(intent)
        }

        val databaseHelper = DatabaseHelper(this)
        val db = databaseHelper.writableDatabase
        val values = ContentValues().apply {
            put(DatabaseHelper.COLUMN_PLAYER_TURN, if (playerTurn) 1 else 0)
            put(DatabaseHelper.COLUMN_ROUND_AI, roundAI)
            put(DatabaseHelper.COLUMN_XPOBJEDE_AI, xPobjedeAI)
            put(DatabaseHelper.COLUMN_OPOBJEDE_AI, oPobjedeAI)
            put(DatabaseHelper.COLUMN_FORFEIT, forfeit)
        }
        val newRowId = db?.insert(DatabaseHelper.TABLE_NAME, null, values)
    }
}