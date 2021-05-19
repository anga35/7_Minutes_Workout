package com.example.a7minutesworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_history.*


class History : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

  setSupportActionBar(toolbar_history)

        var actionBar=supportActionBar

        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.title="History"
        }


        toolbar_history.setNavigationOnClickListener {

            finish()
        }
        if(getListFromDatabase().isNotEmpty()) {
            var historyAdapter = HistoryAdapter(this, getListFromDatabase())
            rv_historyList.layoutManager = LinearLayoutManager(this)
            rv_historyList.adapter = historyAdapter
        }
    }




    fun getListFromDatabase():ArrayList<String>{

        var databaseHandler=DatabaseHandler(this)


        return databaseHandler.fetchData()
    }
}