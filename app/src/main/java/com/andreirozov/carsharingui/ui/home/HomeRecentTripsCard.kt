package com.andreirozov.carsharingui.ui.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.navigation.NavHostController
import com.andreirozov.carsharingui.R
import com.andreirozov.carsharingui.ui.theme.Black
import com.andreirozov.carsharingui.ui.theme.Mint

@Composable
fun HomeRecentTripsCard(
    navHostController: NavHostController,
    isVisible: Boolean,
    animationDuration: Int
) {
    // Key for restart pointer input
    var pointerInputKey by rememberSaveable { mutableStateOf(false) }

    // State of card
    var isPressed by rememberSaveable { mutableStateOf(false) }

    // Scale of card
    val scale by animateFloatAsState(if (isPressed) 0.96f else 1f)

    AnimatedVisibility(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp),
        visible = isVisible,
        enter = scaleIn(
            animationSpec = tween(
                durationMillis = animationDuration,
                easing = LinearOutSlowInEasing
            )
        ),
        exit = scaleOut(
            animationSpec = tween(
                durationMillis = animationDuration,
                easing = LinearOutSlowInEasing
            )
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
                            navHostController.navigate("car")
                        }

                        // Card released, update state
                        isPressed = false

                        // Update key
                        pointerInputKey = !pointerInputKey
                    }
                },
            shape = RoundedCornerShape(32.dp)
        ) {
            Column {
                Row(
                    modifier = Modifier.padding(
                        start = 16.dp,
                        top = 16.dp,
                        end = 16.dp,
                        bottom = 8.dp
                    ),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier.weight(1f),
                        text = "recent trips"
                    )

                    Icon(
                        imageVector = Icons.Rounded.ArrowForward,
                        contentDescription = "Refresh icon"
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        modifier = Modifier
                            .weight(0.5f)
                            .padding(start = 16.dp),
                    ) {
                        Text(
                            text = "Mercedes Benz C 300 Coupe",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Button(
                            modifier = Modifier
                                .fillMaxWidth(0.75f)
                                .weight(1f)
                                .wrapContentHeight(Alignment.CenterVertically)
                                .height(56.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Mint,
                                contentColor = Black
                            ),
                            shape = RoundedCornerShape(20.dp),
                            onClick = {

                            }
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.Refresh,
                                contentDescription = "Refresh icon"
                            )
                        }
                    }

                    Image(
                        modifier = Modifier.weight(0.5f),
                        alignment = Alignment.CenterEnd,
                        painter = painterResource(id = R.drawable.mb_300),
                        contentDescription = "Mercedes Benz C 300 Coupe photo"
                    )
                }
            }
        }
    }
}