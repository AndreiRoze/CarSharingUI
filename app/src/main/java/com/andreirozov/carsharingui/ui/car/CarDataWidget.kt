package com.andreirozov.carsharingui.ui.car

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.andreirozov.carsharingui.R
import com.andreirozov.carsharingui.ui.theme.Black
import com.andreirozov.carsharingui.ui.theme.Blue
import com.andreirozov.carsharingui.ui.theme.LightBlue
import com.andreirozov.carsharingui.ui.theme.LightMint
import com.andreirozov.carsharingui.ui.theme.Mint

@Composable
fun CarDataWidget(
    isVisible: Boolean,
    animationDuration: Int,
    engine: Int,
    temperature: Int,
    gas: Int
) {
    // Animation for engine text
    val engineCounter by animateIntAsState(
        targetValue = engine,
        animationSpec = tween(
            durationMillis = animationDuration * 2,
            easing = LinearOutSlowInEasing
        )
    )

    // Animation for temperature text
    val temperatureCounter by animateIntAsState(
        targetValue = temperature,
        animationSpec = tween(
            durationMillis = animationDuration * 2,
            easing = LinearOutSlowInEasing
        )
    )

    // Animation for gas text
    val gasCounter by animateIntAsState(
        targetValue = gas,
        animationSpec = tween(
            durationMillis = animationDuration * 2,
            easing = LinearOutSlowInEasing
        )
    )

    Row(
        verticalAlignment = Alignment.Bottom
    ) {
        Column(
            modifier = Modifier.weight(0.45f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            AnimatedVisibility(
                modifier = Modifier.height(180.dp),
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
                EngineCard(
                    engine = engineCounter
                )
            }

            AnimatedVisibility(
                modifier = Modifier.height(180.dp),
                visible = isVisible,
                enter = slideInHorizontally(
                    animationSpec = tween(
                        durationMillis = animationDuration,
                        delayMillis = 100,
                        easing = LinearOutSlowInEasing
                    ),
                    initialOffsetX = { -(it * 2) }
                ),
                exit = slideOutHorizontally(
                    animationSpec = tween(
                        durationMillis = animationDuration,
                        delayMillis = 100,
                        easing = LinearOutSlowInEasing
                    ),
                    targetOffsetX = { -(it * 2) }
                )
            ) {
                TemperatureCard(
                    temperature = temperatureCounter
                )
            }

            AnimatedVisibility(
                modifier = Modifier.height(180.dp),
                visible = isVisible,
                enter = slideInHorizontally(
                    animationSpec = tween(
                        durationMillis = animationDuration,
                        delayMillis = 200,
                        easing = LinearOutSlowInEasing
                    ),
                    initialOffsetX = { -(it * 2) }
                ),
                exit = slideOutHorizontally(
                    animationSpec = tween(
                        durationMillis = animationDuration,
                        delayMillis = 200,
                        easing = LinearOutSlowInEasing
                    ),
                    targetOffsetX = { -(it * 2) }
                )
            ) {
                GasCard(
                    gas = gasCounter
                )
            }
        }

        AnimatedVisibility(
            modifier = Modifier
                .weight(0.55f)
                .height(556.dp),
            visible = isVisible,
            enter = slideInVertically(
                animationSpec = tween(
                    durationMillis = animationDuration + 300,
                    easing = LinearOutSlowInEasing
                ),
                initialOffsetY = { (it * 2) }
            ),
            exit = slideOutVertically(
                animationSpec = tween(
                    durationMillis = animationDuration + 300,
                    easing = LinearOutSlowInEasing
                ),
                targetOffsetY = { (it * 2) }
            )
        ) {
            Image(
                alignment = Alignment.BottomEnd,
                contentScale = ContentScale.FillHeight,
                painter = painterResource(id = R.drawable.mb_300_top_half),
                contentDescription = "Mercedes Benz C 300 Coupe top view photo"
            )
        }
    }
}

@Composable
private fun EngineCard(engine: Int) {
    Card(
        shape = RoundedCornerShape(32.dp),
        colors = CardDefaults.cardColors(containerColor = LightBlue, contentColor = Black)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(id = R.drawable.engine_icon),
                contentDescription = "Engine icon",
                tint = Blue
            )

            Text(
                text = engine.toString(),
                fontSize = 48.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "engine"
            )
        }
    }
}

@Composable
private fun TemperatureCard(temperature: Int) {
    Card(
        shape = RoundedCornerShape(32.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(id = R.drawable.snow_icon),
                contentDescription = "Snow icon"
            )

            Text(
                text = "${temperature}°",
                fontSize = 48.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "temperature"
            )
        }
    }
}

@Composable
private fun GasCard(gas: Int) {
    Card(
        shape = RoundedCornerShape(32.dp),
        colors = CardDefaults.cardColors(containerColor = Mint, contentColor = Black)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .drawWithCache {
                    onDrawBehind {
                        val path = Path().apply {
                            moveTo(x = 0f, y = size.height * 0.2f)
                            quadraticBezierTo(
                                x1 = size.width * 0.7f,
                                y1 = size.height * 0.9f,
                                x2 = size.width,
                                y2 = size.height * 0.2f
                            )
                        }

                        drawRect(
                            color = LightMint,
                            size = Size(width = size.width, height = size.height * 0.2f)
                        )

                        drawPath(
                            path = path,
                            color = LightMint,
                            style = Fill
                        )
                    }
                },
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(id = R.drawable.gas_icon),
                contentDescription = "Gas icon",
                tint = Mint
            )

            Text(
                text = gas.toString(),
                fontSize = 48.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "MPG"
            )
        }
    }
}