package com.example.letslearninkotlin

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DataBaseHelper(
    context: Context?
) : SQLiteOpenHelper(context, "customDatabase", null, 1) {

    val CUSTOMER_TABLE = "CUSTOMER_TABLE"
    val CUSTOMER_NAME = "CUSTOMER_NAME"
    val CUSTOMER_PASSWORD = "CUSTOMER_PASSWORD"

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableStatement = "CREATE TABLE " + CUSTOMER_TABLE +
                " (ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                CUSTOMER_NAME + " TEXT, " + CUSTOMER_PASSWORD + " TEXT)"

        db?.execSQL(createTableStatement)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        //Not using this.
    }

    fun addOne(user : User) : Boolean {
        val db = this.writableDatabase
        val cv = ContentValues()

        cv.put(CUSTOMER_NAME, user.username)
        cv.put(CUSTOMER_PASSWORD, user.password)
        val long = db.insert(CUSTOMER_TABLE, null, cv)

        return long != -1L
    }

    fun getEveryone() : List<User> {
        var returnList = mutableListOf<User>()

        val queryString = "SELECT * FROM " + CUSTOMER_TABLE
        val db = this.writableDatabase

        val cursor = db.rawQuery(queryString, null)

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(0)
                val name = cursor.getString(1)
                val password = cursor.getString(2)

                val user = User(id, name, password)
                returnList.add(user)
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()

        return returnList
    }

    fun deleteData(name: String): Int {
        val db = this.writableDatabase
        val result = db.delete(
            CUSTOMER_TABLE,
            CUSTOMER_NAME + " = ?", arrayOf(name)
        )
        db.close()
        return result
    }

    fun updateData(currentName: String, newName: String?): Boolean {
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(CUSTOMER_NAME, newName)
        db.update(
            CUSTOMER_TABLE, cv,
            CUSTOMER_NAME + " = ?", arrayOf(currentName)
        )
        return true
    }
}