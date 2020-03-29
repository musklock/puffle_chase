package com.mmendira.mygame.ui.main.welcome

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import android.widget.Button
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController

import com.mmendira.mygame.R
import kotlinx.android.synthetic.main.welcome_fragment.*

class WelcomeFragment : Fragment() {
    private lateinit var puffle: ImageView

    companion object {
        fun newInstance() = WelcomeFragment()
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
        puffle = view.findViewById(R.id.play_music)

        play_welcome.setOnClickListener {
            view.findNavController().navigate(R.id.action_welcomeFragment_to_levelFragment)
            viewModel.transitionSound()

        }
        settings.setOnClickListener {
            view.findNavController().navigate(R.id.action_welcomeFragment_to_settingsFragment)
            viewModel.transitionSound()
        }
        viewModel.playSound()
        play_music.setOnClickListener {
            animatePuffle()
            viewModel.playSound()
        }


    }

    private fun animatePuffle(){
        val animator_up = ObjectAnimator.ofFloat(puffle, "translationY", 0f, -50f).setDuration(500)
        val animator_down = ObjectAnimator.ofFloat(puffle, "translationY", 0f, 50f).setDuration(500)

        animator_up.interpolator = LinearInterpolator()
        animator_down.interpolator = LinearInterpolator()
        val set = AnimatorSet()
        set.play(animator_up).before(animator_down)
        set.start()

    }




}
