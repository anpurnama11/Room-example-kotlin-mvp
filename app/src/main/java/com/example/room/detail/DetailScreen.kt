package com.example.room.detail

import com.example.room.BaseScreen
import com.example.room.data.Citizen

interface DetailScreen: BaseScreen<DetailPresenter> {
    fun bind(data: Citizen)
    fun showEditForm(id: String)
    fun back()
}