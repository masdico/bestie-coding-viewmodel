package my.masdico.viewmodel

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Spinner.MODE_DIALOG
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import my.masdico.viewmodel.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel
    private lateinit var optionTime: Spinner
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        supportActionBar?.title = "Count Down"
        displayCountDown()

        val dropDownData = TimerData.dropDownData
        val counter = TimerData.counter
        val dropDownAdapter = ArrayAdapter(this, android.R.layout.select_dialog_item, dropDownData)
        optionTime = Spinner(this,MODE_DIALOG)
        optionTime.apply {
            adapter = dropDownAdapter
            gravity = Gravity.CENTER
            prompt = "Select count down time:"
            setSelection(15,false)
            onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    mainBinding.tvTimeCountdown.text = counter[position].toString()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    Toast.makeText(this@MainActivity,"no selection",Toast.LENGTH_SHORT).show()
                }
            }
        }

        mainBinding.dropdownChoice.addView(optionTime)

        mainBinding.btnStart.setOnClickListener {
            mainViewModel.startCountDown(Integer.parseInt(mainBinding.tvTimeCountdown.text.toString()))
        }

        mainBinding.btnStop.setOnClickListener {
            mainViewModel.stopCountDown()
            mainBinding.tvTimeCountdown.text = mainViewModel.elapsedTime.value.toString()
        }
    }

    private fun displayCountDown() {
        mainViewModel.elapsedTime.observe(this) { remainingTime ->
            mainBinding.tvTimeCountdown.text = remainingTime.toString()
            if (remainingTime == 0) {
                Toast.makeText(this, "Time Out!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}