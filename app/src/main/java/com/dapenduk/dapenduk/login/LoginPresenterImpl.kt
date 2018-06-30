package com.dapenduk.dapenduk.login

class LoginPresenterImpl(val screen: LoginScreen,val repository: SessionRepository): LoginPresenter {

    init {
        screen.presenter = this
    }

    override fun isLogin() {
        if(!repository.getUsername().isBlank()) {
            screen.displayDashboard()
            screen.close()
        }
    }

    override fun onLoginButtonTapped(username: String, password: String) {
        if (username.isBlank()) {
            screen.show("username")
            return
        }
        else if (password.isBlank()) {
            screen.show("password")
            return
        }
        else if (username=="admin" && password=="admin") {
            repository.saveSession(username)
            screen.displayDashboard()
        }
        else
            screen.show("credentials")
    }

}