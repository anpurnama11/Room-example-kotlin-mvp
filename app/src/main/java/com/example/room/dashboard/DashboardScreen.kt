package com.example.room.dashboard

import com.example.room.BaseScreen
import com.example.room.dashboard.DashboardPresenter

interface DashboardScreen: BaseScreen<DashboardPresenter> {
    fun showAddForm()
    fun showSearchTab()
    fun logout()
}