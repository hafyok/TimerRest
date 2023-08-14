package com.example.timerrest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.widget.NumberPicker
import com.example.timerrest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private var timer: CountDownTimer? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val numberPicker: NumberPicker  = binding.numberPicker
        numberPicker.minValue = 0
        numberPicker.maxValue = 9
        numberPicker.wrapSelectorWheel = false

        numberPicker.setOnValueChangedListener { picker, oldVal, newVal ->
            binding.textView.text = "Выбранное значение: $newVal"
        }

        setContentView(binding.root)
        binding.apply {
            bStart.setOnClickListener {
                startCountDownTimer(10000)
            }
        }
    }

    private fun startCountDownTimer(timerMillis: Long){
        timer?.cancel()
        timer = object : CountDownTimer(timerMillis, 1000){
            override fun onTick(timeM: Long) {
                binding.timerMinutes.text = (timeM / 1000 / 60).toString() + ":"
                binding.timerSeconds.text = (timeM / 1000 % 60).toString()
            }

            override fun onFinish() {
                binding.timerSeconds.visibility = View.GONE
                binding.timerMinutes.text = "Finish"
            }

        }.start()
    }
}