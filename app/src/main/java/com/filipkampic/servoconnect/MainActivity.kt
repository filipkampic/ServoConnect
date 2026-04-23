package com.filipkampic.servoconnect

import com.filipkampic.servoconnect.bluetooth.BluetoothManager
import com.filipkampic.servoconnect.ui.ServoScreen
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

class MainActivity : ComponentActivity() {
    private val bluetoothManager = BluetoothManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ServoScreen(
                onConnectClick = {
                    connectToBluetooth()
                }
            )
        }
    }

    private fun connectToBluetooth() {
        val deviceAddress = "00:00:00:00:00:00" // Dummy address

        bluetoothManager.connect(deviceAddress) { success ->
            runOnUiThread {
                if (success) {
                    Log.d("BT", "Connected successfully")
                } else {
                    Log.e("BT", "Connection failed")
                }
            }
        }
    }
}
