package com.kukis.horoscoapp.data

import android.util.Log
import com.kukis.horoscoapp.data.network.HoroscopeApiService
import com.kukis.horoscoapp.data.network.response.PredictionResponse
import com.kukis.horoscoapp.domain.Repository
import com.kukis.horoscoapp.domain.model.PredictionModel
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val apiService: HoroscopeApiService) : Repository {
    override suspend fun getPrediction(sign: String):PredictionModel? {
        kotlin.runCatching { apiService.getHoroscope(sign) }
            .onSuccess { return it.toDomain() }
            .onFailure { Log.i("Retrofit", "Error al traer el getPrediction ${it.message}") }
        return null
    }
}