package com.example.dobcalculator

import android.app.DatePickerDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnDatePicker : Button = findViewById<Button>(R.id.btnDatePicker)

        btnDatePicker.setOnClickListener{
            clickDate()
        }
    }

    private fun clickDate() {
        val myCalander = Calendar.getInstance()
        var year = myCalander.get(Calendar.YEAR)
        var month = myCalander.get(Calendar.MONTH)
        var day = myCalander.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog( this,
                DatePickerDialog.OnDateSetListener { view, selectedYear, selectedMonth, selectedDay ->
                    month = selectedMonth+1
                    year=selectedYear
                    day=selectedDay
                    Toast.makeText(this,
                            "DatePickerWorks Pressed and slected the date is $day - $month - $year", Toast.LENGTH_LONG).show()

                    val inspection : TextView = findViewById<TextView>(R.id.dateEntered);
                    val selectedString = day.toString() + "/" + month.toString() + "/" + year.toString();
                    inspection.text = selectedString
                    val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                    val date = sdf.parse(selectedString)
                    val selectedDateInMinutes = date.time / 60000
                    val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                    val currentDateInMinutes = currentDate.time / 60000
                    val diff = currentDateInMinutes - selectedDateInMinutes

                    val minutesR = findViewById<TextView>(R.id.inMinutes)
                    minutesR.text = diff.toString()

                },
                year,
                month,
                day
        )

        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000

        dpd.show()
    }

}