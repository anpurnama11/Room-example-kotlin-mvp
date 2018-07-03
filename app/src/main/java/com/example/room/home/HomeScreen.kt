package com.example.room.home

import com.example.room.BaseScreen
import com.example.room.data.Citizen

interface HomeScreen: BaseScreen<HomePresenter> {
    fun bind(citizens: List<Citizen>)
    fun showDetail(id: String)
}