package com.dapenduk.dapenduk.login

import android.content.SharedPreferences

class SessionRepository(private val pref:SharedPreferences) {

    fun saveSession(username:String) {
        val editor:SharedPreferences.Editor = pref.edit()
        editor.putString("username",username)
        editor.apply()
    }
    fun getUsername() = pref.getString("username","")
}