package com.example.android_dev_1_2024

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.android_dev_1_2024.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var bindingClass : ActivityMainBinding
    private var counter = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingClass = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)
        // name="txt_remained">Осталось мест:
        val textPassed = bindingClass.status.context.getText(R.string.txt_remained)
        // name="txt_free">Все места свободны
        val textFree = bindingClass.status.context.getText(R.string.txt_free)
        //name="txt_error">Пассажиров слишком много
        val textFull = bindingClass.status.context.getText(R.string.txt_error)
        //Кнопка добавление пассажиров в автобус
        bindingClass.buttonPlus.setOnClickListener {
            counter++
            bindingClass.textCounter.text = counter.toString()
            bindingClass.status.setTextColor(getColor(R.color.blue))
            bindingClass.status.text = "$textPassed ${50 - counter}"
            if(counter >= 50){
                bindingClass.buttonReset.visibility = View.VISIBLE
                bindingClass.status.setTextColor(getColorStateList(R.color.red))
                bindingClass.status.text = "$textFull"
                bindingClass.buttonPlus.isEnabled = false
            }
            else{
                bindingClass.buttonMinus.isEnabled = true
            }
        }
        //Кнопка высаживаемых пассажиров из автобус
        bindingClass.buttonMinus.setOnClickListener {
            counter--
            bindingClass.textCounter.text = counter.toString()
            bindingClass.status.text = "$textPassed ${50 - counter}"
            bindingClass.buttonPlus.isEnabled = true
            if(counter == 0){
                bindingClass.buttonMinus.isEnabled = false
                bindingClass.status.setTextColor(getColorStateList(R.color.green))
            }
        }
        // Сброс кол-ва людей в автобусе
        bindingClass.buttonReset.setOnClickListener {
            counter = 0
            bindingClass.textCounter.text = counter.toString()
            bindingClass.buttonReset.visibility = View.INVISIBLE
            bindingClass.status.setTextColor(getColorStateList(R.color.green))
            bindingClass.buttonMinus.isEnabled = false
            bindingClass.status.text = "$textFree"
            bindingClass.buttonPlus.isEnabled = true


        }
    }
}