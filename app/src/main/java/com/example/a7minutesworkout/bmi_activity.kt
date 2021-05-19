package com.example.a7minutesworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_bmi_activity.*
import java.lang.Exception
import java.math.BigDecimal
import java.math.RoundingMode

class bmi_activity : AppCompatActivity() {

    val BMI_METRIC="BMI METRIC"
    val BMI_US="BMI US"

var currentBmiView:String=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bmi_activity)

        //Setup Action Bar
        setSupportActionBar(tool_bar_bmi)
        var actionBar=supportActionBar

        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true)
actionBar.title="Calculate BMI"
        }
        tool_bar_bmi.setNavigationOnClickListener {
           finish()

        }
        displayMetricBmi()


        //Check Tapped Button on the Radio Group
        rg_unit.setOnCheckedChangeListener { group, checkedId ->

            if(checkedId==R.id.rb_metric){

                displayMetricBmi()
            }
            else{
                displayUSBmi()
            }


        }
//If Calculate butten is tapped
        btn_bmi_calculate.setOnClickListener {view->
try {
    var value:Float=0f

    if(currentBmiView==BMI_METRIC) {//If Current selected Screen is BMI Metric

        //Get User Input in Float
        var weight = tv_weight_input.text.toString().toFloat()
        var height = tv_height_input.text.toString().toFloat()


        var bmiVal: Float = (weight) / ((height / 100) *( height/100))//BMI Metric Formula
        var bmiRound = BigDecimal(bmiVal.toDouble()).setScale(2, RoundingMode.HALF_EVEN).toString()
        tv_bmi_value.text = bmiRound
         value = tv_bmi_value.text.toString().toFloat()

    }
else if(currentBmiView==BMI_US){ //If current selected screen US Metric

    //Get User Input in Float
        var weight = tv_USweight_input.text.toString().toFloat()
        var height = (tv_USfeet_input.text.toString().toFloat()+tv_USinch_input.text.toString().toFloat())*12


        var bmiVal: Float =703*((weight) / ((height) * (height)))//BMI US Metric Formula
        var bmiRound = BigDecimal(bmiVal.toDouble()).setScale(2, RoundingMode.HALF_EVEN).toString()
        tv_bmi_value.text = bmiRound
        value = tv_bmi_value.text.toString().toFloat()


    }
//Block of Code to Validate the BMI Value for commenting
    if (value < 18.5) {

        tv_bmi_comment.text = "You are underweight"
    } else if (value >= 25.0) {
        tv_bmi_comment.text = "You are overweight"
    } else if (value >= 18.5) {

        tv_bmi_comment.text = "You have a normal weight from the BMI metric"

    }


    ll_bmi_result.visibility = View.VISIBLE


}

catch(e:Exception){
Snackbar.make(view,"Wrong Input,Try again",Snackbar.LENGTH_SHORT).show()

}



        }

    }
//Display BMI Metric Layout and Hide BMI US Layout
    fun displayMetricBmi(){
        ll_us_metric.visibility=View.GONE
        ll_bmi_metric.visibility= View.VISIBLE
        currentBmiView=BMI_METRIC
tv_height_input.text!!.clear()
        tv_weight_input.text!!.clear()
ll_bmi_result.visibility=View.INVISIBLE
    }

    //Display BMI US Layout and Hide BMI Metric Layout
    fun displayUSBmi(){
        ll_us_metric.visibility=View.VISIBLE
        ll_bmi_metric.visibility= View.GONE
        currentBmiView=BMI_US
tv_USfeet_input.text!!.clear()
 tv_USinch_input.text!!.clear()
        tv_USweight_input.text!!.clear()
        ll_bmi_result.visibility=View.INVISIBLE
    }


}