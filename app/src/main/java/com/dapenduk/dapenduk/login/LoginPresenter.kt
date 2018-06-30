package com.dapenduk.dapenduk.login

interface LoginPresenter {
    fun isLogin()
    fun onLoginButtonTapped(username: String, password: String)
}