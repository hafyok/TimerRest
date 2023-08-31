package com.example.timerrest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.NumberPicker
import android.widget.Toast
import androidx.core.view.GravityCompat
import com.example.timerrest.databinding.ActivityMainBinding


class TimerActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private var timer: CountDownTimer? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)



        val numberPicker: NumberPicker  = binding.numberPicker
        numberPicker.minValue = 0
        numberPicker.maxValue = 33 // Количество возможных значений

        numberPicker.displayedValues = (15..180 step 5).map { it.toString() }.toTypedArray()
        numberPicker.wrapSelectorWheel = true

        numberPicker.setOnValueChangedListener { picker, oldVal, newVal ->
            //binding.textView.text = "Выбранное значение: ${numberPicker.displayedValues[newVal]}"
            binding.timerMinutes.text = numberPicker.displayedValues[newVal] + ":00"
        }

        setContentView(binding.root)
        binding.bStart.setOnClickListener {
            val selectedMinutes = numberPicker.displayedValues[numberPicker.value].toLong() // Выбранное количество минут
            startCountDownTimer(selectedMinutes * 60 * 1000)
        }

        binding.apply {
            mainNavigationMenu.setNavigationItemSelectedListener {
                when(it.itemId){
                    R.id.statistics -> Toast.makeText(this@TimerActivity, "Статистика", Toast.LENGTH_SHORT).show()
                    R.id.shop -> Toast.makeText(this@TimerActivity, "Магазин", Toast.LENGTH_SHORT).show()
                    R.id.achieve -> Toast.makeText(this@TimerActivity, "Достижения", Toast.LENGTH_SHORT).show()
                    R.id.settings -> Toast.makeText(this@TimerActivity, "Настройки", Toast.LENGTH_SHORT).show()
                }
                mainDrawer.closeDrawer(GravityCompat.START)
                true
            }
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