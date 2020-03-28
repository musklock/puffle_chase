package com.mmendira.mygame.model

class Game {

    val levelsList = listOf("Easy", "Medium", "Hard")
    val levelsMap = mapOf("Easy" to "Easy",
                            "Medium" to "Medium",
                            "Hard" to "Hard")

    fun levelSelected(level: String): String?{
        return levelsMap.getValue(level)

    }

}