package com.ravikrs.kmp.weather.model

import kotlinx.serialization.Serializable

@Serializable
data class HourlyUnits(
    val pressure_msl: String,
    val relativehumidity_2m: String,
    val temperature_2m: String,
    val time: String,
    val weathercode: String,
    val windspeed_10m: String
)