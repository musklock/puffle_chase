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
            viewModel.playSoundWin()
        }
        location1.setOnClickListener{
            if (location_1.lostGame()){
                location_1.wasPrevious = true
                view.findNavController().navigate(R.id.action_gameFragment_to_gameOverFragment)
                viewModel.playSoundLose()
            }
            if (location_1.isValid()){
                location_1.tile = Game.Tile.PUFFLE
                location_1.isNext = false
                viewModel.playSound()
            }

            location_5.isNext = true
            location_7.isNext = true
            location_2.isNext = true

            if (location_2.wasPrevious){
                location_2.tile = Game.Tile.BAD
            }
            if (location_5.wasPrevious){
                location_5.tile = Game.Tile.BAD
            }
            if (location_7.wasPrevious){
                location_7.tile = Game.Tile.BAD
            }
            makeBoard()

        }
        location2.setOnClickListener{
            location_2.isNext = true
            if (location_2.lostGame()){
                view.findNavController().navigate(R.id.action_gameFragment_to_gameOverFragment)
                viewModel.playSoundLose()
            }
            if (location_2.tile != Game.Tile.BAD){
                location_2.tile = Game.Tile.PUFFLE
                location_2.isNext = false
                location_2.wasPrevious = true
                viewModel.playSound()
            }
            location_1.isNext = true
            location_8.isNext = true
            location_3.isNext = true

            location_1.tile = Game.Tile.BAD

            if (location_8.wasPrevious){
                location_8.tile = Game.Tile.BAD
            }
            if (location_3.wasPrevious){
                location_3.tile = Game.Tile.BAD
            }
            makeBoard()
        }
        location3.setOnClickListener{
            if (location_3.lostGame()){
                view.findNavController().navigate(R.id.action_gameFragment_to_gameOverFragment)
                viewModel.playSoundLose()
            }
            if (location_3.isValid()){
                location_3.tile = Game.Tile.PUFFLE
                location_3.isNext = false
                location_3.wasPrevious = true
                viewModel.playSound()
            }
            location_2.isNext = true
            location_9.isNext = true
            location_4.isNext = true

            if (location_2.wasPrevious){
                location_2.tile = Game.Tile.BAD
            }
            if (location_9.wasPrevious){
                location_9.tile = Game.Tile.BAD
            }
            if (location_4.wasPrevious){
                location_4.tile = Game.Tile.BAD
            }
            makeBoard()
        }
        location4.setOnClickListener{
            if (location_4.isNext){
                view.findNavController().navigate(R.id.action_gameFragment_to_gameOverFragment)
                viewModel.playSoundWin()
            }
            if (location_3.wasPrevious){
                location_3.tile = Game.Tile.BAD
            }
            if (location_9.wasPrevious){
                location_9.tile = Game.Tile.BAD
            }
            if (location_10.wasPrevious){
                location_10.tile = Game.Tile.BAD
            }

        }
        location5.setOnClickListener{
            if (location_5.lostGame()){
                view.findNavController().navigate(R.id.action_gameFragment_to_gameOverFragment)
                viewModel.playSoundLose()
            }
            if (location_5.isValid()){
                location_5.tile = Game.Tile.PUFFLE
                location_5.isNext = false
                location_5.wasPrevious = true
                viewModel.playSound()
            }
            location_1.isNext = true
            location_6.isNext = true

            if (location_1.wasPrevious){
                location_1.tile = Game.Tile.BAD
            }
            if (location_6.wasPrevious){
                location_6.tile = Game.Tile.BAD
            }

            makeBoard()
        }
        location6.setOnClickListener{
            if (location_6.lostGame()){
                view.findNavController().navigate(R.id.action_gameFragment_to_gameOverFragment)
                viewModel.playSoundLose()
            }
            if (location_6.isValid()){
                location_6.tile = Game.Tile.PUFFLE
                location_6.isNext = false
                location_6.wasPrevious = true
                viewModel.playSound()
            }
            location_5.isNext = true
            location_7.isNext = true

            if (location_5.wasPrevious){
                location_5.tile = Game.Tile.BAD
            }
            if (location_7.wasPrevious){
                location_7.tile = Game.Tile.BAD
            }
            makeBoard()
        }
        location7.setOnClickListener{
            location_7.isNext = true
            if (location_7.lostGame()){
                view.findNavController().navigate(R.id.action_gameFragment_to_gameOverFragment)
                viewModel.playSoundLose()
            }
            if (location_7.tile != Game.Tile.BAD){
                location_7.tile = Game.Tile.PUFFLE
                location_7.isNext = false
                location_7.wasPrevious = true
                viewModel.playSound()
            }

            location_1.isNext = true
            location_8.isNext = true
            location_6.isNext = true
            if (location_7.tile != Game.Tile.EMPTY){
                location_1.tile = Game.Tile.BAD
            }
            if (location_8.wasPrevious){
                location_8.tile = Game.Tile.BAD
            }
            if (location_6.wasPrevious){
                location_6.tile = Game.Tile.BAD
            }
            makeBoard()
        }
        location8.setOnClickListener{
            if (location_8.lostGame()){
                view.findNavController().navigate(R.id.action_gameFragment_to_gameOverFragment)
                viewModel.playSoundLose()
            }
            if (location_8.isValid()){
                location_8.tile = Game.Tile.PUFFLE
                location_8.isNext = false
                location_8.wasPrevious = true
                viewModel.playSound()
            }
            location_2.isNext = true
            location_7.isNext = true
            location_9.isNext = true

            if (location_2.wasPrevious){
                location_2.tile = Game.Tile.BAD
            }
            if (location_9.wasPrevious){
                location_9.tile = Game.Tile.BAD
            }
            if (location_7.wasPrevious){
                location_7.tile = Game.Tile.BAD
            }
            makeBoard()
        }
        location9.setOnClickListener{
            if (location_9.lostGame()){
                view.findNavController().navigate(R.id.action_gameFragment_to_gameOverFragment)
                viewModel.playSoundLose()
            }
            if (location_9.isValid()){
                location_9.tile = Game.Tile.PUFFLE
                location_9.isNext = false
                location_9.wasPrevious = true
                viewModel.playSound()
            }
            location_10.isNext = true
            location_8.isNext = true
            location_3.isNext = true

            if (location_3.wasPrevious){
                location_3.tile = Game.Tile.BAD
            }
            if (location_8.wasPrevious){
                location_8.tile = Game.Tile.BAD
            }
            if (location_10.wasPrevious){
                location_10.tile = Game.Tile.BAD
            }
            makeBoard()
        }
        location10.setOnClickListener{
            if (location_10.lostGame()){
                view.findNavController().navigate(R.id.action_gameFragment_to_gameOverFragment)
                viewModel.playSoundLose()
            }
            if (location_10.isValid()){
                location_10.tile = Game.Tile.PUFFLE
                location_10.isNext = false
                location_10.wasPrevious = true
                viewModel.playSound()
            }
            location_9.isNext = true
            location_4.isNext = true

            if (location_9.wasPrevious){
                location_9.tile = Game.Tile.BAD
            }
            if (location_4.wasPrevious){
                location_4.tile = Game.Tile.BAD
            }

            makeBoard()
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
