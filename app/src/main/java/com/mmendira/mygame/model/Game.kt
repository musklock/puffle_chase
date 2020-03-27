package com.mmendira.mygame.model

class Game {

    val levelsList = listOf("Easy", "Medium", "Hard")
    val levelsMap = mapOf("Easy" to "Easy level",
                            "Medium" to "Medium level",
                            "Hard" to "Hard level")

    fun levelSelected(level: String): String?{
        return levelsMap.getValue(level)

    }

}