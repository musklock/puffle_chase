package com.mmendira.mygame.ui.main.settings

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController

import com.mmendira.mygame.R
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
        viewModel = ViewModelProvider(this).get(SettingsViewModel::class.java)
        back_btn.setOnClickListener {
            view.findNavController().navigate(R.id.action_settingsFragment_to_welcomeFragment)
            viewModel.transitionSound()
        }
        radioGroup.setOnCheckedChangeListener { _, id ->
            when (id) {
                R.id.fire_btn -> fireBackground()
                R.id.ice_btn -> iceBackground()
            }
        }
    }

    fun fireBackground(){
        viewModel.playSound()
        fire_or_ice.setImageResource(R.drawable.fire)
    }
    fun iceBackground(){
        viewModel.playSound()
        fire_or_ice.setImageResource(R.drawable.iceblock)
    }

}
