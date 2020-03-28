package com.mmendira.mygame.ui.main.level

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.mmendira.mygame.R
import kotlinx.android.synthetic.main.level_fragment.*
import kotlinx.android.synthetic.main.level_item.view.*

class LevelFragment : Fragment() {

    companion object {
        fun newInstance() = LevelFragment()
    }

    private lateinit var viewModel: LevelViewModel
    private lateinit var levelsRecycler: RecyclerView
    private lateinit var levelSelected: String


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.level_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel = ViewModelProvider(this).get(LevelViewModel::class.java)

        levelsRecycler = view.findViewById(R.id.level_recycler)
        levelsRecycler.layoutManager = LinearLayoutManager(context)
        levelsRecycler.adapter = LevelsAdapter(viewModel.levels_list)

        viewModel.levelSelected.observe(viewLifecycleOwner, Observer {
            level_selected.text = it.toString()
            levelSelected = it.toString()
        })

        play_game.setOnClickListener {
            val actionResults =
                LevelFragmentDirections.actionLevelFragmentToGameFragment(levelSelected)
            Navigation.findNavController(view).navigate(actionResults)
            viewModel.transitionSound()
        }
    }

    private inner class LevelsViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {

        private lateinit var level: String
        private val termTextView: TextView = itemView.levelName

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            viewModel.getLevelSelected(level)
            levelSelected = level
            viewModel.playSound()
        }

        fun bind(level: String) {
            this.level = level
            termTextView.text = level
        }
    }
    private inner class LevelsAdapter(private val terms: List<String>) :
        RecyclerView.Adapter<LevelsViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LevelsViewHolder {
            val view = layoutInflater.inflate(R.layout.level_item, parent, false)
            return LevelsViewHolder(view)
        }

        override fun getItemCount() = terms.size

        override fun onBindViewHolder(holder: LevelsViewHolder, position: Int) {
            holder.bind(terms[position])
        }
    }

}
