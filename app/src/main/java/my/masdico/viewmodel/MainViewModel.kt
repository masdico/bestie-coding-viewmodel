package my.masdico.viewmodel

import android.os.SystemClock
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

class MainViewModel : ViewModel() {
    companion object {
        private const val ONE_SECOND: Long = 1000
    }

    private var _elapsedTime = MutableLiveData<Long?>()
    val elapsedTime: LiveData<Long?> get() = _elapsedTime
    private var timer: Timer? = null
    private val delay = ONE_SECOND
    private val period = ONE_SECOND

    fun openTimer() {
        timer = Timer()
        val initialTime = SystemClock.elapsedRealtime()
        val task = object : TimerTask() {
            override fun run() {
                val newValue = (SystemClock.elapsedRealtime() - initialTime) / ONE_SECOND
                _elapsedTime.postValue(newValue)
            }
        }
        timer?.schedule(task, delay, period)
    }

    fun stopTimer() {
        if (timer != null) {
            timer?.cancel()
            timer = null
            _elapsedTime.postValue(0)
        }
    }
}