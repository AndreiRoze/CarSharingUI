package com.andreirozov.carsharingui.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun HomeScreen(navHostController: NavHostController) {
    // Visibility of cards, required for animations
    var isVisible by rememberSaveable { mutableStateOf(false) }

    // Distance for card
    var distance by rememberSaveable { mutableIntStateOf(0) }

    // Duration of animations
    val animationDuration = 800

    // Launch animation only when screen launched
    LaunchedEffect(key1 = Unit) {
        isVisible = true
        distance = 1734
    }

    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(it)
                .padding(16.dp)
        ) {
            HomeHeader(
                isVisible = isVisible,
                animationDuration = animationDuration
            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            HomeRecentTripsCard(
                navHostController = navHostController,
                isVisible = isVisible,
                animationDuration = animationDuration
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            HomeDataWidget(
                isVisible = isVisible,
                animationDuration = animationDuration,
                distance = distance
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            HomeRecommendationCard(
                isVisible = isVisible,
                animationDuration = animationDuration
            )
        }
    }
}





