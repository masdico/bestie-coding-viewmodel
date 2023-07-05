package my.masdico.viewmodel

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    companion object {
        private const val ONE_SECOND: Long = 1000
    }

    private var timer: CountDownTimer? = null
    private var _elapsedTime = MutableLiveData<Int>()
    val elapsedTime: LiveData<Int> get() = _elapsedTime

    fun startCountDown(time: Int){
        val countDownInterval: Long = ONE_SECOND
        val countDownTime: Long = (time+1) * ONE_SECOND
        timer = object : CountDownTimer(countDownTime, countDownInterval) {
            override fun onTick(remainingTime: Long) {
                val timeLeft = remainingTime/ ONE_SECOND
                _elapsedTime.value = timeLeft.toInt()
            }

            override fun onFinish() {
                timer = null
            }
        }.start()
    }

    fun stopCountDown(){
        timer?.cancel()
        _elapsedTime.value = 0
    }
}