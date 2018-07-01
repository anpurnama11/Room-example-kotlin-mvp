package com.dapenduk.dapenduk

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.dapenduk.dapenduk.home.HomeFragment
import com.dapenduk.dapenduk.login.LoginFragment
import com.dapenduk.dapenduk.login.LoginPresenterImpl
import com.dapenduk.dapenduk.login.SessionRepository
import com.dapenduk.dapenduk.search.SearchFragment
import com.dapenduk.dapenduk.util.replaceFragmentInActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                replaceFragmentInActivity(HomeFragment.newInstance(),R.id.containerView)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                val pref = getSharedPreferences("session",android.content.Context.MODE_PRIVATE)
                val repository = SessionRepository(pref)
                if(repository.getUsername().isBlank()) {
                    val loginFragment = LoginFragment.newInstance()
                    val presenterLogin = LoginPresenterImpl(loginFragment,repository)
                    replaceFragmentInActivity(loginFragment,R.id.containerView)
                }
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_search -> {
                replaceFragmentInActivity(SearchFragment.newInstance(),R.id.containerView)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        replaceFragmentInActivity(HomeFragment.newInstance(),R.id.containerView)
        navigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
    }
}
