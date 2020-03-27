package com.mmendira.mygame.ui.main.level

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mmendira.mygame.model.Game

class LevelViewModel : ViewModel() {
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
