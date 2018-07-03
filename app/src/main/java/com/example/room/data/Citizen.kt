package com.example.room.data

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.*

@Entity(tableName = "Citizen")
data class Citizen(
        @PrimaryKey @ColumnInfo(name = "id") var id: String = UUID.randomUUID().toString(),
        @ColumnInfo(name = "name") var name: String,
        @ColumnInfo(name = "sex") var sex: Int = -1,
        @ColumnInfo(name = "address") var address: String,
        @ColumnInfo(name = "dateofbirth") var dateOfBirth: String,
        @ColumnInfo(name = "placeofbirth") var placeOfBirth: String,
        @ColumnInfo(name = "occupation") var occupation: String = ""
)