package isel.adeetc.pdm.helloapplication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.primary_activity.*

class PrimaryActivity : AppCompatActivity() {

    var text_key = null
    var textViewModel = PrimaryActivity.TextViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.primary_activity)

        if(savedInstanceState != null)
            textViewModel = savedInstanceState?.getParcelable(text_key)

        text.text = textViewModel.text

        button.setOnClickListener{
            val sendIntent = Intent(this,SecondaryActivity::class.java).apply {
                action = Intent.ACTION_SEND
                type = "text/plain"
                putExtra("text", textView.text.toString())
            }
            startActivityForResult(sendIntent,2)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(Activity.RESULT_CANCELED==resultCode) text.text = textViewModel.text
        else
        {
            var returnText =data?.getStringExtra("returnMessage");
            text.text = returnText
            textViewModel.text = returnText
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putParcelable(text_key, textViewModel)
    }

    class TextViewModel(var text:String? = "texto"):Parcelable {
        constructor(parcel: Parcel) : this(parcel.readString()) {
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeString(text)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<TextViewModel> {
            override fun createFromParcel(parcel: Parcel): TextViewModel {
                return TextViewModel(parcel)
            }

            override fun newArray(size: Int): Array<TextViewModel?> {
                return arrayOfNulls(size)
            }
        }
    }
}




