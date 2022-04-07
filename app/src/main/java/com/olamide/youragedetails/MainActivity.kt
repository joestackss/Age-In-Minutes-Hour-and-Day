package com.olamide.youragedetails

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    var selectedBirthday : TextView? = null
    var datesInMinutes : TextView? = null
    var datesInHour : TextView? = null
    var daysInDays : TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        selectedBirthday = findViewById(R.id.selectedBirthday)
        datesInMinutes = findViewById(R.id.datesInMinutes)
        datesInHour = findViewById(R.id.datesInHour)
        daysInDays = findViewById(R.id.daysInDays)
        val btnDatesPicker : Button = findViewById(R.id.btnDatesPicker)

        btnDatesPicker.setOnClickListener {
            //Toast.makeText(this, "The Btn Date is selected", Toast.LENGTH_LONG).show()
            myDatePicker()
        }
    }

    fun myDatePicker () {
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

        DatePickerDialog (this,
        DatePickerDialog.OnDateSetListener { view, selectedYear, selectedMonth, selectedDayOfMonth ->

            val selectedDate = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"
            selectedBirthday?.text = selectedDate

            val sdf = SimpleDateFormat ( "dd/MM/yyyy", Locale.ENGLISH)
            val theDate = sdf.parse(selectedDate)

            val selectedDatesInMinutes = theDate.time/60000
            val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
            val currentDatessInMinutes = currentDate.time/60000
            val differenceInMinutes = currentDatessInMinutes - selectedDatesInMinutes

            datesInMinutes?.text = differenceInMinutes.toString() + " Minutes"

            val selectedDatesInHour = theDate.time/3600000
            val currentDatessInHour = currentDate.time/3600000
            val differenceInHours = currentDatessInHour - selectedDatesInHour

            datesInHour?.text = differenceInHours.toString() + " Hours"


            val selectedDatesInDays = theDate.time/86400000
            val currentDatessInDays = currentDate.time/86400000
            val differenceInDays = currentDatessInDays - selectedDatesInDays

            daysInDays?.text = differenceInDays.toString() + " Days"
            if (differenceInDays?.toString() == "1") {
                daysInDays?.text = "1 Day"

            }


        },
        year,
        month,
        day).show()

    }

}