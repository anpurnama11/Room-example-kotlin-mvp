package com.dapenduk.dapenduk.detail

import com.dapenduk.dapenduk.data.Dapenduk
import com.dapenduk.dapenduk.data.DapendukRepository

class DetailPresenterImpl(val screen: DetailScreen,val repository: DapendukRepository):DetailPresenter {

    init {
        screen.presenter = this
    }

    override fun loadData(id: String) {
        repository.getData(id, object : DapendukRepository.DapendukRepositoryListener.GetDataListener {
            override fun onDataAvailable(data: Dapenduk) {
                screen.bind(data)
            }

            override fun onDataNotAvailable() {
                screen.show("error")
            }

        })
    }

    override fun showUpdateForm() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun delete(data: Dapenduk) {
        repository.delete(data)
        screen.show("success")
        screen.back()
    }
}