package com.example.a7minutesworkout

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.a7minutesworkout.R
import kotlinx.android.synthetic.main.history_item.view.*

class HistoryAdapter(val context: Context,val dateList:ArrayList<String>):RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        var item=view.txt_dateHistory


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.history_item,parent,false))
    }

    override fun getItemCount(): Int {
return dateList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
     holder.item.text="Session completed:"+ dateList[position]

    }
}