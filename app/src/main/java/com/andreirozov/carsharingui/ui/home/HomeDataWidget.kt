package com.andreirozov.carsharingui.ui.home

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.andreirozov.carsharingui.R
import com.andreirozov.carsharingui.ui.theme.Black
import com.andreirozov.carsharingui.ui.theme.Blue
import com.andreirozov.carsharingui.ui.theme.LightBlue

@Composable
fun HomeDataWidget(isVisible: Boolean, animationDuration: Int, distance: Int) {
    // Animation for distance text
    val distanceCounter by animateIntAsState(
        targetValue = distance,
        label = "Distance counter animation",
        animationSpec = tween(
            durationMillis = animationDuration * 2,
            easing = LinearOutSlowInEasing
        )
    )

    Row {
        AnimatedVisibility(
            modifier = Modifier
                .weight(0.5f)
                .height(180.dp),
            visible = isVisible,
            enter = slideInHorizontally(
                animationSpec = tween(
                    durationMillis = animationDuration,
                    easing = LinearOutSlowInEasing
                ),
                initialOffsetX = { -(it * 2) }
            ),
            exit = slideOutHorizontally(
                animationSpec = tween(
                    durationMillis = animationDuration,
                    easing = LinearOutSlowInEasing
                ),
                targetOffsetX = { -(it * 2) }
            )
        ) {
            DistanceCard(distance = distanceCounter)
        }

        Spacer(
            modifier = Modifier.width(8.dp)
        )

        AnimatedVisibility(
            modifier = Modifier
                .weight(0.5f)
                .height(180.dp),
            visible = isVisible,
            enter = slideInHorizontally(
                animationSpec = tween(
                    durationMillis = animationDuration,
                    easing = LinearOutSlowInEasing
                ),
                initialOffsetX = { it * 2 }
            ),
            exit = slideOutHorizontally(
                animationSpec = tween(
                    durationMillis = animationDuration,
                    easing = LinearOutSlowInEasing
                ),
                targetOffsetX = { it * 2 }
            )
        ) {
            MapCard()
        }
    }
}

@Composable
private fun DistanceCard(distance: Int) {
    // Key for restart pointer input
    var pointerInputKey by rememberSaveable { mutableStateOf(false) }

    // State of card
    var isPressed by rememberSaveable { mutableStateOf(false) }

    // Scale of card
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.96f else 1f,
        label = "Scale animation"
    )

    // Context used for toast
    val context = LocalContext.current

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
                            .makeText(context, "Distance card click", Toast.LENGTH_SHORT)
                            .show()
                    }

                    // Card released, update state
                    isPressed = false

                    // Update key
                    pointerInputKey = !pointerInputKey
                }
            },
        shape = RoundedCornerShape(32.dp),
        colors = CardDefaults.cardColors(containerColor = LightBlue, contentColor = Black)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(id = R.drawable.car_icon),
                contentDescription = "Car icon",
                tint = Blue
            )

            Text(
                text = distance.toString(),
                fontSize = 48.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "total km"
            )
        }
    }
}

@Composable
private fun MapCard() {
    // Key for restart pointer input
    var pointerInputKey by rememberSaveable { mutableStateOf(false) }

    // State of card
    var isPressed by rememberSaveable { mutableStateOf(false) }

    // Scale of card
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.96f else 1f,
        label = "Scale animation"
    )

    // Context used for toast
    val context = LocalContext.current

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
                            .makeText(context, "Map card click", Toast.LENGTH_SHORT)
                            .show()
                    }

                    // Card released, update state
                    isPressed = false

                    // Update key
                    pointerInputKey = !pointerInputKey
                }
            },
        shape = RoundedCornerShape(32.dp)
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            painter = painterResource(id = R.drawable.map),
            contentDescription = "Map photo",
            colorFilter = ColorFilter.colorMatrix(
                ColorMatrix().apply {
                    setToScale(0.8f, 0.8f, 0.8f, 1f)
                }
            )
        )
    }
}