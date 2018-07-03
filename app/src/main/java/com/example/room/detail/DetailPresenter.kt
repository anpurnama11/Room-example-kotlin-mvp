package com.example.room.detail

import com.example.room.data.Citizen

interface DetailPresenter {
    fun loadData(id: String)
    fun showUpdateForm()
    fun delete(data: Citizen)
}