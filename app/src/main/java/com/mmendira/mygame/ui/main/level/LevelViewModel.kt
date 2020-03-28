package com.mmendira.mygame.ui.main.level

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mmendira.mygame.Sounds
import com.mmendira.mygame.model.Game

class LevelViewModel(application: Application) : AndroidViewModel(application){
    private val sounds = Sounds(getApplication<Application>().assets)
    val soundsList = sounds.sounds

    fun playSound(){
        val welcomeTrack = "level"
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

    override fun onCleared() {
        super.onCleared()
        sounds.release()
    }
    val game= Game()
    val levels_list = game.levelsList

    private val _level_selected = MutableLiveData<String>()
    var levelSelected: LiveData<String> = _level_selected

    fun getLevelSelected(level: String): String? {
        val def = game.levelSelected(level)
        _level_selected.value = game.levelSelected(level)
        return def
    }
}
