package com.dapenduk.dapenduk.home

import com.dapenduk.dapenduk.data.Dapenduk
import com.dapenduk.dapenduk.data.DapendukRepository

class HomePresenterImpl(val screen: HomeScreen,val repository: DapendukRepository):HomePresenter {

    init {
        screen.presenter = this
    }

    override fun loadDatas() {
        repository.getDatas(object : DapendukRepository.DapendukRepositoryListener.getDatasListener {
            override fun onDatasAvailable(datas: List<Dapenduk>) {
                screen.bind(datas)
            }

            override fun ondatasNotAvailable() {
                screen.show("empty")
            }

        })
    }

    override fun onDataClicked(id: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}