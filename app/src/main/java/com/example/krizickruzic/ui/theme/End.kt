package com.example.krizickruzic.ui.theme

import android.media.Image.Plane
import com.example.krizickruzic.MainActivity
import com.example.krizickruzic.R

fun checkEndGame(place: Array<Array<Boolean?>>): Boolean? {
    println("papa franjo")
    if (
        (place[0][0] == MainActivity.playerTurn && place[0][1] == MainActivity.playerTurn && place[0][2] == MainActivity.playerTurn) ||
        (place[1][0] == MainActivity.playerTurn && place[1][1] == MainActivity.playerTurn && place[1][2] == MainActivity.playerTurn) ||
        (place[2][0] == MainActivity.playerTurn && place[2][1] == MainActivity.playerTurn && place[2][2] == MainActivity.playerTurn) ||
        (place[0][0] == MainActivity.playerTurn && place[1][0] == MainActivity.playerTurn && place[2][0] == MainActivity.playerTurn) ||
        (place[0][1] == MainActivity.playerTurn && place[1][1] == MainActivity.playerTurn && place[2][1] == MainActivity.playerTurn) ||
        (place[0][2] == MainActivity.playerTurn && place[1][2] == MainActivity.playerTurn && place[2][2] == MainActivity.playerTurn) ||
        (place[0][0] == MainActivity.playerTurn && place[1][1] == MainActivity.playerTurn && place[2][2] == MainActivity.playerTurn) ||
        (place[0][2] == MainActivity.playerTurn && place[1][1] == MainActivity.playerTurn && place[2][0] == MainActivity.playerTurn)
    ) {
        return true
    }
    else if (place.all { row -> row.all { it != null }}) {
        return null
    }
    else {
        /*for(i in 0..2) {
            for(j in 0..2) {
                print(place[i][j])
                print(" ")
            }
            println()
        }*/
        return false
    }
}

fun prostoriStavljanja(place: Array<Array<Boolean?>>): Pair<Int, Int> {
    if (place[0][0] != null && place[0][1] != null && place[0][2] == null) {
        return Pair(0,2)
    }
    else if (place[0][0] != null && place[0][1] == null && place[0][2] != null) {
        return Pair(0,1)
    }
    else if (place[0][0] == null && place[0][1] != null && place[0][2] != null) {
        return Pair(0,0)
    }

    else if (place[0][0] != null && place[1][0] != null && place[2][0] == null) {
        return Pair(2,0)
    }
    else if (place[0][0] != null && place[1][0] == null && place[2][0] != null) {
        return Pair(1,0)
    }
    else if (place[0][0] == null && place[1][0] != null && place[2][0] != null) {
        return Pair(0,0)
    }

    else if (place[1][0] != null && place[1][1] != null && place[1][2] == null) {
        return Pair(1,2)
    }
    else if (place[1][0] != null && place[1][1] == null && place[1][2] != null) {
        return Pair(1,1)
    }
    else if (place[1][0] == null && place[1][1] != null && place[1][2] != null) {
        return Pair(1,0)
    }

    else if (place[0][1] != null && place[1][1] != null && place[2][1] == null) {
        return Pair(2,1)
    }
    else if (place[0][1] != null && place[1][1] == null && place[2][1] != null) {
        return Pair(1,1)
    }
    else if (place[0][1] == null && place[1][1] != null && place[2][1] != null) {
        return Pair(0,1)
    }

    else if (place[2][0] != null && place[2][1] != null && place[2][2] == null) {
        return Pair(2,2)
    }
    else if (place[2][0] != null && place[2][1] == null && place[2][2] != null) {
        return Pair(2,1)
    }
    else if (place[2][0] == null && place[2][1] != null && place[2][2] != null) {
        return Pair(2,0)
    }

    else if (place[0][2] != null && place[1][2] != null && place[2][2] == null) {
        return Pair(2,2)
    }
    else if (place[0][2] != null && place[1][2] == null && place[2][2] != null) {
        return Pair(1,2)
    }
    else if (place[0][2] == null && place[1][2] != null && place[2][2] != null) {
        return Pair(0,2)
    }


    else if (place[0][0] != null && place[1][1] != null && place[2][2] == null) {
        return Pair(2,2)
    }
    else if (place[0][0] != null && place[1][1] == null && place[2][2] != null) {
        return Pair(1,1)
    }
    else if (place[0][0] == null && place[1][1] != null && place[2][2] != null) {
        return Pair(0,0)
    }

    else if (place[0][2] != null && place[1][1] != null && place[2][0] == null) {
        return Pair(2,0)
    }
    else if(place[0][2] != null && place[1][1] == null && place[2][0] != null) {
        return Pair(1,1)
    }
    else if(place[0][2] == null && place[1][1] != null && place[2][0] != null) {
        return Pair(0,2)
    }
    else if(place[1][1] == null){
        return Pair(1,1)
    }
    else {
        return Pair(0,0)
    }
}


