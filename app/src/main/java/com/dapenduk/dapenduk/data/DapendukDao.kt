package com.dapenduk.dapenduk.data

import android.arch.persistence.room.*

@Dao interface DapendukDao {

    @Query("select * from Dapenduk")
    fun getDatas(): List<Dapenduk>

    @Query("select * from Dapenduk where name = :name ")
    fun getDataByName(name: String):Dapenduk

    @Query("select * from Dapenduk where address = :address ")
    fun getDataByAddress(address: String):Dapenduk

    @Insert
    fun insert(data: Dapenduk)

    @Update
    fun update(data: Dapenduk)

    @Delete
    fun delete(data: Dapenduk)

}