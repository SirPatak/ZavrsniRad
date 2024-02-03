package com.example.krizickruzic.ui.theme

import android.media.Image.Plane
import com.example.krizickruzic.MainActivity
import com.example.krizickruzic.R

fun checkEndGame(place: Array<Array<Boolean?>>): Boolean? {
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
        return false
    }
}

fun prostoriStavljanja(place: Array<Array<Boolean?>>): Pair<Int, Int> {
    var kraj1 = 0
    var kraj2 = 0
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
        for(i in 0..2) {
            for(j in 0..2) {
                if(place[i][j] == null) {
                    return Pair(i,j)
                }
            }
        }
        for(i in 0..2) {
            for(j in 0..2) {
                if(place[i][j] == false) {
                    kraj1 = i
                    kraj2 = j
                }
            }
        }
        return Pair(kraj1,kraj2)
    }
}

fun jelPobjedaAI(place: Array<Array<Boolean?>>): Pair<Int, Int> {
    var rezultat = Pair(-1, -1)
    for(i in 0..2) {
        for(j in 0..2) {
            if(place[i][j] == null) {
                place[i][j] = false
                if(checkEndGame(place)==true) {
                    rezultat = Pair(i,j)
                    place[i][j] = null
                    return rezultat
                }
                place[i][j] = null
            }
        }
    }

    for(i in 0..2) {
        for(j in 0..2) {
            if(place[i][j] == null) {
                place[i][j] = true
                if(checkEndGame(place)==true) {
                    rezultat = Pair(i,j)
                    place[i][j] = null
                    return rezultat
                }
                place[i][j] = null
            }
        }
    }
    rezultat = prostoriStavljanja(place)
    return rezultat
}