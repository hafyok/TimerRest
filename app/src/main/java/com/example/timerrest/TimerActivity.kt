package com.example.timerrest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.widget.NumberPicker
import android.widget.Toast
import com.example.timerrest.databinding.ActivityMainBinding
import java.lang.String.format

class TimerActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private var timer: CountDownTimer? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        val numberPicker: NumberPicker  = binding.numberPicker
        numberPicker.minValue = 0
        numberPicker.maxValue = (180 - 15) / 5 // Количество возможных значений

        numberPicker.displayedValues = (15..180 step 5).map { it.toString() }.toTypedArray()
        numberPicker.wrapSelectorWheel = true

        numberPicker.setOnValueChangedListener { picker, oldVal, newVal ->
            binding.textView.text = "Выбранное значение: ${numberPicker.displayedValues[newVal]}"
            binding.timerMinutes.text = numberPicker.displayedValues[newVal] + ":00"
        }

        setContentView(binding.root)
        binding.bStart.setOnClickListener {
            val selectedMinutes = numberPicker.displayedValues[numberPicker.value].toLong() // Выбранное количество минут
            startCountDownTimer(selectedMinutes * 60 * 1000)
        }

    }

    private fun startCountDownTimer(timerMillis: Long){
        timer?.cancel()
        timer = object : CountDownTimer(timerMillis, 1){
            override fun onTick(timeM: Long) {
                binding.timerMinutes.text = (timeM / 1000 / 60).toString() + ":" + (timeM / 1000 % 60).toString()
            }

            override fun onFinish() {
                binding.timerMinutes.text = "Finish"
            }

        }.start()
    }
}