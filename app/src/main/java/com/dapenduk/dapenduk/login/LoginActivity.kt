package com.dapenduk.dapenduk.login

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import com.dapenduk.dapenduk.R
import com.dapenduk.dapenduk.showSnackBar
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(),LoginScreen {

    override lateinit var presenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val pref = this.getSharedPreferences("session",android.content.Context.MODE_PRIVATE)
        val repository = SessionRepository(pref)
        presenter = LoginPresenterImpl(this,repository)

        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()
            presenter.onLoginButtonTapped(username,password)
        }

    }

    override fun show(message: String) {
        var text = ""
        when(message) {
            "username" -> text = getString(R.string.username_empty)
            "password" -> text = getString(R.string.password_empty)
            "credentials" -> text = getString(R.string.wrong_credentials)
        }
        container.showSnackBar(text,Snackbar.LENGTH_LONG)
    }

    override fun displayDashboard() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun close() {
        finish()
    }

}
