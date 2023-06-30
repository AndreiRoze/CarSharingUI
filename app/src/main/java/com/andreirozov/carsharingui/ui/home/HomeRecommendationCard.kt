package com.andreirozov.carsharingui.ui.home

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.andreirozov.carsharingui.R
import com.andreirozov.carsharingui.ui.theme.White

@Composable
fun HomeRecommendationCard(isVisible: Boolean, animationDuration: Int) {
    // Key for restart pointer input
    var pointerInputKey by rememberSaveable { mutableStateOf(false) }

    // State of card
    var isPressed by rememberSaveable { mutableStateOf(false) }

    // Scale of card
    val scale by animateFloatAsState(if (isPressed) 0.96f else 1f)

    // Context used for toast
    val context = LocalContext.current

    AnimatedVisibility(
        modifier = Modifier
            .fillMaxWidth()
            .height(240.dp),
        visible = isVisible,
        enter = slideInVertically(
            animationSpec = tween(
                durationMillis = animationDuration,
                easing = LinearOutSlowInEasing
            ),
            initialOffsetY = { it * 2 }
        ),
        exit = slideOutVertically(
            animationSpec = tween(
                durationMillis = animationDuration,
                easing = LinearOutSlowInEasing
            ),
            targetOffsetY = { it * 2 }
        )
    ) {
        Card(
            modifier = Modifier
                .graphicsLayer {
                    scaleX = scale
                    scaleY = scale
                }
                .pointerInput(pointerInputKey) {
                    awaitPointerEventScope {
                        // Card pressed, update state
                        awaitFirstDown(requireUnconsumed = false)
                        isPressed = true

                        // Wait when user release card
                        waitForUpOrCancellation()

                        // Card click events
                        if (this.currentEvent.type == PointerEventType.Release) {
                            Toast
                                .makeText(context, "Recommendation card click", Toast.LENGTH_SHORT)
                                .show()
                        }

                        // Card released, update state
                        isPressed = false

                        // Update key
                        pointerInputKey = !pointerInputKey
                    }
                },
            colors = CardDefaults.cardColors(contentColor = White),
            shape = RoundedCornerShape(32.dp),
        ) {
            Box {
                Image(
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop,
                    painter = painterResource(id = R.drawable.porsche911),
                    contentDescription = "Porsche 911 photo",
                    colorFilter = ColorFilter.colorMatrix(
                        ColorMatrix().apply {
                            setToScale(0.7f, 0.7f, 0.7f, 1f)
                        }
                    )
                )

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            modifier = Modifier.weight(1f),
                            text = "5 most beautiful places to travel"
                        )

                        Icon(
                            imageVector = Icons.Rounded.ArrowForward,
                            contentDescription = "Refresh icon"
                        )
                    }

                    Text(
                        text = "We have put together a small selection of places where you could go on a car"
                    )
                }
            }
        }
    }
}