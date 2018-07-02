package com.dapenduk.dapenduk.login

import com.dapenduk.dapenduk.BaseScreen

interface LoginScreen: BaseScreen<LoginPresenter> {
    fun displayDashboard()
}