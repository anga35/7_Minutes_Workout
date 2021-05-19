package com.example.a7minutesworkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_manage_session.*

class ManageSession : AppCompatActivity() {
    var itemAdapter:ManageSessionAdapter?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manage_session)

        setSupportActionBar(toolbar_manageSession)
        var actionBar=supportActionBar

        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true)
        }
        toolbar_manageSession.setNavigationOnClickListener {
           onBackPressed()
        }




         itemAdapter=ManageSessionAdapter(this,fetchdata())
        rv_manageSession.layoutManager=LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        rv_manageSession.adapter=itemAdapter

    }

    fun fetchdata():ArrayList<Session>{

        var sessionDatabase=SessionDatabase(this)
       return sessionDatabase.fetchData()

    }

    fun refreshList(){
        itemAdapter=ManageSessionAdapter(this,fetchdata())
        rv_manageSession.layoutManager=LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        rv_manageSession.adapter=itemAdapter

    }

    override fun onBackPressed() {

        var intent=Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()

    }

}