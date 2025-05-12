package com.example.nanokids

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "PlaySchool.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "children"
        private const val COLUMN_ID = "id"
        private const val COLUMN_NAME = "name"
        private const val COLUMN_EMAIL = "email"
        private const val COLUMN_PHONE = "phone"
        private const val COLUMN_AGE = "age"
        private const val COLUMN_GENDER = "gender"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery = """
            CREATE TABLE $TABLE_NAME (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_NAME TEXT,
                $COLUMN_EMAIL TEXT,
                $COLUMN_PHONE TEXT,
                $COLUMN_AGE INTEGER,
                $COLUMN_GENDER TEXT
            )
        """
        db?.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    // Add a child to the database
    fun addChild(name: String, email: String, phone: String, age: Int, gender: String) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_NAME, name)
            put(COLUMN_EMAIL, email)
            put(COLUMN_PHONE, phone)
            put(COLUMN_AGE, age)
            put(COLUMN_GENDER, gender)
        }
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    // Retrieve all contacts (email and phone) of children
    fun getAllContacts(): List<Pair<String, String>> {
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT $COLUMN_EMAIL, $COLUMN_PHONE FROM $TABLE_NAME", null)
        val contacts = mutableListOf<Pair<String, String>>()
        if (cursor.moveToFirst()) {
            do {
                val email = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EMAIL))
                val phone = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PHONE))
                contacts.add(Pair(email, phone))
            } while (cursor.moveToNext())
        }
        cursor.close()
        return contacts
    }

    // Retrieve details of all children
    fun getAllChildrenDetails(): List<Map<String, String>> {
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME", null)
        val children = mutableListOf<Map<String, String>>()
        if (cursor.moveToFirst()) {
            do {
                val childDetails = mapOf(
                    "id" to cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ID)),
                    "name" to cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME)),
                    "email" to cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EMAIL)),
                    "phone" to cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PHONE)),
                    "age" to cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_AGE)),
                    "gender" to cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_GENDER))
                )
                children.add(childDetails)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return children
    }
}
