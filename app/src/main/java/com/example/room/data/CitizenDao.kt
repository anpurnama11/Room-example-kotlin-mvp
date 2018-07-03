package com.example.room.data

import android.arch.persistence.room.*

@Dao interface CitizenDao {

    @Query("select * from Citizen")
    fun getDatas(): List<Citizen>

    @Query("select * from Citizen where id = :id")
    fun getDataById(id: String): Citizen

    @Query("select * from Citizen where name = :name ")
    fun getDataByName(name: String): Citizen

    @Query("select * from Citizen where address = :address ")
    fun getDataByAddress(address: String): Citizen

    @Insert
    fun insert(data: Citizen)

    @Update
    fun update(data: Citizen)

    @Delete
    fun delete(data: Citizen)

}