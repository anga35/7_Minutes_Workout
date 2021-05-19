package com.example.a7minutesworkout

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_exercise_no.view.*

class ExerciseAdapter(var context: Context,var exerciseModel: ArrayList<ExerciseModel>):RecyclerView.Adapter<ExerciseAdapter.ViewHolder>(){

    var count=0
// View Holder class made to store views for them to be inflated
    class ViewHolder(view:View):RecyclerView.ViewHolder(view){
        var exerciseItem=view.tv_exercise_no
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

      return ViewHolder(
              LayoutInflater.from(context).inflate(R.layout.item_exercise_no,parent,false)

      )
    }

//Handles what happens with each ViewHolder as they are binded
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

var exercise=exerciseModel[position]
    count++
        holder.exerciseItem.text=count.toString()

        if(exercise.getIsSelected()==true){
            holder.exerciseItem.background=ContextCompat.getDrawable(context,R.drawable.rv_exercise_selected)

        }
        if(exercise.getIsCompleted()==true){

            holder.exerciseItem.background=ContextCompat.getDrawable(context,R.drawable.rv_exercise_completed)

        }



    }
// The amount of Views to be Binded
    override fun getItemCount(): Int {

        return  exerciseModel.size
    }

    override fun onViewRecycled(holder: ViewHolder) {

        count=1

    }

}