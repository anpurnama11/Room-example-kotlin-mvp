package com.dapenduk.dapenduk

interface BaseScreen<P> {
    var presenter: P
    fun show(message: String)
}