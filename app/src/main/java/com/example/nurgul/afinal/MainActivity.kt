package com.example.nurgul.afinal

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Database
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Insert
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.Query
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.Update
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem


import kotlin.experimental.ExperimentalTypeInference

class MainActivity : AppCompatActivity() {
    @Entity
    inner class Contact {
        @PrimaryKey(autoGenerate = true)
        private val uid: Int = 0

        @ColumnInfo(name = "name")
        private val name: String? = null

        @ColumnInfo(name = "mobile phone number")
        private val mobphoneNumber: String? = null

        @ColumnInfo(name = "home phone number")
        private val homephoneNumber: String? = null

    }

    @Dao
    interface ContactDao {
        @get:Query("SELECT * FROM contact")
        val all: List<Contact>

        @Query("SELECT * FROM contact WHERE name LIKE :name LIMIT 1")
        fun findByName(name: String): Contact

        @Insert
        fun insertAll(contacts: List<Contact>)

        @Update
        fun update(contact: Contact)

        @Delete
        fun delete(contact: Contact)
    }

    @Database(entities = arrayOf(Contact::class), version = 1)
    abstract inner class MyDatabase : RoomDatabase() {
        abstract fun contactDao(): ContactDao
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        database = Room.database.Builder(applicationContext, MyDatabase::class.java, DATABASE_NAME).build()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        val fab = findViewById<View>(R.id.fab) as FloatingActionButton
        fab.setOnClickListener(View.OnClickListener { view ->
            Snackbar.make(view, text)
            "Add Contact"
            Snackbar.LENGTH_LONG
            setAction(text)
            "Action"
            listener
            null
            show()
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        return if (id == R.id.action_settings) {
            true
        } else super.onOptionsItemSelected(item)

    }
}