package com.dapenduk.dapenduk.home

import com.dapenduk.dapenduk.BaseScreen
import com.dapenduk.dapenduk.data.Dapenduk

interface HomeScreen: BaseScreen<HomePresenter> {
    fun bind(datas: List<Dapenduk>)
    fun showDetail(id: String)
}