package com.example.a7minutesworkout

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.activity_bmi_activity.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //If start Button is clicked
btn_start.setOnClickListener {

    var intent= Intent(this,exercise_activity::class.java)
    startActivity(intent)


}

        //If BMI Button is clicked
        llBMI.setOnClickListener {
var intent=Intent(this,bmi_activity::class.java)
            startActivity(intent)

        }

        //If History Button is clicked
llHistory.setOnClickListener {

    var intent:Intent=Intent(this,History::class.java)
    startActivity(intent)


}

        //If Create  Session Button is clicked
        btn_createSession.setOnClickListener {
var intent=Intent(this,CreateExerciseSession::class.java)
startActivity(intent)
finish()
        }

        //If Manage Session Button is clicked
        btn_manageSession.setOnClickListener {

var intent=Intent(this,ManageSession::class.java)
startActivity(intent)
finish()

        }

        // ArrayList of Sessions to be read by the ViewPager Adapter
var sessionData=ArrayList<Session>()

        sessionData.add(Session(1,"Default Session",Constants.defaultExerciseList()))//Initialize The Array List with Default Sessions

        //Fetch List of Sessions existing on the Database
        sessionData.addAll(fetchdata())
        var itemAdapter=SelectSessionAdapter(this,sessionData) //Generate PagerAdapter Subclass to attach to View Pager
        current_session.adapter=itemAdapter//Attach Defined View Pager Adapter to the ViewPager View



        //Listener that will be invoked whenever the page changes or is incrementally
        current_session.setOnPageChangeListener(object :ViewPager.OnPageChangeListener{
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {

            Constants.currentSession=sessionData[position]
            }

            override fun onPageScrollStateChanged(state: Int) {

            }


        })


    }


    //Fetch Session Data saved on the Database
    fun fetchdata():ArrayList<Session>{

        var sessionDatabase=SessionDatabase(this)
        return sessionDatabase.fetchData()

    }


}