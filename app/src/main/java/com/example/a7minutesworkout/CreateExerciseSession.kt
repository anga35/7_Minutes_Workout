package com.example.a7minutesworkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.activity_create_exercise_session.*
import kotlinx.android.synthetic.main.activity_main.*


class CreateExerciseSession : AppCompatActivity() {
var rvItemAdapter:ExerciseTextListAdapter?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_exercise_session)

        var itemAdapter = CreateCardSessionAdapter(this, Constants.defaultExerciseList())

        vp_create_exerciseList.adapter=itemAdapter


         rvItemAdapter=ExerciseTextListAdapter(this,Constants.tempExerciseList)
        rv_addSessionList.layoutManager=GridLayoutManager(this,3,GridLayoutManager.VERTICAL,false)
        rv_addSessionList.adapter=rvItemAdapter

btn_doneCreate.setOnClickListener {


if(editText_sessionName.text!!.isNotEmpty()){

    var session=Session(1,editText_sessionName.text.toString(),Constants.tempExerciseList)
    addSessionToDatabase(session)
    onBackPressed()

}



}




    vp_create_exerciseList.setOnPageChangeListener( object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {

            }

        })


    }

//Clear tempExerciseList when onBackPressed is called
    override fun onBackPressed() {
        Constants.tempExerciseList.clear()

        var intent=Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    //Refresh the RecyclerView
fun refreshSelectedList(exerciseModelList:ArrayList<ExerciseModel>){

    rvItemAdapter=ExerciseTextListAdapter(this,exerciseModelList)
    rv_addSessionList.adapter=rvItemAdapter
}
//Add session to DataBase
    fun addSessionToDatabase(session: Session){

        var databaseHandler=SessionDatabase(this)
        databaseHandler.addSession(session)


    }


}