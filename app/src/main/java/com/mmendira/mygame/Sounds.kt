package com.mmendira.mygame

import android.app.Application
import android.content.ContentValues.TAG
import android.content.res.AssetFileDescriptor
import android.content.res.AssetManager
import android.media.SoundPool
import android.util.Log
import java.io.IOException


private const val TAG = "Zounds"

data class Sound(val path: String, var sndId: Int? = null) {
    val title = path.split("/")
        .last()
        .removeSuffix(Sounds.FILE_FORMAT)
//        .split("_")
//        .last()
//        .replace("-", " ")
}

class Sounds(private val assets: AssetManager) {

    val sounds: List<Sound>
    private val soundPool = SoundPool.Builder()
        .setMaxStreams(MAX_STREAMS)
        .build()

    init {
        sounds = loadSounds()
    }

    fun release() = soundPool.release()

    fun play(sound: Sound) {
        sound.sndId?.let {
            soundPool.play(
                it,
                1f,
                1f,
                1,
                0,
                1f)
        }
    }

    private fun loadSounds(): List<Sound> {
        var files = emptyArray<String>()
        try {
            assets.list(SOUNDS_DIR)?.let {
                files = it
            }
        } catch (e: Exception) {
            Log.d(TAG, "Couldn't list sound assets!", e)
            return emptyList()
        }

        val sounds = mutableListOf<Sound>()
        files.forEach { filename ->
            val assetPath = "$SOUNDS_DIR/$filename"
            if (assetPath.contains(FILE_FORMAT)) {
                val sound = Sound(assetPath)
                try {
                    val afd: AssetFileDescriptor = assets.openFd(sound.path)
                    sound.sndId = soundPool.load(afd, 1)
                    sounds.add(sound)
                } catch (ioe: IOException) {
                    Log.e(TAG, "Couldn't load sound: $filename", ioe)
                }
            }
        }
        return sounds
    }

    companion object {
        const val FILE_FORMAT = ".wav"
        const val SOUNDS_DIR = "sounds"
        const val MAX_STREAMS = 5
    }
}