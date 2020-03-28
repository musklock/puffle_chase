package com.mmendira.mygame.ui.main.gameover

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController

import com.mmendira.mygame.R
import kotlinx.android.synthetic.main.game_over_fragment.*

class GameOverFragment : Fragment() {

    companion object {
        fun newInstance() = GameOverFragment()
    }

    private lateinit var viewModel: GameOverViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.game_over_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(GameOverViewModel::class.java)
        play_again.setOnClickListener {
            view.findNavController().navigate(R.id.action_gameOverFragment_to_welcomeFragment)
            viewModel.transitionSound()

        }
        win_button.setOnClickListener {
            result_image.setImageResource(R.drawable.puffle2)
            result_textView.text = resources.getText(R.string.game_won)
            viewModel.playSoundWin()
        }
        lose_button.setOnClickListener {
            result_image.setImageResource(R.drawable.raincloud)
            result_textView.text = resources.getText(R.string.game_lost)
            viewModel.playSoundLose()
        }
    }

}
