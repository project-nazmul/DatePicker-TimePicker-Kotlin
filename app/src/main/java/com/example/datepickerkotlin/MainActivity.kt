package com.example.datepickerkotlin

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private val calender = Calendar.getInstance()
    @SuppressLint("MissingInflatedId", "SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var selectDate = findViewById<Button>(R.id.button)
        var selectTime = findViewById<Button>(R.id.button2)
        var textView = findViewById<TextView>(R.id.textView)
        var textView2 = findViewById<TextView>(R.id.textView2)

        selectDate.setOnClickListener {
            val datePickerDialog = DatePickerDialog(this,{_, year:Int, monthOfYear:Int, dayOfMonth:Int ->
                val selectedDate = Calendar.getInstance()
                selectedDate.set(year,monthOfYear,dayOfMonth)
                val dateFormat = SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault())
                val formattedDate:String = dateFormat.format(selectedDate.time)
                textView.text = formattedDate
            },
                calender.get(Calendar.YEAR),
                calender.get(Calendar.MONTH),
                calender.get(Calendar.DAY_OF_MONTH),
            ).apply {
                datePicker.maxDate = Date().time+(1000*60*60*24*9)
                datePicker.minDate = Date().time-(1000*60*60*24*9)
            }
            datePickerDialog.show()
        }

        selectTime.setOnClickListener {
            val cal = Calendar.getInstance()
            val timeSetListener = TimePickerDialog.OnTimeSetListener { _, hour, minute ->
                cal.set(Calendar.HOUR_OF_DAY, hour)
                cal.set(Calendar.MINUTE, minute)
                var formattedTime: String = SimpleDateFormat("hh:mm a").format(cal.time)
                textView2.text = formattedTime
            }
            TimePickerDialog(this, timeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), false).show()
        }


    }
}