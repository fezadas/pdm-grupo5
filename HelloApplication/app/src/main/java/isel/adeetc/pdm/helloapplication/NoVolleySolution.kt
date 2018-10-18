package isel.adeetc.pdm.hellothreadingmodel

import android.os.AsyncTask
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var text_key = null
    var textViewModel = MainActivity.TextViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.v("HelloThreadingModel", "onCreate for ${hashCode()} on Thread ${Thread.currentThread().id} is working hard")

        MyClass.activity = this;

        if(savedInstanceState!=null){
            textViewModel = savedInstanceState?.getParcelable(text_key)
        }

        MyClass.activity.msgTextView.text = textViewModel.text

        hitMeButton.setOnClickListener {
            val work = object: AsyncTask<Unit, Unit, String>() {
                override fun doInBackground(vararg params: Unit?): String {
                    Log.v("HelloThreadingModel", "Thread ${Thread.currentThread().id} is working hard")
                    Thread.sleep(10000)
                    Log.v("HelloThreadingModel", "Thread ${Thread.currentThread().id} completed hard work")
                    return "Work Completed"
                }

                override fun onPostExecute(result: String?) {
                    Log.v("HelloThreadingModel", "Thread ${Thread.currentThread().id} is displaying result")
                    Log.v("HelloThreadingModel", "Activity is ${this@MainActivity.hashCode()}")
                    textViewModel.text = result;
                    MyClass.activity.msgTextView.text = result

                }
            }

            Log.v("HelloThreadingModel", "Thread ${Thread.currentThread().id} dispatching work")
            work.execute()
            Log.v("HelloThreadingModel", "Thread ${Thread.currentThread().id} dispatched work")
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putParcelable(text_key, textViewModel)
    }


    class MyClass {
        companion object { lateinit var activity: MainActivity }
    }

    class TextViewModel(var text:String? = "") : Parcelable {
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