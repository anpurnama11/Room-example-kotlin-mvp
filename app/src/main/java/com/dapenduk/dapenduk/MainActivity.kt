package com.dapenduk.dapenduk

import android.content.SharedPreferences
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import com.dapenduk.dapenduk.dashboard.DashboardFragment
import com.dapenduk.dapenduk.dashboard.DashboardPresenterImpl
import com.dapenduk.dapenduk.dashboard.DashboardScreen
import com.dapenduk.dapenduk.data.DapendukDatabase
import com.dapenduk.dapenduk.data.DapendukRepository
import com.dapenduk.dapenduk.home.HomeFragment
import com.dapenduk.dapenduk.login.LoginFragment
import com.dapenduk.dapenduk.login.LoginPresenterImpl
import com.dapenduk.dapenduk.login.LoginScreen
import com.dapenduk.dapenduk.data.SessionRepository
import com.dapenduk.dapenduk.home.HomePresenterImpl
import com.dapenduk.dapenduk.home.HomeScreen
import com.dapenduk.dapenduk.search.SearchFragment
import com.dapenduk.dapenduk.util.AppExecutors
import com.dapenduk.dapenduk.util.replaceFragmentInActivity
import com.dapenduk.dapenduk.util.setupActionBar

class MainActivity : AppCompatActivity() {

    lateinit var navigation:BottomNavigationView

    lateinit var presenterHome:HomePresenterImpl
    lateinit var presenterLogin: LoginPresenterImpl
    lateinit var presenterDashboard: DashboardPresenterImpl
    lateinit var preferences: SharedPreferences

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                val homeFragment = HomeFragment.newInstance()
                providePresenterHome(homeFragment)
                replaceFragmentInActivity(homeFragment,R.id.containerView)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                preferences = getSharedPreferences("session",android.content.Context.MODE_PRIVATE)
                val repository = SessionRepository(preferences)
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

        setupActionBar(R.id.toolbar) {
            title = getString(R.string.app_name)
        }

        val homeFragment = HomeFragment.newInstance()
        providePresenterHome(homeFragment)
        replaceFragmentInActivity(homeFragment,R.id.containerView)
        navigation = findViewById(R.id.navigation)
        navigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
    }

    private fun providePresenterLogin(screen: LoginScreen,repository: SessionRepository) {
        presenterLogin = LoginPresenterImpl(screen,repository)
    }

    fun providePresenterDashboard(screen: DashboardScreen) {
        presenterDashboard = DashboardPresenterImpl(screen)
    }

    private fun providePresenterHome(screen: HomeScreen) {
        val database = DapendukDatabase.getInstance(applicationContext)
        val repository = DapendukRepository(AppExecutors(),database.dapendukDAO())
        presenterHome = HomePresenterImpl(screen,repository)
    }

}
