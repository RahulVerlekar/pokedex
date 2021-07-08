package com.rahulverlekar.pokedex

import androidx.activity.viewModels
import com.rahulverlekar.pokedex.base.BaseActivity
import com.rahulverlekar.pokedex.base.BaseViewModel
import com.rahulverlekar.pokedex.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val viewModel: MainViewModel by viewModels()

    override fun getVM(): BaseViewModel = viewModel
}