fun prostoriStavljanja1(place: Array<Array<Boolean?>>): Pair<Int, Int>? {
    for (i in 0..2) {
        for (j in 0..2) {
            if (place[i][j] == null && place[i].filterNotNull().size == 2) {
                return Pair(i, j)
            }
            if (place[j][i] == null && place.map { it[i] }.filterNotNull().size == 2) {
                return Pair(j, i)
            }
        }
    }
    if (place[1][1] == null) {
        return Pair(1, 1)
    }
    else {
        return Pair(1,0)
    }
}



fun jelPobjedaAI(place: Array<Array<Boolean?>>): Pair<Int, Int> {
    println("kaka")
    var rezultat = Pair(-1, -1)
    for(i in 0..2) {
        for(j in 0..2) {
            if(place[i][j] == null) {
                place[i][j] = false
                if(checkEndGame(place)==true) {
                    println("hihi")
                    rezultat = Pair(i,j)
                    place[i][j] = null
                    return rezultat
                }
                place[i][j] = null
            }
        }
    }

    //MainActivity.playerTurn = !MainActivity.playerTurn

    for(i in 0..2) {
        for(j in 0..2) {
            if(place[i][j] == null) {
                place[i][j] = true
                if(checkEndGame(place)==true) {
                    println("haha")
                    rezultat = Pair(i,j)
                    place[i][j] = null
                    return rezultat
                }
                place[i][j] = null
            }
        }
    }
    //MainActivity.playerTurn = !MainActivity.playerTurn
    println("kakadudu")
    rezultat = prostoriStavljanja(place)
    return rezultat
}


enum class Win {
    PLAYER, AI, DRAW
}

fun checkEndGame1(m: Array<Boolean?>): Win? {
    var win: Win? = null
    if (
        (m[0] == true && m[1] == true && m[2] == true) ||
        (m[3] == true && m[4] == true && m[5] == true) ||
        (m[6] == true && m[7] == true && m[8] == true) ||
        (m[0] == true && m[3] == true && m[6] == true) ||
        (m[1] == true && m[4] == true && m[7] == true) ||
        (m[2] == true && m[5] == true && m[8] == true) ||
        (m[0] == true && m[4] == true && m[8] == true) ||
        (m[2] == true && m[4] == true && m[6] == true)
    )
        win = Win.PLAYER



    if (
        (m[0] == false && m[1] == false && m[2] == false) ||
        (m[3] == false && m[4] == false && m[5] == false) ||
        (m[6] == false && m[7] == false && m[8] == false) ||
        (m[0] == false && m[3] == false && m[6] == false) ||
        (m[1] == false && m[4] == false && m[7] == false) ||
        (m[2] == false && m[5] == false && m[8] == false) ||
        (m[0] == false && m[4] == false && m[8] == false) ||
        (m[2] == false && m[4] == false && m[6] == false)
    )
        win = Win.AI

    if (win == null) {
        var available = false
        for (i in 0..8) {
            if(m[i] == null)
                available = true
        }
        if(!available)
            win = Win.DRAW
    }
    println(win)
    return win
}