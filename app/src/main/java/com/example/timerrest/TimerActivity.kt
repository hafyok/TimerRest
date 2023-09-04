package com.example.timerrest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.NumberPicker
import android.widget.Toast
import androidx.core.view.GravityCompat
import com.example.timerrest.Model.Objects.DatabaseHelper
import com.example.timerrest.Model.Objects.NetworkUtils
import com.example.timerrest.ViewModel.TimerViewModel
import com.example.timerrest.databinding.ActivityMainBinding
import javax.inject.Inject


class TimerActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private var timer: CountDownTimer? = null
    lateinit var viewModel: TimerViewModel

    //Activity -> Component -> Module -> Presenter (Dagger)
    lateinit var databaseHelper: DatabaseHelper
    lateinit var networkUtils: NetworkUtils
    /*@Inject lateinit var databaseHelper: DatabaseHelper
    @Inject lateinit var networkUtils: NetworkUtils*/


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val appComponent = (application as App).appComponent

        databaseHelper = appComponent.getDatabaseHelper()
        networkUtils = appComponent.getNetworkUtils()

        //(application as App).appComponent.injectMainActivity(this)

        initTimer(binding)

        menuItem(binding)
    }

    private fun initTimer(binding: ActivityMainBinding){
        val numberPicker: NumberPicker  = binding.numberPicker
        numberPicker.minValue = 0
        numberPicker.maxValue = 33 // Количество возможных значений

        numberPicker.displayedValues = (15..180 step 5).map { it.toString() }.toTypedArray()
        numberPicker.wrapSelectorWheel = true

        numberPicker.setOnValueChangedListener { picker, oldVal, newVal ->
            //binding.textView.text = "Выбранное значение: ${numberPicker.displayedValues[newVal]}"
            binding.timerMinutes.text = numberPicker.displayedValues[newVal] + ":00"
        }

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

    private fun menuItem(binding: ActivityMainBinding){
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

}