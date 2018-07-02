package com.dapenduk.dapenduk.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

@Database(entities = [Dapenduk::class],version = 1)
abstract class DapendukDatabase: RoomDatabase() {
    abstract fun dapendukDAO(): DapendukDao

    companion object {
        private var instance: DapendukDatabase? = null

        fun getInstance(context: Context): DapendukDatabase {
            if (instance==null)
                instance = Room.databaseBuilder(context,DapendukDatabase::class.java,"dapenduk.db").build()
            return instance!!
        }
    }

}