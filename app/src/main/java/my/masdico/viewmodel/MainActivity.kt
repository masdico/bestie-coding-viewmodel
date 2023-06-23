package my.masdico.viewmodel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import my.masdico.viewmodel.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        subscribe()

        mainBinding.btnStart.setOnClickListener {
            mainViewModel.openTimer()
        }

        mainBinding.btnStop.setOnClickListener {
            mainViewModel.stopTimer()
        }
    }

    private fun subscribe() {
        val elapsedTimeObserver = Observer<Long?>{
            val newText = this.resources.getString(R.string.text_timer2, it)
            mainBinding.tvTimer.text = newText
        }
        mainViewModel.elapsedTime.observe(this, elapsedTimeObserver)
    }
}