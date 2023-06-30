package com.andreirozov.carsharingui.ui.car

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.andreirozov.carsharingui.R

@Composable
fun CarSlider(
    isVisible: Boolean,
    animationDuration: Int
) {
    // Key for restart pointer input
    var pointerInputKey by rememberSaveable { mutableStateOf(false) }

    // State of card
    var isPressed by rememberSaveable { mutableStateOf(false) }

    // State of card size
    var isExpanded by rememberSaveable { mutableStateOf(true) }

    // Scale of card
    val scale by animateFloatAsState(if (isPressed) 0.96f else 1f)

    AnimatedVisibility(
        visible = isVisible,
        enter = slideInVertically(
            animationSpec = tween(
                durationMillis = animationDuration,
                delayMillis = animationDuration + 300,
                easing = LinearOutSlowInEasing
            ),
            initialOffsetY = { (it * 2) }
        ),
        exit = slideOutVertically(
            animationSpec = tween(
                durationMillis = animationDuration,
                delayMillis = animationDuration + 300,
                easing = LinearOutSlowInEasing
            ),
            targetOffsetY = { (it * 2) }
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
                        awaitFirstDown(requireUnconsumed = true)
                        isPressed = true

                        // Wait when user release card
                        waitForUpOrCancellation()

                        // Card click events
                        if (this.currentEvent.type == PointerEventType.Release) {
                            isExpanded = !isExpanded
                        }

                        // Card released, update state
                        isPressed = false

                        // Update key
                        pointerInputKey = !pointerInputKey
                    }
                }
                .height(72.dp)
                .padding(end = 16.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Row(
                modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 16.dp),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier.animateContentSize()
                ) {
                    if (isExpanded) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = "Unlock the car",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Box(
                    modifier = Modifier.animateContentSize()
                ) {
                    Icon(
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .size(40.dp),
                        painter = painterResource(id = if (isExpanded) R.drawable.lock_icon else R.drawable.unlock_icon),
                        contentDescription = "Lock icon"
                    )
                }
            }
        }
    }
}