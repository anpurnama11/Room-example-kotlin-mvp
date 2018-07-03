package com.dapenduk.dapenduk.detail

import com.dapenduk.dapenduk.data.Dapenduk

interface DetailPresenter {
    fun loadData(id: String)
    fun showUpdateForm()
    fun delete(data: Dapenduk)
}