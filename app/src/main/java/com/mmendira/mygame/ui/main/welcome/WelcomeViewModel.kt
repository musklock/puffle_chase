package com.mmendira.mygame.ui.main.welcome

import android.app.Application
import android.app.PendingIntent.getActivity
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import com.mmendira.mygame.Sounds
private const val TAG = "Zounds"

class WelcomeViewModel(application: Application) : AndroidViewModel(application){
    private val sounds = Sounds(getApplication<Application>().assets)
    val soundsList = sounds.sounds

    fun playSound(){
        val welcomeTrack = "opening_music"
        for (sound in soundsList){
            Log.e(TAG, "Couldn't list sound assets! $sound.title")

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



}
