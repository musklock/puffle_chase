package com.mmendira.mygame.ui.main.gameover

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.mmendira.mygame.Sounds

class GameOverViewModel (application: Application) : AndroidViewModel(application){
    private val sounds = Sounds(getApplication<Application>().assets)
    val soundsList = sounds.sounds

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
    fun transitionSound(){
        val welcomeTrack = "transition_sound"
        for (sound in soundsList){

            if (sound.title.equals(welcomeTrack)){
                sounds.play(sound)
            }
        }
    }
}
