package com.andreirozov.carsharingui.ui.car

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CarScreen() {
    // Visibility of cards, required for animations
    var isVisible by rememberSaveable { mutableStateOf(false) }

    // Engine HP for card
    var engine by rememberSaveable { mutableIntStateOf(0) }

    // Temperature for card
    var temperature by rememberSaveable { mutableIntStateOf(0) }

    // Gas for card
    var gas by rememberSaveable { mutableIntStateOf(0) }

    // Duration of animations
    val animationDuration = 800

    // Launch animation only when screen launched
    LaunchedEffect(key1 = Unit) {
        isVisible = true
        engine = 255
        temperature = 20
        gas = 40
    }

    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(it)
                .padding(start = 16.dp, top = 16.dp, bottom = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.End
        ) {
            CarHeader(
                isVisible = isVisible,
                animationDuration = animationDuration
            )

            CarDataWidget(
                isVisible = isVisible,
                animationDuration = animationDuration,
                engine = engine,
                temperature = temperature,
                gas = gas
            )

            CarSlider(
                isVisible = isVisible,
                animationDuration = animationDuration
            )
        }
    }
}