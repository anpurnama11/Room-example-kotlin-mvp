package com.example.room.login

import com.example.room.BaseScreen
import com.example.room.login.LoginPresenter

interface LoginScreen: BaseScreen<LoginPresenter> {
    fun displayDashboard()
}