package com.example.a7minutesworkout

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHandler(context: Context,factory: SQLiteDatabase.CursorFactory?=null):SQLiteOpenHelper(context, DATABASE_NAME,factory,
    DATABASE_VERSION) {

    companion object{
        private val DATABASE_NAME="DateDatabase"
        private val DATABASE_VERSION=1
        private val TABLE_HISTORY="History"
        private val COLUMN_ID="_id"
        private val COLUMN_COMPLETED_DATE="completed_date"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE=("CREATE TABLE $TABLE_HISTORY($COLUMN_ID INTEGER PRIMARY KEY,$COLUMN_COMPLETED_DATE TEXT)")
        db?.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
   db!!.execSQL("DROP TABLE IF EXISTS $TABLE_HISTORY")
        onCreate(db)
    }

    fun addDate(date:String){

        var values=ContentValues()
        values!!.put(COLUMN_COMPLETED_DATE,date)
        var db=this.writableDatabase
        db.insert(TABLE_HISTORY,null,values)
        db.close()
    }

    fun fetchData():ArrayList<String>{
        var dateHistoryList=ArrayList<String>()

        var db=this.readableDatabase

        var cursor:Cursor?=null

        var selectQuery="SELECT * FROM $TABLE_HISTORY"

        try{
            cursor=db.rawQuery(selectQuery,null)
        }
        catch (e:SQLiteException){
            return ArrayList()
        }

        if(cursor.moveToFirst()){
            do {
                var date:String=""

                date=cursor.getString(cursor.getColumnIndex(COLUMN_COMPLETED_DATE))

                dateHistoryList.add(date)
            }

            while (cursor.moveToNext())
        }


        return dateHistoryList
    }


}