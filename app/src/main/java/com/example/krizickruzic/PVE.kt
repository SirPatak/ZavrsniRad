package com.example.krizickruzic

import GameDataHelper
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.krizickruzic.MainActivity.Companion.playerTurn
import com.example.krizickruzic.ui.theme.checkEndGame
import com.example.krizickruzic.ui.theme.jelPobjedaAI

class PVE : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pve)

        val postotak = (MainActivity.xPobjedeAI.toFloat()/(MainActivity.roundAI-1).toFloat()*100).toInt()

        val xPlus = findViewById<TextView>(R.id.xplus)
        xPlus.text = MainActivity.xPobjedeAI.toString()

        val oPlus = findViewById<TextView>(R.id.oplus)
        oPlus.text = MainActivity.oPobjedeAI.toString()

        val brojRunde = findViewById<TextView>(R.id.runda)
        brojRunde.text = MainActivity.roundAI.toString()

        val postotakPobjede = findViewById<TextView>(R.id.postotakpobjede)
        postotakPobjede.text = postotak.toString()

        if(MainActivity.roundAI.mod(2) == 0) {
            playerTurn = false
        }
        if(MainActivity.roundAI.mod(2) == 1) {
            playerTurn = true
        }

        val place = Array(3) { arrayOfNulls<Boolean?>(3) }

        val buttons = arrayOf(
            arrayOf(findViewById<Button>(R.id.button0), findViewById<Button>(R.id.button1), findViewById<Button>(R.id.button2)),
            arrayOf(findViewById<Button>(R.id.button3), findViewById<Button>(R.id.button4), findViewById<Button>(R.id.button5)),
            arrayOf(findViewById<Button>(R.id.button6), findViewById<Button>(R.id.button7), findViewById<Button>(R.id.button8))
        )

        fun robot() {
            val (i, j) = jelPobjedaAI(place)
            place[i][j] = false
            buttons[i][j].setBackgroundResource(R.drawable.oimage)

            playerTurn = !playerTurn
        }

        if(!playerTurn){
            robot()
        }

        if (playerTurn) {
            for (i in 0..2) {
                for (j in 0..2) {
                    buttons[i][j].setOnClickListener {
                        if (place[i][j] == null) {
                            place[i][j] = true
                            buttons[i][j].setBackgroundResource(R.drawable.ximage)
                            if(checkEndGame(place) == true) {
                                Toast.makeText(this@PVE, "X je pobjednik", Toast.LENGTH_SHORT).show()
                                MainActivity.roundAI++
                                MainActivity.xPobjedeAI++
                                val gameDataHelper = GameDataHelper(this)
                                gameDataHelper.saveGameData(playerTurn, MainActivity.roundAI, MainActivity.xPobjedeAI, MainActivity.oPobjedeAI, MainActivity.forfeit)
                                val intent= Intent(this, PVE::class.java)
                                startActivity(intent)
                            }
                            else if(checkEndGame(place) == null) {
                                Toast.makeText(this@PVE, "Izjednaceno", Toast.LENGTH_SHORT).show()
                                MainActivity.roundAI++
                                val gameDataHelper = GameDataHelper(this)
                                gameDataHelper.saveGameData(playerTurn, MainActivity.roundAI, MainActivity.xPobjedeAI, MainActivity.oPobjedeAI, MainActivity.forfeit)
                                val intent= Intent(this, PVE::class.java)
                                startActivity(intent)
                            }
                            else {
                                robot()
                                if(checkEndGame(place) == true) {
                                    Toast.makeText(this@PVE, "O je pobjednik", Toast.LENGTH_SHORT).show()
                                    MainActivity.roundAI++
                                    MainActivity.oPobjedeAI++
                                    val gameDataHelper = GameDataHelper(this)
                                    gameDataHelper.saveGameData(playerTurn, MainActivity.roundAI, MainActivity.xPobjedeAI, MainActivity.oPobjedeAI, MainActivity.forfeit)
                                    val intent= Intent(this, PVE::class.java)
                                    startActivity(intent)
                                }
                                else if(checkEndGame(place) == null) {
                                    Toast.makeText(this@PVE, "Izjednaceno", Toast.LENGTH_SHORT).show()
                                    MainActivity.roundAI++
                                    val gameDataHelper = GameDataHelper(this)
                                    gameDataHelper.saveGameData(playerTurn, MainActivity.roundAI, MainActivity.xPobjedeAI, MainActivity.oPobjedeAI, MainActivity.forfeit)
                                    val intent= Intent(this, PVE::class.java)
                                    startActivity(intent)
                                }
                            }
                            playerTurn = !playerTurn
                        }

                    }
                }
            }
        }

        val restart: Button = findViewById(R.id.reset)
        restart.setOnClickListener {
            MainActivity.forfeit++
            MainActivity.oPobjedeAI++
            val gameDataHelper = GameDataHelper(this)
            gameDataHelper.saveGameData(playerTurn, MainActivity.roundAI, MainActivity.xPobjedeAI, MainActivity.oPobjedeAI, MainActivity.forfeit)

            val intent= Intent(this, PVE::class.java)
            startActivity(intent)
        }

        val home: Button = findViewById(R.id.home)
        home.setOnClickListener {
            val intent= Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val pvp: Button = findViewById(R.id.pvp)
        pvp.setOnClickListener {
            val intent= Intent(this, PVP::class.java)
            startActivity(intent)
        }

    }
}