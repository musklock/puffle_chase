package com.mmendira.mygame.ui.main.settings

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.mmendira.mygame.Sounds
import com.mmendira.mygame.model.Game


class SettingsViewModel(application: Application) : AndroidViewModel(application){
    private val sounds = Sounds(getApplication<Application>().assets)
    val soundsList = sounds.sounds

    fun setMode(mode: String){
        Game.mode = mode
    }

    fun playSound(){
        val welcomeTrack = "opening_music"
        for (sound in soundsList){
            if (sound.title.equals("settings")){
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

    override fun onCleared() {
        super.onCleared()
        sounds.release()
    }
}
