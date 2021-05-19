package com.example.a7minutesworkout

import android.app.Dialog
import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.widget.Toast
import android.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_exercise_activity.*
import kotlinx.android.synthetic.main.finish_dialog.*
import java.util.*
import kotlin.collections.ArrayList

class exercise_activity : AppCompatActivity(),TextToSpeech.OnInitListener {

    var restTimer: CountDownTimer?=null
    var exerciseTimer:CountDownTimer?=null
    var restProgress=0
    var currentPos=0
    var frProgress=30

    var tts:TextToSpeech?=null
    var exerciseList=ArrayList<ExerciseModel>()
var exerciseAdapter:ExerciseAdapter?=null
var media:MediaPlayer?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise_activity)
   setSupportActionBar(tool_bar_exercise)// set defined ToolBar as ActionBar
        var actionBar=supportActionBar


      //Enable Back Button on ToolBar
        if(actionBar!=null){


actionBar.setDisplayHomeAsUpEnabled(true)
        }

        //If ToolBar Back Button is Pressed
tool_bar_exercise.setNavigationOnClickListener {

    //Define Dialog with the specified attributes
    var dialog=Dialog(this)
    dialog.setContentView(R.layout.finish_dialog)

    (dialog.finish_yes).setOnClickListener {
       finish()
    dialog.dismiss()
    }
    (dialog.finish_no).setOnClickListener {
        dialog.dismiss()


    }
    dialog.show()

}


        current_sessionName.text=Constants.currentSession!!.sessionName//Current Session selected on the ViewPager from Main Activity
tts= TextToSpeech(this,this)//Initialize Text To Speech Object



        exerciseList=Constants.currentSession!!.exerciseList//Assign List of Exercises from selected Session




    }
    //Garbage Collector
    override fun onDestroy() {
        super.onDestroy()

        if(restTimer!=null){
            restTimer!!.cancel()
restTimer=null
        }

        if(exerciseTimer!=null){
            exerciseTimer!!.cancel()
            exerciseTimer=null
        }

if(media!=null){

    media!!.stop()

}
        if(tts!=null){
            tts!!.shutdown()
        }

    }


    fun startCountDown(){


        setupRest()





    }







/*Function to Initialize Exercise Countdown

  *Is called After SetupRest()
  */
fun setupExercise(){
    img_exercise.setImageResource(exerciseList[currentPos].getImage())
    txt_exercise_name.text=exerciseList[currentPos].getName()
frProgress=30
media=MediaPlayer.create(this,R.raw.press_start)

    media!!.start()



    exerciseTimer=object : CountDownTimer(30000,1000){



        override fun onFinish() {
            Toast.makeText(this@exercise_activity,"DONE",Toast.LENGTH_SHORT).show()

            exerciseTimer!!.cancel()
           if(currentPos<exerciseList.size) {


               ll_exercise.visibility=View.GONE
               ll_rest.visibility=View.VISIBLE



               exerciseList[currentPos].setIsCompleted(true)

               exerciseList[currentPos].setIsSelected(false)


               currentPos++


               exerciseList[currentPos].setIsSelected(true)


               exerciseAdapter!!.notifyDataSetChanged()

               setupRest()
           }
            else{
           var intent= Intent(this@exercise_activity,FinishingActivity::class.java)
               startActivity(intent)

           }

        }

        override fun onTick(millisUntilFinished: Long) {


            if(frProgress<=5){
                speakUp(frProgress.toString())
            }
            progress_circular2.progress=frProgress
            txt_time2.text=frProgress.toString()



            frProgress--
        }


    }.start()


}

//Function to Initialize Rest Count Down
    fun setupRest(){


restProgress=0
        txt_current_activity.text=exerciseList[currentPos].getName()

            speakUp(exerciseList[currentPos].getName())


        restTimer=object: CountDownTimer(10000,1000){



            override fun onTick(millisUntilFinished: Long) {


                if((10-restProgress)<=5){
                    speakUp((10-restProgress).toString())
                }

                progress_circular.progress=10-restProgress

                txt_time.text=(10-restProgress).toString()

                restProgress++

            }


            override fun onFinish() {



                ll_rest.visibility= View.GONE
                ll_exercise.visibility=View.VISIBLE
                restTimer!!.cancel()


              setupExercise()
            }

        }.start()


    }


    //Initialize Text to Speech Object
    override fun onInit(status: Int){
if(status==TextToSpeech.SUCCESS){

    val result=tts!!.setLanguage(Locale.US)
    if(result== TextToSpeech.LANG_MISSING_DATA|| result== TextToSpeech.LANG_NOT_SUPPORTED){

        Log.e("TTS","Language Missing")

    }


}

        else{
    Log.e("TTS","Initialization Failed")
        }
        exerciseList[currentPos].setIsSelected(true)
        exerciseAdapter= ExerciseAdapter(this,exerciseList)
        rv_exercise_status.layoutManager=LinearLayoutManager(this,RecyclerView.HORIZONTAL,false)

        rv_exercise_status.adapter=exerciseAdapter



        startCountDown()
    }

    //Function to speak a text
fun speakUp(text:String){
    tts!!.speak(text,TextToSpeech.QUEUE_FLUSH,null,"")

}
}