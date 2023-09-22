package com.kukis.horoscoapp.ui.horoscope

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.kukis.horoscoapp.databinding.FragmentHoroscopeBinding
import com.kukis.horoscoapp.domain.model.HoroscopeInfo.Aquarius
import com.kukis.horoscoapp.domain.model.HoroscopeInfo.Aries
import com.kukis.horoscoapp.domain.model.HoroscopeInfo.Cancer
import com.kukis.horoscoapp.domain.model.HoroscopeInfo.Capricorn
import com.kukis.horoscoapp.domain.model.HoroscopeInfo.Gemini
import com.kukis.horoscoapp.domain.model.HoroscopeInfo.Leo
import com.kukis.horoscoapp.domain.model.HoroscopeInfo.Libra
import com.kukis.horoscoapp.domain.model.HoroscopeInfo.Pisces
import com.kukis.horoscoapp.domain.model.HoroscopeInfo.Sagittarius
import com.kukis.horoscoapp.domain.model.HoroscopeInfo.Scorpio
import com.kukis.horoscoapp.domain.model.HoroscopeInfo.Taurus
import com.kukis.horoscoapp.domain.model.HoroscopeInfo.Virgo
import com.kukis.horoscoapp.domain.model.HoroscopeModel
import com.kukis.horoscoapp.ui.horoscope.adapter.HoroscopeAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HoroscopeFragment : Fragment() {
    private val horoscopeViewModel by viewModels<HoroscopeViewModel>()
    private lateinit var horoscopeAdapter: HoroscopeAdapter
    private var _binding: FragmentHoroscopeBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {
        initList()
        initUIState()
    }

    private fun initList() {
        horoscopeAdapter = HoroscopeAdapter(onItemSelected = {
            val type = when (it) {
                Aries -> HoroscopeModel.Aries
                Taurus -> HoroscopeModel.Taurus
                Gemini -> HoroscopeModel.Gemini
                Cancer -> HoroscopeModel.Cancer
                Leo -> HoroscopeModel.Leo
                Virgo -> HoroscopeModel.Virgo
                Libra -> HoroscopeModel.Libra
                Scorpio -> HoroscopeModel.Scorpio
                Sagittarius -> HoroscopeModel.Sagittarius
                Capricorn -> HoroscopeModel.Capricorn
                Aquarius -> HoroscopeModel.Aquarius
                Pisces -> HoroscopeModel.Pisces
            }
            findNavController().navigate(
                HoroscopeFragmentDirections.actionHoroscopeFragmentToHoroscopeDetailActivity(type)
            )
        })

        binding.rvHoroscope.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = horoscopeAdapter
        }
    }

    private fun initUIState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                horoscopeViewModel.horoscope.collect {
                    horoscopeAdapter.updateList(it)
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHoroscopeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
}