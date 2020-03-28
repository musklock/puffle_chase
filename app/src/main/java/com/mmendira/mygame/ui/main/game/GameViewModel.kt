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
}
