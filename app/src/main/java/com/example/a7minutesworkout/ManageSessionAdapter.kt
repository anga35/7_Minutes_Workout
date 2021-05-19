package com.example.a7minutesworkout

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_manage_session.view.*

class ManageSessionAdapter(val context: Context,val sessionList:ArrayList<Session>):RecyclerView.Adapter<ManageSessionAdapter.ViewHolder>() {




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_manage_session,parent,false))
    }

    override fun getItemCount(): Int {
     return sessionList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
      holder.itemName.text=sessionList[position].sessionName

        holder.itemDelete.setOnClickListener {
deleteSession(sessionList[position])

            if(context is ManageSession){
                context.refreshList()

            }


        }


    }



    class ViewHolder(view: View):RecyclerView.ViewHolder(view){
var itemName=view.item_sessionName
var itemDelete=view.manageSession_delete
    }

    fun deleteSession(session:Session){
        var SessionDatabase=SessionDatabase(context)
        SessionDatabase.deleteSession(session)


    }

}