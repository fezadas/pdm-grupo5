package pdm.isel.yama_pdm

import android.app.Application
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley

class MainApplication : Application() {
    lateinit var queue: RequestQueue

    override fun onCreate() {
        super.onCreate()
        queue = Volley.newRequestQueue(this)
    }
}