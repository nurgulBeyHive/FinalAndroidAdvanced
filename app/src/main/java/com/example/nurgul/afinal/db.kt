package com.example.nurgul.afinal

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.os.Build
import java.security.AccessControlContext

@Database (entities=[Contacts::class], version=AppDatabase.VERSION)
abstract class AppDatabase: RoomDatabase(){
    abstract fun contactDao(): ContactDao
    companion object {
        const val DB_NAME="contacts.db"
        const val VERSION=1
        private val instance: AppDatabase by lazy { create(App.instance)}

        @Synchronized
        internal fun getInstance (): AppDatabase{
            return instance
        }

        private fun create(context: Context): AppDatabase{
            return Room.databaseBuilder(context,AppDatabase::class.java, DB_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
        }
    }
}