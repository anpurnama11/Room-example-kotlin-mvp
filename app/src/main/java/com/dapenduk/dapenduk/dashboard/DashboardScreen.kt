package com.dapenduk.dapenduk.dashboard

import com.dapenduk.dapenduk.BaseScreen

interface DashboardScreen: BaseScreen<DashboardPresenter> {
    fun showAddForm()
    fun showSearchTab()
    fun logout()
}