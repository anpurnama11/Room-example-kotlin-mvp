package com.example.room.login

interface LoginPresenter {
    fun isLogin()
    fun onLoginButtonTapped(username: String, password: String)
}