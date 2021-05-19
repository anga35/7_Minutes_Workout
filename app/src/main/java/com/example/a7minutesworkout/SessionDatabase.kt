package com.example.a7minutesworkout

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class SessionDatabase(context: Context):SQLiteOpenHelper(context, DATABASE_NAME,null, DATABASE_VERSION) {

    companion object{
        private var DATABASE_NAME="SessionDatabase"
        private var DATABASE_VERSION=1
        private var TABLE_SESSION="Session"
        private var COLUMN_ID="_id"
        private var COLUMN_SESSIONDATA="data"
    }

    override fun onCreate(db: SQLiteDatabase?) {
var CREATE_TABLE=("CREATE TABLE $TABLE_SESSION($COLUMN_ID INTEGER PRIMARY KEY,$COLUMN_SESSIONDATA BLOB)")
        db?.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    db?.execSQL("DROP TABLE IF EXISTS $TABLE_SESSION")
        onCreate(db)
    }


    fun addSession(session:Session){
        var db=this.writableDatabase
        var contentValues=ContentValues()


        var rawData=ObjectParser.serialize(session)

        contentValues.put(COLUMN_SESSIONDATA,rawData)

db.insert(TABLE_SESSION,null,contentValues)
db.close()

    }

    fun fetchData():ArrayList<Session>{

        var db=this.readableDatabase
var sessionList=ArrayList<Session>()
        var cursor:Cursor?=null

        var selectQuery="SELECT * FROM $TABLE_SESSION"


       try {
           cursor=db.rawQuery(selectQuery,null)
       }
catch (e:SQLiteException){
    return ArrayList()
}
        if(cursor.moveToFirst()){
            do {
                var id=cursor.getInt(cursor.getColumnIndex(COLUMN_ID))
                var data=cursor.getBlob(cursor.getColumnIndex(COLUMN_SESSIONDATA))

                var session=ObjectParser.deserialize(data)
               try{
                (session as Session).id=id
                   }
               catch (e:Exception){
                   Log.d("Session Failed","Session faileddd")
               }


                sessionList.add(session as Session)

            }

            while (cursor.moveToNext())

        }
db.close()
return sessionList
    }

fun deleteSession(session: Session){
    var db=this.writableDatabase

    db.delete(TABLE_SESSION, COLUMN_ID+"="+session.id,null)
db.close()
}

}