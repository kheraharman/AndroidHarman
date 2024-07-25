package com.android.core_ui

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import kotlinx.coroutines.delay

/**
 * Displays a loading animation with three dots pulsing in opacity.
 *
 * This composable function creates a dialog window that shows a loading animation, indicating that an operation is in progress.
 * The animation consists of three dots that pulse in opacity to give a visual cue of ongoing activity. It's designed to keep the user informed
 * about the process that is currently being executed in the background.
 */
@Composable
fun LoadingScreen() {
    Dialog(onDismissRequest = {}) {
        val circleColor: Color = Color.Black
        val circleSize: Dp = 12.dp
        val animationDelay = ANIMATION_DELAY
        val initialAlpha = INITIAL_ALPHA

        val circles = listOf(remember {
            Animatable(initialValue = initialAlpha)
        }, remember {
            Animatable(initialValue = initialAlpha)
        }, remember {
            Animatable(initialValue = initialAlpha)
        })

        circles.forEachIndexed { index, animatable ->
            LaunchedEffect(Unit) {
                delay(timeMillis = (animationDelay / circles.size).toLong() * index)
                animatable.animateTo(
                    targetValue = 1f, animationSpec = infiniteRepeatable(
                        animation = tween(
                            durationMillis = animationDelay
                        ), repeatMode = RepeatMode.Reverse
                    )
                )
            }
        }


        Row(
            modifier = Modifier
        ) {
            circles.forEachIndexed { index, animatable ->
                if (index != 0) {
                    Spacer(modifier = Modifier.width(width = 6.dp))
                }
                Box(
                    modifier = Modifier
                        .size(size = circleSize)
                        .clip(shape = CircleShape)
                        .background(
                            color = circleColor.copy(alpha = animatable.value)
                        )
                ) {}
            }
        }
    }
}

const val ANIMATION_DELAY = 200
const val INITIAL_ALPHA = 0.3f