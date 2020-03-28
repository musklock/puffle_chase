package com.mmendira.mygame.model

import com.mmendira.mygame.R

class Location(tile: Game.Tile){
    var tile = tile
    var isNext = false
    fun isValid():Boolean{
        if (tile == Game.Tile.GOOD && isNext){
            return true
        }
        return false
    }
    fun lostGame(): Boolean{
        if (isNext && tile == Game.Tile.BAD){
            return true
        }
        return false
    }

}

class Game {

    val levelsList = listOf("Easy", "Medium", "Hard")
    val levelsMap = mapOf("Easy" to "Easy",
                            "Medium" to "Medium",
                            "Hard" to "Hard")
    var level: String
    var mode:  String


    enum class Tile{
        GOOD, BAD, WINNING, EMPTY, PUFFLE
    }

    init{
        mode = "fire"
        level = "Hard"
    }

    fun levelSelected(level: String): String?{
        return levelsMap.getValue(level)
    }

    fun getImageId(tile: Tile) = when (tile) {
        Tile.GOOD -> R.drawable.tile
        Tile.WINNING -> R.drawable.winningtile
        Tile.EMPTY -> 0
        Tile.BAD -> fireOrIce()
        Tile.PUFFLE -> R.drawable.puffle
    }

    private fun fireOrIce(): Int{
        if (mode == "fire"){
            return R.drawable.fire
        }else{
            return R.drawable.iceblock
        }
    }

}