package com.ravikrs.kmp.weather

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import kmpweather.composeapp.generated.resources.Res
import kmpweather.composeapp.generated.resources.compose_multiplatform

@Composable
@Preview
fun App() {
    MaterialTheme {
        val viewModel = getViewModel(Unit, viewModelFactory { WeatherInfoViewModel() })
        val uiState by viewModel.uiState.collectAsState()

        LaunchedEffect(viewModel) {
            viewModel.updateWeatherData()
        }
        Text(uiState.toString(), Modifier.verticalScroll(rememberScrollState()))

    }
}