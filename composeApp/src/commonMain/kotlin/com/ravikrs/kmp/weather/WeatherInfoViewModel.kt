package com.ravikrs.kmp.weather

import androidx.compose.runtime.collectAsState
import com.ravikrs.kmp.weather.model.WeatherResponse
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class WeatherInfoUIState(val weatherResponse: WeatherResponse?)

class WeatherInfoViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(WeatherInfoUIState(null))
    val uiState = _uiState.asStateFlow()

    private val httpClient = HttpClient {
        install(ContentNegotiation) {
            json()
        }
    }
    fun updateWeatherData(){
        viewModelScope.launch {
            val weatherResponse = getWeatherData()
            _uiState.update {
                it.copy(weatherResponse = weatherResponse)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        httpClient.close()
    }

    private suspend fun getWeatherData(): WeatherResponse =
        httpClient.get("https://api.open-meteo.com/v1/forecast?latitude=52.52&longitude=13.41&hourly=temperature_2m,weathercode,relativehumidity_2m,windspeed_10m,pressure_msl")
            .body<WeatherResponse>()
}