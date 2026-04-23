package com.filipkampic.servoconnect.bluetooth

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothSocket
import android.util.Log
import java.io.OutputStream
import java.util.UUID

@Suppress("DEPRECATION")
class BluetoothManager {

    private var socket: BluetoothSocket? = null
    private var outputStream: OutputStream? = null

    fun connect(address: String, onResult: (Boolean) -> Unit) {
        Thread {
            try {
                val adapter = BluetoothAdapter.getDefaultAdapter()
                val device = adapter.getRemoteDevice(address)

                val uuid = UUID.fromString(
                    "00001101-0000-1000-8000-00805F9B34FB"
                )

                socket = device.createRfcommSocketToServiceRecord(uuid)
                socket?.connect()
                outputStream = socket?.outputStream

                onResult(true)
            } catch (e: Exception) {
                e.printStackTrace()
                onResult(false)
            }
        }.start()
    }

    fun send(data: String) {
        try {
            if (outputStream != null) {
                Log.e("BT", "Not connected")
                return
            }

            outputStream?.write(data.toByteArray())
            Log.d("BT", "Sent: $data")
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("BT", "Send failed")
        }
    }
}