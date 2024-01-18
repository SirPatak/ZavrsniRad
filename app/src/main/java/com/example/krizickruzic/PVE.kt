package com.example.krizickruzic

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.krizickruzic.ui.theme.checkEndGame
import com.example.krizickruzic.ui.theme.jelPobjedaAI
import com.example.krizickruzic.ui.theme.prostoriStavljanja

class PVE : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pve)

        //val moves = arrayOfNulls<Boolean?>(9)

        var board = Array(3,{IntArray(3)})

        val place = Array(3,{arrayOfNulls<Boolean?>(3)})

        val pobjednik: TextView = findViewById(R.id.pobjednik)
        val xPobjednik: TextView = findViewById(R.id.xplus)
        var oPobjednik: TextView = findViewById(R.id.oplus)

        val buttons = arrayOf(
            arrayOf(findViewById<Button>(R.id.button0), findViewById<Button>(R.id.button1), findViewById<Button>(R.id.button2)),
            arrayOf(findViewById<Button>(R.id.button3), findViewById<Button>(R.id.button4), findViewById<Button>(R.id.button5)),
            arrayOf(findViewById<Button>(R.id.button6), findViewById<Button>(R.id.button7), findViewById<Button>(R.id.button8))
        )




        fun kaka(a: Int): Int {
            println("a")
            /*if(place.flatten().all { it == null }) {
                println("alalalala")
                place[0][0] = false
                buttons[1][1].setBackgroundResource(R.drawable.oimage)
                return 1
            }
            if(place.flatten().count { it != null } == 1 && (place[0][0] == true || place[0][2] == true || place[2][0] == true || place[2][2] == true)) {
                place[1][1] = false
                buttons[1][1].setBackgroundResource(R.drawable.oimage)
                return 1
            }*/

            //prostoriStavljanja(place)

            val (i, j) = jelPobjedaAI(place)
            place[i][j] = false
            buttons[i][j].setBackgroundResource(R.drawable.oimage)

            MainActivity.playerTurn = MainActivity.playerTurn.not()

            //prostoriStavljanja(place)

            return 1
        }



        if (MainActivity.playerTurn) {
            for (i in 0..2) {
                for (j in 0..2) {
                    buttons[i][j].setOnClickListener {
                        if (place[i][j] == null) {
                            place[i][j] = true
                            buttons[i][j].setBackgroundResource(R.drawable.ximage)
                            var c = checkEndGame(place)
                            MainActivity.playerTurn = !MainActivity.playerTurn
                            println(c)
                            val k = kaka(1)
                            MainActivity.playerTurn = !MainActivity.playerTurn
                            var b = checkEndGame(place)
                            println(b)
                        }
                    }
                }
            }
        }





        fun findNull(board: Array<Array<Int?>>): Pair<Int, Int>? {
            val directions = listOf(Pair(1, 0), Pair(0, 1), Pair(1, 1), Pair(-1, 1))

            for (i in 0..2) {
                for (j in 0..2) {
                    for (dir in directions) {
                        val dx = dir.first
                        val dy = dir.second
                        if (i + 2 * dx in 0..2 && j + 2 * dy in 0..2) {
                            val values = listOf(board[i][j], board[i + dx][j + dy], board[i + 2 * dx][j + 2 * dy])
                            if (values.count { it == null } == 1) {
                                return Pair(i + values.indexOf(null) * dx, j + values.indexOf(null) * dy)
                            }
                        }
                    }
                }
            }
            return null
        }

        if (!MainActivity.playerTurn) {
            if(place.all { row -> row.all { it == null }}) {
                place[0][0] = false
            }
            /*if(place.count { it != null } == 1 && (place[0][0] == true || place[0][2] == true || place[2][0] == true || place[2][2] == true)) {
                place[1][1] = false
                buttons[1][1].setBackgroundResource(R.drawable.oimage)
            }*/

            prostoriStavljanja(place)

            MainActivity.playerTurn = MainActivity.playerTurn.not()

        }



        fun checkWin(board: Array<Array<String>>): String {
            // Check rows and columns
            for (i in 0..2) {
                if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != "") {
                    return board[i][0]
                }
                if (board[0][i] == board[1][i] && board[1][i] == board[2][i] && board[0][i] != "") {
                    return board[0][i]
                }
            }

            // Check diagonals
            if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != "") {
                return board[0][0]
            }
            if (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != "") {
                return board[0][2]
            }

            // No winner
            return ""
        }



        val restart: Button = findViewById(R.id.reset)
        restart.setOnClickListener {
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