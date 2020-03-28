package com.mmendira.mygame.ui.main.settings

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController

import com.mmendira.mygame.R
import kotlinx.android.synthetic.main.level_fragment.*
import kotlinx.android.synthetic.main.settings_fragment.*

class SettingsFragment : Fragment() {

    companion object {
        fun newInstance() = SettingsFragment()
    }

    private lateinit var viewModel: SettingsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.settings_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        back_btn.setOnClickListener {
            view.findNavController().navigate(R.id.action_settingsFragment_to_welcomeFragment)
        }
        radioGroup.setOnCheckedChangeListener { _, id ->
            when (id) {
                R.id.fire_btn -> fireBackground()
                R.id.ice_btn -> iceBackground()
            }
        }
    }

    fun fireBackground(){
        settings_layout.setBackgroundResource(R.drawable.fire_background)
    }
    fun iceBackground(){
        settings_layout.setBackgroundResource(R.drawable.ice_background)
    }

}
