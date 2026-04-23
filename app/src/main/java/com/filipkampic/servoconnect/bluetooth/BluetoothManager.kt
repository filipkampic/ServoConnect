import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothSocket
import java.util.UUID

class BluetoothManager {

    private var socket: BluetoothSocket? = null

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

                onResult(true)
            } catch (e: Exception) {
                e.printStackTrace()
                onResult(false)
            }
        }.start()
    }
}