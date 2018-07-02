package com.dapenduk.dapenduk

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.dapenduk.dapenduk.dashboard.DashboardFragment
import com.dapenduk.dapenduk.dashboard.DashboardPresenterImpl
import com.dapenduk.dapenduk.dashboard.DashboardScreen
import com.dapenduk.dapenduk.home.HomeFragment
import com.dapenduk.dapenduk.login.LoginFragment
import com.dapenduk.dapenduk.login.LoginPresenterImpl
import com.dapenduk.dapenduk.login.LoginScreen
import com.dapenduk.dapenduk.login.SessionRepository
import com.dapenduk.dapenduk.search.SearchFragment
import com.dapenduk.dapenduk.util.addFragmentToActivity
import com.dapenduk.dapenduk.util.replaceFragmentInActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var navigation:BottomNavigationView

    lateinit var presenterLogin: LoginPresenterImpl
    lateinit var presenterDashboard: DashboardPresenterImpl

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
                    providePresenterLogin(loginFragment,repository)
                    replaceFragmentInActivity(loginFragment,R.id.containerView)
                }
                else {
                    val dashboardFragment = DashboardFragment.newInstance()
                    providePresenterDashboard(dashboardFragment)
                    replaceFragmentInActivity(dashboardFragment,R.id.containerView)
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
        navigation = findViewById(R.id.navigation)
        navigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
    }

    private fun providePresenterLogin(screen: LoginScreen,repository:SessionRepository) {
        presenterLogin = LoginPresenterImpl(screen,repository)
    }

    fun providePresenterDashboard(screen: DashboardScreen) {
        presenterDashboard = DashboardPresenterImpl(screen)
    }

}
