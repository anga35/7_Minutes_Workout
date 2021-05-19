package com.example.a7minutesworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_finishing.*
import java.text.SimpleDateFormat
import java.util.*

class FinishingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finishing)

        setSupportActionBar(toolbar_finish)

        var actionBar=supportActionBar

        if(actionBar!=null){

            actionBar.setDisplayHomeAsUpEnabled(true)

toolbar_finish.setNavigationOnClickListener {
    onBackPressed()
}
        }
var calendar=Calendar.getInstance()
        var currentTime=calendar.time
var sdf=SimpleDateFormat("dd MMM yyyy HH:mm:ss")
var date=sdf.format(currentTime)
        
addDateToDatabase(date)

    }

    fun addDateToDatabase(date:String){
        var databaseHandler=DatabaseHandler(this,null)
        databaseHandler.addDate(date)

        databaseHandler.close()
        Log.e("Date",date+" Added")
    }

}