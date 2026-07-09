package com.example.swimpulse

import android.os.Bundle
import android.os.SystemClock
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SwimPulseApp()
        }
    }
}

@Composable
private fun SwimPulseApp() {
    MaterialTheme {
        var isSwimming by remember { mutableStateOf(false) }
        var startTimeMillis by remember { mutableLongStateOf(0L) }

        SwimPulseScreen(
            isSwimming = isSwimming,
            startTimeMillis = startTimeMillis,
            onStart = {
                startTimeMillis = SystemClock.elapsedRealtime()
                isSwimming = true
            },
            onStop = { isSwimming = false },
        )
    }
}

@Composable
private fun SwimPulseScreen(
    isSwimming: Boolean,
    startTimeMillis: Long,
    onStart: () -> Unit,
    onStop: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(horizontal = 18.dp, vertical = 14.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        if (isSwimming) {
            SwimMetrics(startTimeMillis = startTimeMillis)
            Spacer(modifier = Modifier.height(10.dp))
            SwimActionButton(text = "Stop", onClick = onStop)
        } else {
            SwimTitle()
            Spacer(modifier = Modifier.height(14.dp))
            SwimActionButton(text = "Start", onClick = onStart)
        }
    }
}

@Composable
private fun SwimTitle() {
    Text(
        text = "SwimPulse",
        color = Color.White,
        fontSize = 28.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
    )
}

@Composable
private fun SwimMetrics(startTimeMillis: Long) {
    var elapsedMillis by remember(startTimeMillis) { mutableLongStateOf(0L) }

    LaunchedEffect(startTimeMillis) {
        while (true) {
            elapsedMillis = SystemClock.elapsedRealtime() - startTimeMillis
            delay(1_000)
        }
    }

    Text(
        text = formatElapsedTime(elapsedMillis),
        color = Color.White,
        fontSize = 44.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
    )
    Text(
        text = "-- bpm",
        color = Color.White,
        fontSize = 30.sp,
        fontWeight = FontWeight.SemiBold,
        textAlign = TextAlign.Center,
    )
    Text(
        text = "תקין",
        color = Color(0xFF69F0AE),
        fontSize = 24.sp,
        fontWeight = FontWeight.Medium,
        textAlign = TextAlign.Center,
    )
}

@Composable
private fun SwimActionButton(
    text: String,
    onClick: () -> Unit,
) {
    Button(onClick = onClick) {
        Text(
            text = text,
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
        )
    }
}

private fun formatElapsedTime(elapsedMillis: Long): String {
    val totalSeconds = (elapsedMillis / 1_000).coerceAtLeast(0)
    val minutes = totalSeconds / 60
    val seconds = totalSeconds % 60
    return "%02d:%02d".format(minutes, seconds)
}
