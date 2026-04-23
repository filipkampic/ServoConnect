package com.filipkampic.servoconnect

import ConnectionState
import com.filipkampic.servoconnect.bluetooth.BluetoothManager
import com.filipkampic.servoconnect.ui.ServoScreen
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class MainActivity : ComponentActivity() {
    private val bluetoothManager = BluetoothManager()

    private var connectionState by mutableStateOf<ConnectionState>(
        ConnectionState.Disconnected
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ServoScreen(
                connectionState = connectionState,
                onConnectClick = { connectToBluetooth() },
                onAngleChange = { angle -> sendAngle(angle) }
            )
        }
    }

    private fun connectToBluetooth() {
        // Simulation
        connectionState = ConnectionState.Connecting

        Handler(Looper.getMainLooper()).postDelayed({
            connectionState = ConnectionState.Connected
            Log.d("BT", "Simulated connection success")
        }, 2000)

        // Real code for HC-06
        /*
        val deviceAddress = "00:00:00:00:00:00" // Dummy address

        connectionState = ConnectionState.Connecting

        bluetoothManager.connect(deviceAddress) { success ->
            runOnUiThread {
                if (success) {
                    connectionState = ConnectionState.Connected
                    Log.d("BT", "Connected successfully")
                } else {
                    connectionState = ConnectionState.Error("Connection failed")
                    Log.e("BT", "Connection failed")
                }
            }
        }*/
    }

    private fun sendAngle(angle: Int) {
        bluetoothManager.send("$angle\n")
    }
}
