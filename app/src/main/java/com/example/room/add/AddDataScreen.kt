package com.example.room.add

import com.example.room.BaseScreen
import com.example.room.add.AddDataPresenter

interface AddDataScreen: BaseScreen<AddDataPresenter> {
    fun showCalendar()
}