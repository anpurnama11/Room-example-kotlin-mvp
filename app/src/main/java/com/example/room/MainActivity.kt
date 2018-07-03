package com.example.room

import android.content.SharedPreferences
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity

import com.example.room.dashboard.DashboardFragment
import com.example.room.dashboard.DashboardPresenterImpl
import com.example.room.dashboard.DashboardScreen
import com.example.room.data.CitizenDatabase
import com.example.room.data.CitizenRepository
import com.example.room.data.SessionRepository
import com.example.room.home.HomeFragment
import com.example.room.home.HomePresenterImpl
import com.example.room.home.HomeScreen
import com.example.room.login.LoginFragment
import com.example.room.login.LoginPresenterImpl
import com.example.room.login.LoginScreen
import com.example.room.search.SearchFragment
import com.example.room.util.AppExecutors
import com.example.room.util.replaceFragmentInActivity
import com.example.room.util.setupActionBar


class MainActivity : AppCompatActivity() {

    lateinit var navigation:BottomNavigationView

    lateinit var presenterHome: HomePresenterImpl
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

    private fun providePresenterLogin(screen: LoginScreen, repository: SessionRepository) {
        presenterLogin = LoginPresenterImpl(screen,repository)
    }

    fun providePresenterDashboard(screen: DashboardScreen) {
        presenterDashboard = DashboardPresenterImpl(screen)
    }

    private fun providePresenterHome(screen: HomeScreen) {
        val database = CitizenDatabase.getInstance(applicationContext)
        val repository = CitizenRepository(AppExecutors(),database.dapendukDAO())
        presenterHome = HomePresenterImpl(screen,repository)
    }

}
