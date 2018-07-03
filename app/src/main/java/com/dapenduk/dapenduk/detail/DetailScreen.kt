package com.dapenduk.dapenduk.detail

import com.dapenduk.dapenduk.BaseScreen
import com.dapenduk.dapenduk.data.Dapenduk

interface DetailScreen: BaseScreen<DetailPresenter> {
    fun bind(data: Dapenduk)
    fun showEditForm(id: String)
    fun back()
}