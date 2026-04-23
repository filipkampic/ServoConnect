package com.filipkampic.servoconnect.ui

import ConnectionState
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ServoScreen(
    connectionState: ConnectionState,
    onConnectClick: () -> Unit,
    onAngleChange: (Int) -> Unit
) {
    var angle by remember { mutableStateOf(0f) }
    val statusText = when (connectionState) {
        is ConnectionState.Connected -> "🟢 Connected"
        is ConnectionState.Connecting -> "🟡 Connecting..."
        is ConnectionState.Disconnected -> "🔴 Disconnected"
        is ConnectionState.Error -> "❌ Error"
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Text(
            text = "Servo Control",
            fontSize = 24.sp
        )

        Text(
            text = statusText,
            fontSize = 18.sp
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Angle: ${angle.toInt()}",
            fontSize = 20.sp
        )

        Button(onClick = {
            onConnectClick()
        }) {
            Text("Connect")
        }

        Slider(
            value = angle,
            onValueChange = {
                angle = it
                val intAngle = it.toInt()

                Log.d("UI", "Angle: $intAngle")
                onAngleChange(intAngle)
            },
            valueRange = 0f..180f
        )
    }
}