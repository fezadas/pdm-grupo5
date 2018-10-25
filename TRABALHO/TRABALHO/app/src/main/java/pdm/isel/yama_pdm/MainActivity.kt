package pdm.isel.yama_pdm

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*
import pdm.isel.yama_pdm.network.GetRequest

class MainActivity : AppCompatActivity() {
    lateinit var queue: RequestQueue
    val PREFS_FILENAME = "pdm.isel.yama.YAMA.prefs"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        queue = Volley.newRequestQueue(this)

        identifier_view.setOnClickListener{ identifier_view.text.clear() }
        token_view.setOnClickListener{ token_view.text.clear() }
        org_view.setOnClickListener{ org_view.text.clear() }
        verify_button.setOnClickListener{
            val sharedPref = this.getSharedPreferences(PREFS_FILENAME,0)
            val editor = sharedPref.edit();
            editor.putString("id", identifier_view.text.toString())
            editor.putString("token", token_view.text.toString())
            editor.putString("org", org_view.text.toString())
            editor.apply();

            val request = GetRequest("https://api.github.com/user?access_token=edfdd0928b9af3374cef4ae2b8a69fbd529fe291"/*+sharedPref.getString("token","")*/,
                    Response.Listener { Log.v("teste",it.email) },
                    Response.ErrorListener {
                        //Toast.makeText(getApplication(), R.string.error_network, Toast.LENGTH_LONG).show()
                    })
            queue.add(request)
        }



    }
}

