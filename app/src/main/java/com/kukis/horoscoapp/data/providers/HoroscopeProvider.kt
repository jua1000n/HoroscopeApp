package com.kukis.horoscoapp.data.providers

import com.kukis.horoscoapp.domain.model.HoroscopeInfo
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
import javax.inject.Inject


class HoroscopeProvider @Inject constructor() {
    fun getHoroscopes(): List<HoroscopeInfo> {
        return listOf(
            Aries,
            Taurus,
            Gemini,
            Cancer,
            Leo,
            Virgo,
            Libra,
            Scorpio,
            Sagittarius,
            Capricorn,
            Aquarius,
            Pisces
        )
    }
}