package com.example.a7minutesworkout

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_create_session_ontap.view.*

class ExerciseTextListAdapter (val context:Context,val exerciseModelList:ArrayList<ExerciseModel>):RecyclerView.Adapter<ExerciseTextListAdapter.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_create_session_ontap,parent,false))
    }

    override fun getItemCount(): Int {
 return exerciseModelList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.itemText.text=exerciseModelList[position].getName()
    }


    class ViewHolder(view: View):RecyclerView.ViewHolder(view){
var itemText=view.item_createSession

    }

}