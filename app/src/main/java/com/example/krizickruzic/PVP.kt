package com.example.krizickruzic

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.krizickruzic.ui.theme.Win
import com.example.krizickruzic.ui.theme.checkEndGame

class PVP : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pvp)

        val place = Array(3,{arrayOfNulls<Boolean?>(3)})

        val pobjednik: TextView = findViewById(R.id.pobjednik)

        val buttons = arrayOf(
            arrayOf(findViewById<Button>(R.id.button0), findViewById<Button>(R.id.button1), findViewById<Button>(R.id.button2)),
            arrayOf(findViewById<Button>(R.id.button3), findViewById<Button>(R.id.button4), findViewById<Button>(R.id.button5)),
            arrayOf(findViewById<Button>(R.id.button6), findViewById<Button>(R.id.button7), findViewById<Button>(R.id.button8))
        )

        if (MainActivity.playerTurn) {
            for (i in 0..2) {
                for (j in 0..2) {
                    buttons[i][j].setOnClickListener {
                        if (MainActivity.playerTurn && place[i][j] == null) {
                            place[i][j] = true
                            buttons[i][j].setBackgroundResource(R.drawable.ximage)
                        } else if (!MainActivity.playerTurn && place[i][j] == null) {
                            place[i][j] = false
                            buttons[i][j].setBackgroundResource(R.drawable.oimage)
                        }
                        MainActivity.playerTurn = MainActivity.playerTurn.not()
                    }
                }
            }
        }


        val restart: Button = findViewById(R.id.reset)
        restart.setOnClickListener {
            val intent= Intent(this, PVP::class.java)
            startActivity(intent)
        }

        val home: Button = findViewById(R.id.home)
        home.setOnClickListener {
            val intent= Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val ai: Button = findViewById(R.id.AI)
        ai.setOnClickListener {
            val intent= Intent(this, PVE::class.java)
            startActivity(intent)
        }

    }
}