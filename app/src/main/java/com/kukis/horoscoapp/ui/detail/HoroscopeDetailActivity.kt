package com.kukis.horoscoapp.ui.detail

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.navArgs
import com.kukis.horoscoapp.R
import com.kukis.horoscoapp.databinding.ActivityHoroscopeDetailBinding
import com.kukis.horoscoapp.domain.model.HoroscopeModel
import com.kukis.horoscoapp.domain.model.HoroscopeModel.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HoroscopeDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHoroscopeDetailBinding
    private val horoscopeDetailViewModel: HoroscopeDetailViewModel by viewModels()

    private val args: HoroscopeDetailActivityArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHoroscopeDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        horoscopeDetailViewModel.getPredictionVM(args.type)
        initUI()
    }

    private fun initUI() {
        initListeners()
        iniUIState()
    }

    private fun initListeners() {
        binding.ivBack.setOnClickListener { onBackPressed() }
    }

    private fun iniUIState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                horoscopeDetailViewModel.state.collect {
                    when (it) {
                        HoroscopeDetailState.Loading -> loadingState()

                        is HoroscopeDetailState.Success -> successState(it)

                        is HoroscopeDetailState.Error -> errorState(it)

                    }
                }
            }
        }
    }

    private fun loadingState() {
        binding.tvProgressBar.isVisible = true
    }

    private fun successState(state: HoroscopeDetailState.Success) {
        binding.tvProgressBar.isVisible = false
        binding.tvTitle.text = state.sign
        binding.tvBody.text = state.prediction
        binding.ivDetail.setImageResource(
            when(state.horoscopeModel) {
                Aries -> R.drawable.detail_aries
                Taurus -> R.drawable.detail_taurus
                Gemini -> R.drawable.detail_gemini
                Cancer -> R.drawable.detail_cancer
                Leo -> R.drawable.detail_leo
                Virgo -> R.drawable.detail_virgo
                Libra -> R.drawable.detail_libra
                Scorpio -> R.drawable.detail_scorpio
                Sagittarius -> R.drawable.detail_sagittarius
                Capricorn -> R.drawable.detail_capricorn
                Aquarius -> R.drawable.detail_aquarius
                Pisces -> R.drawable.detail_pisces
            }
        )
    }

    private fun errorState(state: HoroscopeDetailState.Error) {
        binding.tvProgressBar.isVisible = false
    }
}