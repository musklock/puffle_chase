package com.mmendira.mygame.ui.main.gameover

import android.animation.ValueAnimator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController

import com.mmendira.mygame.R
import com.mmendira.mygame.model.Game
import com.mmendira.mygame.ui.main.game.GameFragmentArgs
import kotlinx.android.synthetic.main.game_fragment.*
import kotlinx.android.synthetic.main.game_over_fragment.*

class GameOverFragment : Fragment() {

    companion object {
        fun newInstance() = GameOverFragment()
    }

    private lateinit var viewModel: GameOverViewModel
    private lateinit var animate: ImageView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.game_over_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(GameOverViewModel::class.java)
        animate = view.findViewById(R.id.to_animate)
        animate()
        arguments?.let { args ->
            val safeArgs = GameOverFragmentArgs.fromBundle(args)
            val result = safeArgs.result
            displayResult(result)
        }
        play_again.setOnClickListener {
            view.findNavController().navigate(R.id.action_gameOverFragment_to_welcomeFragment)
            viewModel.transitionSound()
        }

    }
    private fun displayResult(result: Int){
        if (result == 0){
            result_image.setImageResource(R.drawable.raincloud)
            result_textView.text = resources.getText(R.string.game_lost)
        }else{
            result_image.setImageResource(R.drawable.puffle2)
            result_textView.text = resources.getText(R.string.game_won)
        }
    }

    private fun animate(){
        val animator =
            ValueAnimator.ofFloat(-5000f, 0f)
        animator.addUpdateListener {
            val value = it.animatedValue as Float
            animate.translationX = value
        }

        animator.duration = 2000L
        animator.interpolator = LinearInterpolator()
        animator.start()

    }

}
