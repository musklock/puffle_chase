package com.mmendira.mygame.ui.main.game

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.mmendira.mygame.Sounds
import com.mmendira.mygame.model.Game

class GameViewModel(application: Application) : AndroidViewModel(application){
    private val sounds = Sounds(getApplication<Application>().assets)
    val soundsList = sounds.sounds
    var game = Game()
    val GOOD = Game.Tile.GOOD
    val BAD = Game.Tile.BAD
    val EMPTY = Game.Tile.EMPTY
    val PUFFLE = Game.Tile.PUFFLE
    val WINNING = Game.Tile.WINNING

    fun playSound(){
        val welcomeTrack = "move_sound"
        for (sound in soundsList){

            if (sound.title.equals(welcomeTrack)){
                sounds.play(sound)
            }
        }
    }

    fun playSoundWin(){
        val welcomeTrack = "win"
        for (sound in soundsList){
            if (sound.title.equals(welcomeTrack)){
                sounds.play(sound)
            }
        }
    }

    fun playSoundLose(){
        val welcomeTrack = "lose"
        for (sound in soundsList){
            if (sound.title.equals(welcomeTrack)){
                sounds.play(sound)
            }
        }
    }

    fun getImageId(tile: Game.Tile): Int{
        return game.getImageId(tile)

    }
}
