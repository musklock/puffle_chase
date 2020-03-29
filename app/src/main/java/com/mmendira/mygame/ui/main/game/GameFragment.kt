package com.mmendira.mygame.ui.main.game

import android.animation.ValueAnimator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs

import com.mmendira.mygame.R
import com.mmendira.mygame.model.Game
import com.mmendira.mygame.model.Location
import com.mmendira.mygame.ui.main.level.LevelFragmentDirections
import kotlinx.android.synthetic.main.game_fragment.*

class GameFragment : Fragment() {
    private lateinit var puffle: ImageView
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


    fun initiateLocations(level: String){
        if (level == "Easy"){
            location_1 = Location(viewModel.PUFFLE)
            location_2 = Location(viewModel.GOOD)
            location_3 = Location(viewModel.GOOD)
            location_4 = Location(viewModel.WINNING)
            location_5 = Location(viewModel.EMPTY)
            location_6 = Location(viewModel.EMPTY)
            location_7 = Location(viewModel.EMPTY)
            location_8 = Location(viewModel.EMPTY)
            location_9 = Location(viewModel.EMPTY)
            location_10 = Location(viewModel.EMPTY)

        }else if (level == "Medium"){
            location_1 = Location(viewModel.PUFFLE)
            location_2 = Location(viewModel.BAD)
            location_7 = Location(viewModel.GOOD)
            location_8 = Location(viewModel.GOOD)
            location_9 = Location(viewModel.GOOD)
            location_3 = Location(viewModel.GOOD)
            location_4 = Location(viewModel.WINNING)
            location_5 = Location(viewModel.EMPTY)
            location_6 = Location(viewModel.EMPTY)
            location_10 = Location(viewModel.EMPTY)

        }else if (level == "Hard"){
            location_5 = Location(viewModel.BAD)
            location_6 = Location(viewModel.GOOD)
            location_7 = Location(viewModel.BAD)
            location_2 = Location(viewModel.GOOD)
            location_8 = Location(viewModel.GOOD)
            location_9 = Location(viewModel.GOOD)
            location_3 = Location(viewModel.BAD)
            location_10 = Location(viewModel.GOOD)
            location_1 = Location(viewModel.PUFFLE)
            location_4 = Location(viewModel.WINNING)
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
        puffle = view.findViewById(R.id.animated_image)

        arguments?.let { args ->
            val safeArgs = GameFragmentArgs.fromBundle(args)
            val level = safeArgs.level
            initiateLocations(level)
        }
        animate()
        makeBoard()

        win.setOnClickListener {
            val actionResults =
                GameFragmentDirections.actionGameFragmentToGameOverFragment(1)
            Navigation.findNavController(view).navigate(actionResults)
            viewModel.playSoundWin()

        }

        lose.setOnClickListener{
            val actionResults =
                GameFragmentDirections.actionGameFragmentToGameOverFragment(0)
            Navigation.findNavController(view).navigate(actionResults)
            viewModel.playSoundLose()
        }

        location1.setOnClickListener{
            if (location_1.lostGame()){
                location_1.wasPrevious = true
                val actionResults =
                    GameFragmentDirections.actionGameFragmentToGameOverFragment(0)
                Navigation.findNavController(view).navigate(actionResults)
                viewModel.playSoundLose()
            }
            if (location_1.isValid()){
                location_1.tile = viewModel.PUFFLE
                location_1.isNext = false
                viewModel.playSound()
                location_5.isNext = true
                location_7.isNext = true
                location_2.isNext = true
            }

            if (location_2.wasPrevious){
                location_2.tile = viewModel.BAD
            }
            if (location_5.wasPrevious){
                location_5.tile = viewModel.BAD
            }
            if (location_7.wasPrevious){
                location_7.tile = viewModel.BAD
            }
            makeBoard()

        }
        location2.setOnClickListener{
            location_2.isNext = true
            if (location_2.lostGame()){
                val actionResults =
                    GameFragmentDirections.actionGameFragmentToGameOverFragment(0)
                Navigation.findNavController(view).navigate(actionResults)
                viewModel.playSoundLose()
            }
            if (location_2.tile != viewModel.BAD){
                location_2.tile = viewModel.PUFFLE
                location_2.isNext = false
                location_2.wasPrevious = true
                viewModel.playSound()
                location_1.isNext = true
                location_8.isNext = true
                location_3.isNext = true
            }


            location_1.tile = viewModel.BAD

            if (location_8.wasPrevious){
                location_8.tile = viewModel.BAD
            }
            if (location_3.wasPrevious){
                location_3.tile = viewModel.BAD
            }
            makeBoard()
        }
        location3.setOnClickListener{
            if (location_3.lostGame()){
                val actionResults =
                    GameFragmentDirections.actionGameFragmentToGameOverFragment(0)
                Navigation.findNavController(view).navigate(actionResults)
                viewModel.playSoundLose()
            }
            if (location_3.isValid()){
                location_3.tile = viewModel.PUFFLE
                location_3.isNext = false
                location_3.wasPrevious = true
                viewModel.playSound()
                location_2.isNext = true
                location_9.isNext = true
                location_4.isNext = true
            }


            if (location_2.wasPrevious){
                location_2.tile = viewModel.BAD
            }
            if (location_9.wasPrevious){
                location_9.tile = viewModel.BAD
            }
            if (location_4.wasPrevious){
                location_4.tile = viewModel.BAD
            }
            makeBoard()
        }
        location4.setOnClickListener{
            if (location_4.isNext){
                val actionResults =
                    GameFragmentDirections.actionGameFragmentToGameOverFragment(1)
                Navigation.findNavController(view).navigate(actionResults)
                viewModel.playSoundWin()
            }
            if (location_3.wasPrevious){
                location_3.tile = viewModel.BAD
            }
            if (location_9.wasPrevious){
                location_9.tile = viewModel.BAD
            }
            if (location_10.wasPrevious){
                location_10.tile = viewModel.BAD
            }

        }
        location5.setOnClickListener{
            if (location_5.lostGame()){
                val actionResults =
                    GameFragmentDirections.actionGameFragmentToGameOverFragment(0)
                Navigation.findNavController(view).navigate(actionResults)
                viewModel.playSoundLose()
            }
            if (location_5.isValid()){
                location_5.tile = viewModel.PUFFLE
                location_5.isNext = false
                location_5.wasPrevious = true
                viewModel.playSound()
                location_1.isNext = true
                location_6.isNext = true
            }


            if (location_1.wasPrevious){
                location_1.tile = viewModel.BAD
            }
            if (location_6.wasPrevious){
                location_6.tile = viewModel.BAD
            }

            makeBoard()
        }
        location6.setOnClickListener{
            if (location_6.lostGame()){
                val actionResults =
                    GameFragmentDirections.actionGameFragmentToGameOverFragment(0)
                Navigation.findNavController(view).navigate(actionResults)
                viewModel.playSoundLose()
            }
            if (location_6.isValid()){
                location_6.tile = viewModel.PUFFLE
                location_6.isNext = false
                location_6.wasPrevious = true
                viewModel.playSound()
                location_5.isNext = true
                location_7.isNext = true
            }


            if (location_5.wasPrevious){
                location_5.tile = viewModel.BAD
            }
            if (location_7.wasPrevious){
                location_7.tile = viewModel.BAD
            }
            makeBoard()
        }
        location7.setOnClickListener{
            location_7.isNext = true
            if (location_7.lostGame()){
                val actionResults =
                    GameFragmentDirections.actionGameFragmentToGameOverFragment(0)
                Navigation.findNavController(view).navigate(actionResults)
                viewModel.playSoundLose()
            }
            if (location_7.tile != viewModel.BAD){
                location_7.tile = viewModel.PUFFLE
                location_7.isNext = false
                location_7.wasPrevious = true
                viewModel.playSound()
                location_1.isNext = true
                location_8.isNext = true
                location_6.isNext = true
            }


            if (location_7.tile != viewModel.EMPTY){
                location_1.tile = viewModel.BAD
            }
            if (location_8.wasPrevious){
                location_8.tile = viewModel.BAD
            }
            if (location_6.wasPrevious){
                location_6.tile = viewModel.BAD
            }
            makeBoard()
        }
        location8.setOnClickListener{
            if (location_8.lostGame()){
                val actionResults =
                    GameFragmentDirections.actionGameFragmentToGameOverFragment(0)
                Navigation.findNavController(view).navigate(actionResults)
                viewModel.playSoundLose()
            }
            if (location_8.isValid()){
                location_8.tile = viewModel.PUFFLE
                location_8.isNext = false
                location_8.wasPrevious = true
                viewModel.playSound()
                location_2.isNext = true
                location_7.isNext = true
                location_9.isNext = true
            }


            if (location_2.wasPrevious){
                location_2.tile = viewModel.BAD
            }
            if (location_9.wasPrevious){
                location_9.tile = viewModel.BAD
            }
            if (location_7.wasPrevious){
                location_7.tile = viewModel.BAD
            }
            makeBoard()
        }
        location9.setOnClickListener{
            if (location_9.lostGame()){
                val actionResults =
                    GameFragmentDirections.actionGameFragmentToGameOverFragment(0)
                Navigation.findNavController(view).navigate(actionResults)
                viewModel.playSoundLose()
            }
            if (location_9.isValid()){
                location_9.tile = viewModel.PUFFLE
                location_9.isNext = false
                location_9.wasPrevious = true
                viewModel.playSound()
                location_10.isNext = true
                location_8.isNext = true
                location_3.isNext = true
            }


            if (location_3.wasPrevious){
                location_3.tile = viewModel.BAD
            }
            if (location_8.wasPrevious){
                location_8.tile = viewModel.BAD
            }
            if (location_10.wasPrevious){
                location_10.tile = viewModel.BAD
            }
            makeBoard()
        }
        location10.setOnClickListener{
            if (location_10.lostGame()){
                val actionResults =
                    GameFragmentDirections.actionGameFragmentToGameOverFragment(0)
                Navigation.findNavController(view).navigate(actionResults)
                viewModel.playSoundLose()
            }
            if (location_10.isValid()){
                location_10.tile = viewModel.PUFFLE
                location_10.isNext = false
                location_10.wasPrevious = true
                viewModel.playSound()
                location_9.isNext = true
                location_4.isNext = true
            }


            if (location_9.wasPrevious){
                location_9.tile = viewModel.BAD
            }
            if (location_4.wasPrevious){
                location_4.tile = viewModel.BAD
            }

            makeBoard()
        }

    }

    private fun makeBoard(){
        location1.setImageResource(viewModel.getImageId(location_1.tile))
        location2.setImageResource(viewModel.getImageId(location_2.tile))
        location3.setImageResource(viewModel.getImageId(location_3.tile))
        location4.setImageResource(viewModel.getImageId(location_4.tile))
        location5.setImageResource(viewModel.getImageId(location_5.tile))
        location6.setImageResource(viewModel.getImageId(location_6.tile))
        location7.setImageResource(viewModel.getImageId(location_7.tile))
        location8.setImageResource(viewModel.getImageId(location_8.tile))
        location9.setImageResource(viewModel.getImageId(location_9.tile))
        location10.setImageResource(viewModel.getImageId(location_10.tile))

    }

    private fun animate(){
        val animator =
            ValueAnimator.ofFloat(0f, 5000f)
        animator.addUpdateListener {
            val value = it.animatedValue as Float
            animated_image.translationX = value
        }

        animator.duration = 2000L
        animator.interpolator = LinearInterpolator()
        animator.start()

    }

}
