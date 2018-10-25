package isel.adeetc.pdm.helloapplication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.secondary_activity.*

class SecondaryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.secondary_activity)

        var string = intent.getStringExtra("text")
        text.setText(string)

        button.setOnClickListener{
            val sendIntent = Intent(this,PrimaryActivity::class.java).apply {
                action = Intent.ACTION_SEND
                type = "text/plain"
                putExtra("returnMessage", textView.text.toString())
            }
            setResult(Activity.RESULT_OK,sendIntent)
            finish()
        }
    }

}