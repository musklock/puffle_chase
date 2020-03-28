package com.mmendira.mygame.ui.main.game

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController

import com.mmendira.mygame.R
import com.mmendira.mygame.model.Game
import com.mmendira.mygame.model.Location
import kotlinx.android.synthetic.main.game_fragment.*

class GameFragment : Fragment() {
    var game = Game()
    private lateinit var location_1: Location
    private lateinit var location_2: Location
    private lateinit var location_3: Location
    private lateinit var location_4: Location
    private lateinit var location_5: Location
    private lateinit var location_6: Location
    private lateinit var location_7: Location
    private lateinit var location_8: Location
    private lateinit var location_9: Location
    private lateinit var location_10: Location


    fun initiateLocations(){
        if (game.level == "Easy"){
            location_1 = Location(Game.Tile.PUFFLE)
            location_2 = Location(Game.Tile.GOOD)
            location_3 = Location(Game.Tile.GOOD)
            location_4 = Location(Game.Tile.WINNING)
            location_5 = Location(Game.Tile.EMPTY)
            location_6 = Location(Game.Tile.EMPTY)
            location_7 = Location(Game.Tile.EMPTY)
            location_8 = Location(Game.Tile.EMPTY)
            location_9 = Location(Game.Tile.EMPTY)
            location_10 = Location(Game.Tile.EMPTY)

        }else if (game.level == "Medium"){
            location_1 = Location(Game.Tile.PUFFLE)
            location_2 = Location(Game.Tile.BAD)
            location_7 = Location(Game.Tile.GOOD)
            location_8 = Location(Game.Tile.GOOD)
            location_9 = Location(Game.Tile.GOOD)
            location_3 = Location(Game.Tile.GOOD)
            location_4 = Location(Game.Tile.WINNING)
            location_5 = Location(Game.Tile.EMPTY)
            location_6 = Location(Game.Tile.EMPTY)
            location_10 = Location(Game.Tile.EMPTY)

        }else if (game.level == "Hard"){
            location_5 = Location(Game.Tile.BAD)
            location_6 = Location(Game.Tile.GOOD)
            location_7 = Location(Game.Tile.BAD)
            location_2 = Location(Game.Tile.GOOD)
            location_8 = Location(Game.Tile.GOOD)
            location_9 = Location(Game.Tile.GOOD)
            location_3 = Location(Game.Tile.BAD)
            location_10 = Location(Game.Tile.GOOD)
            location_1 = Location(Game.Tile.PUFFLE)
            location_4 = Location(Game.Tile.WINNING)
        }

    }

    companion object {
        fun newInstance() = GameFragment()
    }

    private lateinit var viewModel: GameViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.game_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(GameViewModel::class.java)
        initiateLocations()
        makeBoard()
        game_over.setOnClickListener {
            view.findNavController().navigate(R.id.action_gameFragment_to_gameOverFragment)
            viewModel.transitionSound()
        }
        location1.setOnClickListener{
            viewModel.playSound()

        }
        location2.setOnClickListener{
            viewModel.playSound()
        }
        location3.setOnClickListener{
            viewModel.playSound()
        }
        location4.setOnClickListener{
            location3.setImageResource(R.drawable.fire)
            view.findNavController().navigate(R.id.action_gameFragment_to_gameOverFragment)
            viewModel.transitionSound()
        }
        location3.setOnClickListener{
            viewModel.playSound()
        }
        location3.setOnClickListener{
            viewModel.playSound()
        }
        location3.setOnClickListener{
            viewModel.playSound()
        }
        location3.setOnClickListener{
            viewModel.playSound()
        }
        location3.setOnClickListener{

        }
        location3.setOnClickListener{

        }
        location3.setOnClickListener{

        }
        location3.setOnClickListener{

        }
    }

    private fun makeBoard(){
        location1.setImageResource(game.getImageId(location_1.tile))
        location2.setImageResource(game.getImageId(location_2.tile))
        location3.setImageResource(game.getImageId(location_3.tile))
        location4.setImageResource(game.getImageId(location_4.tile))
        location5.setImageResource(game.getImageId(location_5.tile))
        location6.setImageResource(game.getImageId(location_6.tile))
        location7.setImageResource(game.getImageId(location_7.tile))
        location8.setImageResource(game.getImageId(location_8.tile))
        location9.setImageResource(game.getImageId(location_9.tile))
        location10.setImageResource(game.getImageId(location_10.tile))

    }

}
