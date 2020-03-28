package com.mmendira.mygame.ui.main.welcome

import android.app.Application
import android.content.res.AssetFileDescriptor
import android.content.res.AssetManager
import android.media.SoundPool
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController

import com.mmendira.mygame.R
import com.mmendira.mygame.Sounds.Companion.MAX_STREAMS
import kotlinx.android.synthetic.main.welcome_fragment.*

class WelcomeFragment : Fragment() {

    companion object {
        fun newInstance() = WelcomeFragment()
        const val MAX_STREAMS = 5
    }

    private lateinit var viewModel: WelcomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.welcome_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(WelcomeViewModel::class.java)

        play.setOnClickListener {
            view.findNavController().navigate(R.id.action_welcomeFragment_to_levelFragment)
            viewModel.transitionSound()
        }
        settings.setOnClickListener {
            view.findNavController().navigate(R.id.action_welcomeFragment_to_settingsFragment)
            viewModel.transitionSound()
        }
        viewModel.playSound()
        play_music.setOnClickListener {
            viewModel.playSound()
        }

    }

}
