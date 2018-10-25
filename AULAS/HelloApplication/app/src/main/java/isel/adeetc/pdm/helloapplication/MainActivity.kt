/*package isel.adeetc.pdm.helloapplication

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    var counter_key = null
    var counterViewModel = MainActivity.CounterViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState != null)
            counterViewModel = savedInstanceState?.getParcelable(counter_key)

        counter.text = counterViewModel.counter.toString()

        increment.setOnClickListener{counter.text = counterViewModel.incrementCount().toString()}
        decrement.setOnClickListener{counter.text = counterViewModel.decrementCount().toString()}
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putParcelable(counter_key, counterViewModel)
    }

    class CounterViewModel(var counter :Int = 0,val max :Int = 5) : Parcelable {

        constructor(parcel: Parcel) : this(
                parcel.readInt(),
                parcel.readInt()) {
        }

        fun incrementCount() :Int = ++counter
        fun decrementCount() :Int = --counter

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeInt(counter)
            parcel.writeInt(max)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<CounterViewModel> {
            override fun createFromParcel(parcel: Parcel): CounterViewModel {
                return CounterViewModel(parcel)
            }

            override fun newArray(size: Int): Array<CounterViewModel?> {
                return arrayOfNulls(size)
            }
        }
    }

}*/
