package com.filipkampic.servoconnect.ui

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
    onConnectClick: () -> Unit,
    onAngleChange: (Int) -> Unit
) {
    var angle by remember { mutableStateOf(0f) }

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