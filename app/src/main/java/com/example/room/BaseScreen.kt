package com.example.room

interface BaseScreen<P> {
    var presenter: P
    fun show(message: String)
}