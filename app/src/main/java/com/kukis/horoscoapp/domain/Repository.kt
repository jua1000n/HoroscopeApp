package com.kukis.horoscoapp.domain

import com.kukis.horoscoapp.data.network.response.PredictionResponse
import com.kukis.horoscoapp.domain.model.PredictionModel

interface Repository {
    suspend fun getPrediction(sign:String): PredictionModel?
}