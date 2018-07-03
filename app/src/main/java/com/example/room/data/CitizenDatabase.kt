package com.example.room.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

@Database(entities = [Citizen::class],version = 1)
abstract class CitizenDatabase: RoomDatabase() {
    abstract fun dapendukDAO(): CitizenDao

    companion object {
        private var instance: CitizenDatabase? = null

        fun getInstance(context: Context): CitizenDatabase {
            if (instance ==null)
                instance = Room.databaseBuilder(context, CitizenDatabase::class.java,"dapenduk.db").build()
            return instance!!
        }
    }

}