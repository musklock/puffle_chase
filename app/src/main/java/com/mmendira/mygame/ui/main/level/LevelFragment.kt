package com.mmendira.mygame.ui.main.level

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController

import com.mmendira.mygame.R
import kotlinx.android.synthetic.main.level_fragment.*

class LevelFragment : Fragment() {

    companion object {
        fun newInstance() = LevelFragment()
    }

    private lateinit var viewModel: LevelViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.level_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        play_game.setOnClickListener {
            view.findNavController().navigate(R.id.action_levelFragment_to_gameFragment)
        }
    }

}
